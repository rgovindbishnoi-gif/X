package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.repository.DemoDataProvider
import com.example.ui.components.AppDestination
import com.example.ui.components.MotoBottomBar
import com.example.ui.components.MotoTopAppBar
import com.example.ui.components.SearchDialog
import com.example.ui.components.SearchTargetType
import com.example.ui.screens.*
import com.example.ui.theme.MotoGuideTheme
import com.example.ui.viewmodel.MotorcycleViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotoGuideTheme {
                MainAppScreen()
            }
        }
    }
}

@Composable
fun MainAppScreen(
    viewModel: MotorcycleViewModel = viewModel()
) {
    val currentDestination by viewModel.currentDestination.collectAsState()
    val currentSpec by viewModel.currentSpec.collectAsState()
    val allSpecs by viewModel.allSpecs.collectAsState()
    val userMotorcycle by viewModel.userMotorcycle.collectAsState()
    val serviceReminders by viewModel.serviceReminders.collectAsState()
    val serviceHistory by viewModel.serviceHistory.collectAsState()
    val checklistHistory by viewModel.checklistHistory.collectAsState()
    val customGuides by viewModel.customGuides.collectAsState()
    val learningLevel by viewModel.learningLevel.collectAsState()
    val chatMessages by viewModel.chatMessages.collectAsState()
    val isAiLoading by viewModel.isAiLoading.collectAsState()

    var showSearchDialog by remember { mutableStateOf(false) }
    var pendingAiPrompt by remember { mutableStateOf<String?>(null) }

    // Hardware / gesture back handling
    BackHandler(enabled = currentDestination != AppDestination.HOME) {
        viewModel.navigateTo(AppDestination.HOME)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MotoTopAppBar(
                title = currentDestination.title,
                subtitle = "${currentSpec.brand} ${currentSpec.model}",
                showBackButton = currentDestination != AppDestination.HOME && !currentDestination.isPrimaryTab,
                onBackClick = { viewModel.navigateTo(AppDestination.HOME) },
                activeBikeName = userMotorcycle?.nickname?.takeIf { it.isNotBlank() }
                    ?: "${currentSpec.brand} ${currentSpec.model}",
                onBikeClick = { viewModel.navigateTo(AppDestination.SPECS) },
                onSearchClick = { showSearchDialog = true },
                onSettingsClick = { viewModel.navigateTo(AppDestination.SETTINGS) }
            )
        },
        bottomBar = {
            MotoBottomBar(
                currentRoute = currentDestination.route,
                onNavigate = { dest -> viewModel.navigateTo(dest) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = currentDestination, label = "ScreenTransition") { destination ->
                when (destination) {
                    AppDestination.HOME -> {
                        HomeScreen(
                            motorcycle = userMotorcycle,
                            spec = currentSpec,
                            reminders = serviceReminders,
                            stages = DemoDataProvider.motorcycleStages,
                            learningLevel = learningLevel,
                            onNavigateToSpecs = { viewModel.navigateTo(AppDestination.SPECS) },
                            onNavigateToStages = { viewModel.navigateTo(AppDestination.STAGES) },
                            onNavigateToParts = { viewModel.navigateTo(AppDestination.PARTS) },
                            onNavigateToMaintenance = { viewModel.navigateTo(AppDestination.MAINTENANCE) },
                            onNavigateToTroubleshooting = { viewModel.navigateTo(AppDestination.TROUBLESHOOTING) },
                            onNavigateToWarningSigns = { viewModel.navigateTo(AppDestination.WARNING_SIGNS) },
                            onNavigateToHealthCheck = { viewModel.navigateTo(AppDestination.HEALTH_CHECK) },
                            onNavigateToChecklists = { viewModel.navigateTo(AppDestination.CHECKLISTS) },
                            onNavigateToCareMods = { viewModel.navigateTo(AppDestination.CARE_MODS) },
                            onNavigateToAssistantWithPrompt = { prompt ->
                                pendingAiPrompt = prompt
                                viewModel.navigateTo(AppDestination.ASSISTANT)
                            },
                            onQuickUpdateKm = { km ->
                                viewModel.updateUserDetails(
                                    nickname = userMotorcycle?.nickname.orEmpty(),
                                    year = userMotorcycle?.year ?: currentSpec.modelYear,
                                    currentKm = km,
                                    purchaseDate = userMotorcycle?.purchaseDate ?: "2024-03-15",
                                    lastServiceDate = userMotorcycle?.lastServiceDate ?: "2024-06-10",
                                    lastServiceKm = userMotorcycle?.lastServiceKm ?: 1000
                                )
                            }
                        )
                    }

                    AppDestination.SPECS -> {
                        MotorcycleDetailScreen(
                            currentSpec = currentSpec,
                            allSpecs = allSpecs,
                            userMotorcycle = userMotorcycle,
                            onSelectMotorcycle = { spec -> viewModel.selectMotorcycle(spec) },
                            onSaveUserDetails = { nick, yr, km, pDate, lsDate, lsKm ->
                                viewModel.updateUserDetails(nick, yr, km, pDate, lsDate, lsKm)
                            }
                        )
                    }

                    AppDestination.PARTS -> {
                        PartsScreen(
                            parts = DemoDataProvider.motorcycleParts,
                            currentLearningLevel = learningLevel,
                            onLearningLevelChanged = { viewModel.setLearningLevel(it) }
                        )
                    }

                    AppDestination.STAGES -> {
                        StagesScreen(
                            stages = DemoDataProvider.motorcycleStages
                        )
                    }

                    AppDestination.MAINTENANCE -> {
                        MaintenanceDashboardScreen(
                            motorcycle = userMotorcycle,
                            spec = currentSpec,
                            reminders = serviceReminders,
                            serviceRecords = serviceHistory,
                            onAddRecord = { viewModel.addServiceRecord(it) },
                            onDeleteRecord = { viewModel.deleteServiceRecord(it) }
                        )
                    }

                    AppDestination.TROUBLESHOOTING -> {
                        TroubleshootingScreen(
                            categories = DemoDataProvider.troubleshootingCategories,
                            steps = DemoDataProvider.troubleshootingSteps
                        )
                    }

                    AppDestination.WARNING_SIGNS -> {
                        WarningSignsScreen(
                            warningSigns = DemoDataProvider.warningSigns
                        )
                    }

                    AppDestination.HEALTH_CHECK -> {
                        HealthCheckScreen(
                            questions = DemoDataProvider.healthCheckQuestions
                        )
                    }

                    AppDestination.CHECKLISTS -> {
                        ChecklistsScreen(
                            checklistHistory = checklistHistory,
                            onSaveChecklist = { viewModel.saveChecklistRecord(it) }
                        )
                    }

                    AppDestination.CARE_MODS -> {
                        CleaningModScreen(
                            careGuides = DemoDataProvider.careGuides,
                            modifications = DemoDataProvider.modifications
                        )
                    }

                    AppDestination.ASSISTANT -> {
                        val promptToRun = pendingAiPrompt
                        pendingAiPrompt = null
                        AiAssistantScreen(
                            currentSpec = currentSpec,
                            messages = chatMessages,
                            isLoading = isAiLoading,
                            initialPrompt = promptToRun,
                            onSendMessage = { prompt -> viewModel.sendAiMessage(prompt) }
                        )
                    }

                    AppDestination.SETTINGS -> {
                        SettingsScreen(
                            currentLevel = learningLevel,
                            onLevelChanged = { viewModel.setLearningLevel(it) },
                            onNavigateToAdmin = { viewModel.navigateTo(AppDestination.ADMIN) }
                        )
                    }

                    AppDestination.ADMIN -> {
                        AdminScreen(
                            customGuides = customGuides,
                            allSpecs = allSpecs,
                            onAddCustomGuide = { viewModel.addCustomGuide(it) },
                            onDeleteCustomGuide = { viewModel.deleteCustomGuide(it) }
                        )
                    }
                }
            }
        }
    }

    if (showSearchDialog) {
        SearchDialog(
            parts = DemoDataProvider.motorcycleParts,
            stages = DemoDataProvider.motorcycleStages,
            warningSigns = DemoDataProvider.warningSigns,
            onDismiss = { showSearchDialog = false },
            onSelectTarget = { targetType, _ ->
                when (targetType) {
                    SearchTargetType.PART -> viewModel.navigateTo(AppDestination.PARTS)
                    SearchTargetType.STAGE -> viewModel.navigateTo(AppDestination.STAGES)
                    SearchTargetType.WARNING_SIGN -> viewModel.navigateTo(AppDestination.WARNING_SIGNS)
                    SearchTargetType.CARE_GUIDE -> viewModel.navigateTo(AppDestination.CARE_MODS)
                }
            }
        )
    }
}
