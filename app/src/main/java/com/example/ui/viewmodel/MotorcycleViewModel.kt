package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ai.BikeAssistantEngine
import com.example.data.ai.ChatMessage
import com.example.data.ai.MessageSender
import com.example.data.db.AppDatabase
import com.example.data.model.*
import com.example.data.repository.DemoDataProvider
import com.example.data.repository.MotorcycleRepository
import com.example.ui.components.AppDestination
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MotorcycleViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MotorcycleRepository

    val currentDestination = MutableStateFlow(AppDestination.HOME)
    val learningLevel = MutableStateFlow(LearningLevel.BEGINNER)

    // Motorcycle specs
    val allSpecs = MutableStateFlow(DemoDataProvider.demoMotorcycles)
    val currentSpec = MutableStateFlow(DemoDataProvider.demoMotorcycles.first())

    // Database backed states
    val userMotorcycle: StateFlow<UserMotorcycleEntity?>
    val serviceHistory: StateFlow<List<ServiceHistoryEntity>>
    val checklistHistory: StateFlow<List<ChecklistRecordEntity>>
    val customGuides: StateFlow<List<CustomGuideEntity>>

    // Dynamic calculated reminders
    val serviceReminders = MutableStateFlow<List<ServiceReminderItem>>(emptyList())

    // Chat AI state
    val chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                sender = MessageSender.ASSISTANT,
                text = "Hello! I am your Bike Assistant. Ask me anything about motorcycle mechanics, diagnostic noises, pre-ride checks, or scheduled servicing.",
                timestamp = System.currentTimeMillis()
            )
        )
    )
    val isAiLoading = MutableStateFlow(false)

    init {
        val database = AppDatabase.getInstance(application)
        repository = MotorcycleRepository(database)

        userMotorcycle = repository.userMotorcycle
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

        serviceHistory = repository.allServiceRecords
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

        checklistHistory = repository.allChecklistRecords
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

        customGuides = repository.customGuides
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

        // Recalculate reminders whenever currentSpec, userMotorcycle, or serviceHistory changes
        viewModelScope.launch {
            combine(currentSpec, userMotorcycle, serviceHistory) { spec, userBike, history ->
                val currentKm = userBike?.currentKm ?: 1250
                val lastServiceKm = userBike?.lastServiceKm ?: 1000
                val latestOilRecord = history.firstOrNull { it.oilReplaced.isNotBlank() }
                val oilKm = latestOilRecord?.mileageKm ?: lastServiceKm
                val chainKm = userBike?.lastServiceKm ?: 1000

                repository.calculateReminders(
                    currentKm = currentKm,
                    lastServiceKm = lastServiceKm,
                    oilChangedKm = oilKm,
                    chainLubeKm = chainKm,
                    spec = spec
                )
            }.collect { calculated ->
                serviceReminders.value = calculated
            }
        }
    }

    fun navigateTo(destination: AppDestination) {
        currentDestination.value = destination
    }

    fun setLearningLevel(level: LearningLevel) {
        learningLevel.value = level
    }

    fun selectMotorcycle(spec: MotorcycleSpec) {
        currentSpec.value = spec
        viewModelScope.launch {
            val existing = userMotorcycle.value
            val entity = UserMotorcycleEntity(
                id = existing?.id ?: 1,
                brand = spec.brand,
                model = spec.model,
                year = existing?.year ?: spec.modelYear,
                nickname = existing?.nickname.takeIf { !it.isNullOrBlank() } ?: "${spec.brand} ${spec.model}",
                currentKm = existing?.currentKm ?: 1250,
                purchaseDate = existing?.purchaseDate ?: "2024-03-15",
                lastServiceDate = existing?.lastServiceDate ?: "2024-06-10",
                lastServiceKm = existing?.lastServiceKm ?: 1000
            )
            repository.saveUserMotorcycle(entity)
        }
    }

    fun updateUserDetails(
        nickname: String,
        year: Int,
        currentKm: Int,
        purchaseDate: String,
        lastServiceDate: String,
        lastServiceKm: Int
    ) {
        viewModelScope.launch {
            val existing = userMotorcycle.value
            val spec = currentSpec.value
            val entity = UserMotorcycleEntity(
                id = existing?.id ?: 1,
                brand = spec.brand,
                model = spec.model,
                year = year,
                nickname = nickname,
                currentKm = currentKm,
                purchaseDate = purchaseDate,
                lastServiceDate = lastServiceDate,
                lastServiceKm = lastServiceKm
            )
            repository.saveUserMotorcycle(entity)
        }
    }

    fun addServiceRecord(record: ServiceHistoryEntity) {
        viewModelScope.launch {
            repository.addServiceRecord(record)
            if (record.mileageKm > (userMotorcycle.value?.currentKm ?: 0)) {
                repository.updateMileage(record.mileageKm)
            }
        }
    }

    fun deleteServiceRecord(record: ServiceHistoryEntity) {
        viewModelScope.launch {
            repository.deleteServiceRecord(record)
        }
    }

    fun saveChecklistRecord(record: ChecklistRecordEntity) {
        viewModelScope.launch {
            repository.addChecklistRecord(record)
        }
    }

    fun addCustomGuide(guide: CustomGuideEntity) {
        viewModelScope.launch {
            repository.addCustomGuide(guide)
        }
    }

    fun deleteCustomGuide(id: Long) {
        viewModelScope.launch {
            repository.deleteCustomGuide(id)
        }
    }

    fun sendAiMessage(prompt: String) {
        val userMsg = ChatMessage(sender = MessageSender.USER, text = prompt)
        chatMessages.value = chatMessages.value + userMsg

        viewModelScope.launch {
            isAiLoading.value = true
            try {
                val spec = currentSpec.value
                val context = "${spec.brand} ${spec.model} (${spec.modelYear}), Engine: ${spec.engineType} ${spec.engineDisplacement}, Transmission: ${spec.transmission}, Level: ${learningLevel.value.name}"
                val response = BikeAssistantEngine.askAssistant(prompt, context)
                val aiMsg = ChatMessage(sender = MessageSender.ASSISTANT, text = response)
                chatMessages.value = chatMessages.value + aiMsg
            } catch (e: Exception) {
                val fallbackMsg = ChatMessage(
                    sender = MessageSender.ASSISTANT,
                    text = "For safety: If your motorcycle shows severe warning signs, unusual grinding noises, or braking loss, stop riding immediately and consult a certified technician."
                )
                chatMessages.value = chatMessages.value + fallbackMsg
            } finally {
                isAiLoading.value = false
            }
        }
    }
}
