package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.*
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    motorcycle: UserMotorcycleEntity?,
    spec: MotorcycleSpec,
    reminders: List<ServiceReminderItem>,
    stages: List<StageInfo>,
    learningLevel: LearningLevel,
    onNavigateToSpecs: () -> Unit,
    onNavigateToStages: () -> Unit,
    onNavigateToParts: () -> Unit,
    onNavigateToMaintenance: () -> Unit,
    onNavigateToTroubleshooting: () -> Unit,
    onNavigateToWarningSigns: () -> Unit,
    onNavigateToHealthCheck: () -> Unit,
    onNavigateToChecklists: () -> Unit,
    onNavigateToCareMods: () -> Unit,
    onNavigateToAssistantWithPrompt: (String) -> Unit,
    onQuickUpdateKm: (Int) -> Unit
) {
    var showQuickKmDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CarbonBlack),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 1. Motorcycle Hero Card
        item {
            MotorcycleHeroCard(
                motorcycle = motorcycle,
                spec = spec,
                onSpecsClick = onNavigateToSpecs,
                onUpdateKmClick = { showQuickKmDialog = true }
            )
        }

        // 2. Critical Safety & Manufacturer Schedule Banner
        item {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = RacingAmber.copy(alpha = 0.12f),
                border = androidx.compose.foundation.BorderStroke(1.dp, RacingAmber.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = null,
                        tint = RacingAmber,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "OFFICIAL SERVICE DISPATCH",
                            color = RacingAmber,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Always follow the motorcycle manufacturer's official service schedule in your owner's manual.",
                            color = TitaniumSilver,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        // 3. Quick Action Hub
        item {
            Column {
                Text(
                    text = "ESSENTIAL RIDER TOOLS",
                    color = MutedSlate,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickToolButton(
                        title = "Diagnosis",
                        subtitle = "Troubleshoot",
                        icon = Icons.Default.MedicalServices,
                        accentColor = TechCyan,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToTroubleshooting
                    )
                    QuickToolButton(
                        title = "Warning Signs",
                        subtitle = "13 Symptoms",
                        icon = Icons.Default.WarningAmber,
                        accentColor = StatusOverdue,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToWarningSigns
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickToolButton(
                        title = "Health Check",
                        subtitle = "Full Quiz",
                        icon = Icons.Default.Favorite,
                        accentColor = StatusGood,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToHealthCheck
                    )
                    QuickToolButton(
                        title = "Checklists",
                        subtitle = "Pre & Long-Ride",
                        icon = Icons.Default.Checklist,
                        accentColor = RacingAmber,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToChecklists
                    )
                    QuickToolButton(
                        title = "Care & Mods",
                        subtitle = "Wash & Safe Upgrades",
                        icon = Icons.Default.AutoFixHigh,
                        accentColor = Color(0xFFA855F7),
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToCareMods
                    )
                }
            }
        }

        // 4. Maintenance Overview & Alerts
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "MAINTENANCE REMINDERS",
                        color = MutedSlate,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                    Text(
                        text = "View Logbook",
                        color = RacingAmber,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable(onClick = onNavigateToMaintenance)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                val urgentReminders = reminders.filter { it.status != ReminderStatus.GOOD }
                if (urgentReminders.isEmpty()) {
                    GlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        borderColor = StatusGood.copy(alpha = 0.4f)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = StatusGood,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "All Systems Within Interval",
                                    color = TitaniumSilver,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Next scheduled service in ${reminders.firstOrNull()?.dueKmRemaining ?: 1000} km.",
                                    color = MutedSlate,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        urgentReminders.forEach { reminder ->
                            ReminderCard(reminder = reminder, onClick = onNavigateToMaintenance)
                        }
                    }
                }
            }
        }

        // 5. Motorcycle Life Stages Preview
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "MOTORCYCLE LIFE & STAGES",
                        color = MutedSlate,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                    Text(
                        text = "All 9 Stages",
                        color = TechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable(onClick = onNavigateToStages)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(stages) { stage ->
                        StagePreviewCard(stage = stage, onClick = onNavigateToStages)
                    }
                }
            }
        }

        // 6. AI Bike Assistant Prompt Chips
        item {
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                borderColor = TechCyan.copy(alpha = 0.3f)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                tint = TechCyan,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "BIKE ASSISTANT AI",
                                color = TechCyan,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                letterSpacing = 1.sp
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = TechCyan.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "Smart Tech",
                                color = TechCyan,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Have a question about symptoms, break-in, or maintenance? Tap an inquiry to ask:",
                        color = MutedSlate,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    val promptChips = listOf(
                        "Why is my motorcycle making this noise?",
                        "What does this warning light mean?",
                        "What should I check before a long ride?",
                        "Explain the clutch.",
                        "What is chain maintenance?"
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        promptChips.forEach { chipPrompt ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = GunmetalSurface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onNavigateToAssistantWithPrompt(chipPrompt) }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = chipPrompt,
                                        color = TitaniumSilver,
                                        fontSize = 12.sp
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = null,
                                        tint = TechCyan,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Quick Update Mileage Dialog
    if (showQuickKmDialog) {
        var kmText by remember { mutableStateOf((motorcycle?.currentKm ?: 1250).toString()) }
        AlertDialog(
            onDismissRequest = { showQuickKmDialog = false },
            containerColor = CharcoalDark,
            title = {
                Text(
                    text = "Update Current Odometer",
                    color = TitaniumSilver,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = "Enter current total kilometers:",
                        color = MutedSlate,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = kmText,
                        onValueChange = { kmText = it.filter { ch -> ch.isDigit() } },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RacingAmber,
                            unfocusedBorderColor = GunmetalBorder,
                            focusedTextColor = TitaniumSilver,
                            unfocusedTextColor = TitaniumSilver
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val parsed = kmText.toIntOrNull()
                        if (parsed != null && parsed >= 0) {
                            onQuickUpdateKm(parsed)
                        }
                        showQuickKmDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RacingAmber)
                ) {
                    Text("Save", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showQuickKmDialog = false }) {
                    Text("Cancel", color = MutedSlate)
                }
            }
        )
    }
}

