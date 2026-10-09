package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ChecklistRecordEntity
import com.example.data.repository.DemoDataProvider
import com.example.ui.components.GlassCard
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChecklistsScreen(
    checklistHistory: List<ChecklistRecordEntity>,
    onSaveChecklist: (ChecklistRecordEntity) -> Unit
) {
    var selectedListType by remember { mutableIntStateOf(0) } // 0: Pre-Ride, 1: Long-Ride, 2: History
    val preRideItems = DemoDataProvider.preRideChecklistItems
    val longRideItems = DemoDataProvider.longRideChecklistItems

    var preRideChecked by remember { mutableStateOf(setOf<Int>()) }
    var longRideChecked by remember { mutableStateOf(setOf<Int>()) }
    var saveSuccessMessage by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CarbonBlack),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column {
                Text(
                    text = "RIDER SAFETY CHECKLISTS",
                    color = RacingAmber,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "T-CLOCS & Touring Inspection",
                    color = TitaniumSilver,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Never embark on two wheels without verifying critical controls, brakes, and tyre conditions.",
                    color = MutedSlate,
                    fontSize = 13.sp
                )
            }
        }

        // Checklist Selection Tabs
        item {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = GunmetalSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(4.dp)) {
                    Surface(
                        onClick = { selectedListType = 0 },
                        shape = RoundedCornerShape(8.dp),
                        color = if (selectedListType == 0) RacingAmber else Color.Transparent,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(
                                text = "Pre-Ride",
                                color = if (selectedListType == 0) Color.Black else TitaniumSilver,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Surface(
                        onClick = { selectedListType = 1 },
                        shape = RoundedCornerShape(8.dp),
                        color = if (selectedListType == 1) RacingAmber else Color.Transparent,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(
                                text = "Long-Ride",
                                color = if (selectedListType == 1) Color.Black else TitaniumSilver,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Surface(
                        onClick = { selectedListType = 2 },
                        shape = RoundedCornerShape(8.dp),
                        color = if (selectedListType == 2) RacingAmber else Color.Transparent,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(
                                text = "History (${checklistHistory.size})",
                                color = if (selectedListType == 2) Color.Black else TitaniumSilver,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        if (saveSuccessMessage != null) {
            item {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = StatusGood.copy(alpha = 0.15f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, StatusGood),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = StatusGood)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = saveSuccessMessage.orEmpty(), color = TitaniumSilver, fontSize = 12.sp)
                    }
                }
            }
        }

        when (selectedListType) {
            0 -> {
                // PRE-RIDE CHECKLIST
                item {
                    val progress = preRideChecked.size.toFloat() / preRideItems.size
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "DAILY BEFORE-RIDE INSPECTION",
                                color = TechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "${preRideChecked.size} of ${preRideItems.size} Checked",
                                color = TitaniumSilver,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { progress },
                            color = RacingAmber,
                            trackColor = GunmetalSurface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                        )
                    }
                }

                itemsIndexed(preRideItems) { index, itemText ->
                    val checked = preRideChecked.contains(index)
                    ChecklistTile(
                        text = itemText,
                        checked = checked,
                        onToggle = {
                            preRideChecked = if (checked) preRideChecked - index else preRideChecked + index
                            saveSuccessMessage = null
                        }
                    )
                }

                item {
                    Button(
                        onClick = {
                            onSaveChecklist(
                                ChecklistRecordEntity(
                                    checklistType = "Pre-Ride Checklist",
                                    timestamp = System.currentTimeMillis(),
                                    itemsCompletedCount = preRideChecked.size,
                                    totalItemsCount = preRideItems.size,
                                    notes = "Verified cold tyre pressure and full control operation."
                                )
                            )
                            saveSuccessMessage = "Pre-Ride inspection saved to logbook!"
                        },
                        enabled = preRideChecked.isNotEmpty(),
                        colors = ButtonDefaults.buttonColors(containerColor = RacingAmber),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text("Log Pre-Ride Verification", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }

            1 -> {
                // LONG-RIDE CHECKLIST
                item {
                    val progress = longRideChecked.size.toFloat() / longRideItems.size
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "TOURING & LONG-RIDE PREPARATION",
                                color = TechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "${longRideChecked.size} of ${longRideItems.size} Checked",
                                color = TitaniumSilver,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { progress },
                            color = RacingAmber,
                            trackColor = GunmetalSurface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                        )
                    }
                }

                itemsIndexed(longRideItems) { index, itemText ->
                    val checked = longRideChecked.contains(index)
                    ChecklistTile(
                        text = itemText,
                        checked = checked,
                        onToggle = {
                            longRideChecked = if (checked) longRideChecked - index else longRideChecked + index
                            saveSuccessMessage = null
                        }
                    )
                }

                item {
                    Button(
                        onClick = {
                            onSaveChecklist(
                                ChecklistRecordEntity(
                                    checklistType = "Long-Ride / Touring Checklist",
                                    timestamp = System.currentTimeMillis(),
                                    itemsCompletedCount = longRideChecked.size,
                                    totalItemsCount = longRideItems.size,
                                    notes = "Pre-departure tour prep: chain lubed, fluids verified, puncture kit packed."
                                )
                            )
                            saveSuccessMessage = "Long-Ride verification saved to logbook!"
                        },
                        enabled = longRideChecked.isNotEmpty(),
                        colors = ButtonDefaults.buttonColors(containerColor = RacingAmber),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text("Log Long-Ride Tour Preparation", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }

            2 -> {
                // HISTORY LOG
                if (checklistHistory.isEmpty()) {
                    item {
                        GlassCard(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(Icons.Default.History, contentDescription = null, tint = MutedSlate, modifier = Modifier.size(40.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("No Verified Checklists Yet", color = TitaniumSilver, fontWeight = FontWeight.Bold)
                                Text("Check off items and tap 'Log Verification' to archive your ride checks.", color = MutedSlate, fontSize = 12.sp)
                            }
                        }
                    }
                } else {
                    itemsIndexed(checklistHistory) { _, record ->
                        val dateStr = remember(record.timestamp) {
                            SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(record.timestamp))
                        }
                        GlassCard(modifier = Modifier.fillMaxWidth(), borderColor = GunmetalBorder) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = record.checklistType, color = RacingAmber, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text(text = "📅 $dateStr", color = TitaniumSilver, fontSize = 11.sp)
                                    if (record.notes.isNotBlank()) {
                                        Text(text = record.notes, color = MutedSlate, fontSize = 11.sp)
                                    }
                                }
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = StatusGood.copy(alpha = 0.15f),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, StatusGood)
                                ) {
                                    Text(
                                        text = "${record.itemsCompletedCount}/${record.totalItemsCount} Done",
                                        color = StatusGood,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChecklistTile(
    text: String,
    checked: Boolean,
    onToggle: () -> Unit
) {
    Surface(
        onClick = onToggle,
        shape = RoundedCornerShape(10.dp),
        color = if (checked) RacingAmber.copy(alpha = 0.12f) else GunmetalSurface,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (checked) RacingAmber.copy(alpha = 0.6f) else GunmetalBorder
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(
                    checkedColor = RacingAmber,
                    uncheckedColor = MutedSlate,
                    checkmarkColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = if (checked) TitaniumSilver else TitaniumSilver.copy(alpha = 0.85f),
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
    }
}