@Composable
private fun MotorcycleHeroCard(
    motorcycle: UserMotorcycleEntity?,
    spec: MotorcycleSpec,
    onSpecsClick: () -> Unit,
    onUpdateKmClick: () -> Unit
) {
    val bikeName = motorcycle?.nickname?.takeIf { it.isNotBlank() } ?: "${spec.brand} ${spec.model}"
    val currentKm = motorcycle?.currentKm ?: 1250
    val lastServiceKm = motorcycle?.lastServiceKm ?: 1000

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = RacingAmber.copy(alpha = 0.5f),
        backgroundColor = CharcoalDark
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = RacingAmber,
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text(
                                text = spec.brand.uppercase(),
                                color = Color.Black,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Text(
                            text = "${spec.modelYear} MODEL",
                            color = MutedSlate,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = bikeName,
                        color = TitaniumSilver,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = "${spec.engineDisplacement} • ${spec.category}",
                        color = TechCyan,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                IconButton(
                    onClick = onSpecsClick,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(GunmetalSurface)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Specs",
                        tint = TitaniumSilver
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Metrics row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(GunmetalSurface)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "ODOMETER", color = MutedSlate, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "$currentKm km",
                        color = TitaniumSilver,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                VerticalDivider(modifier = Modifier.height(28.dp), color = GunmetalBorder)

                Column {
                    Text(text = "LAST SERVICE", color = MutedSlate, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "$lastServiceKm km",
                        color = TitaniumSilver,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                VerticalDivider(modifier = Modifier.height(28.dp), color = GunmetalBorder)

                Button(
                    onClick = onUpdateKmClick,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RacingAmber.copy(alpha = 0.2f)),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, RacingAmber)
                ) {
                    Text(text = "+ Edit KM", color = RacingAmber, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun QuickToolButton(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        color = GunmetalSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = accentColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                color = TitaniumSilver,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            Text(
                text = subtitle,
                color = MutedSlate,
                fontSize = 10.sp,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun ReminderCard(
    reminder: ServiceReminderItem,
    onClick: () -> Unit
) {
    val (statusColor, statusText) = when (reminder.status) {
        ReminderStatus.OVERDUE -> Pair(StatusOverdue, "OVERDUE")
        ReminderStatus.DUE_SOON -> Pair(StatusDueSoon, "DUE SOON")
        ReminderStatus.GOOD -> Pair(StatusGood, "OK")
    }

    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        borderColor = statusColor.copy(alpha = 0.6f)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(statusColor)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = reminder.name,
                        color = TitaniumSilver,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Text(
                        text = if (reminder.dueKmRemaining <= 0)
                            "Overdue by ${-reminder.dueKmRemaining} km"
                        else
                            "Due in ${reminder.dueKmRemaining} km",
                        color = statusColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = statusColor.copy(alpha = 0.15f),
                border = androidx.compose.foundation.BorderStroke(1.dp, statusColor)
            ) {
                Text(
                    text = statusText,
                    color = statusColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
private fun StagePreviewCard(
    stage: StageInfo,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = GunmetalSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
        modifier = Modifier.width(180.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = RacingAmber.copy(alpha = 0.15f)
            ) {
                Text(
                    text = "STAGE ${stage.stageNumber}",
                    color = RacingAmber,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stage.title.substringAfter("— ").trim(),
                color = TitaniumSilver,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stage.subtitle,
                color = MutedSlate,
                fontSize = 10.sp,
                maxLines = 2,
                lineHeight = 14.sp
            )
        }
    }
}
