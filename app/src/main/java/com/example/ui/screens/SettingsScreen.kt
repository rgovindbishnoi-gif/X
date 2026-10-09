package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.data.model.LearningLevel
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun SettingsScreen(
    currentLevel: LearningLevel,
    onLevelChanged: (LearningLevel) -> Unit,
    onNavigateToAdmin: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CarbonBlack),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "APPLICATION SETTINGS",
                    color = RacingAmber,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Preferences & System",
                    color = TitaniumSilver,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        // 1. Learning Level
        item {
            GlassCard(modifier = Modifier.fillMaxWidth(), borderColor = GunmetalBorder) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "TECHNICAL LEARNING DEPTH",
                        color = TechCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Select how technical and comprehensive you want component descriptions and explanations to be.",
                        color = MutedSlate,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    LearningLevel.entries.forEach { level ->
                        val isSelected = currentLevel == level
                        Surface(
                            onClick = { onLevelChanged(level) },
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) RacingAmber.copy(alpha = 0.15f) else CharcoalDark,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) RacingAmber else GunmetalBorder
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = { onLevelChanged(level) },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = RacingAmber,
                                        unselectedColor = MutedSlate
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = level.name,
                                        color = if (isSelected) TitaniumSilver else MutedSlate,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                    Text(
                                        text = when (level) {
                                            LearningLevel.BEGINNER -> "Simple, jargon-free analogies and straightforward safety tips."
                                            LearningLevel.INTERMEDIATE -> "Mechanical terminology, operating principles, and standard service steps."
                                            LearningLevel.ADVANCED -> "Deep engineering details, metallurgy, hydraulic mechanics, and tolerances."
                                        },
                                        color = MutedSlate,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 2. Admin Portal Access
        item {
            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onNavigateToAdmin),
                borderColor = GunmetalBorder
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.AdminPanelSettings, contentDescription = null, tint = StatusDueSoon, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = "Technician / Admin Portal", color = TitaniumSilver, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(text = "Manage models, recall bulletins, and schedules", color = MutedSlate, fontSize = 11.sp)
                        }
                    }
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = TitaniumSilver)
                }
            }
        }

        // 3. Safety Notice & Official Manual Reminder
        item {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = GunmetalSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Security, contentDescription = null, tint = RacingAmber, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "MOTORCYCLE SAFETY CHARTER", color = RacingAmber, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 1.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "1. Always refer to your motorcycle's official factory owner's manual for torque specifications, exact fluid capacities, and tyre pressures.\n2. Do NOT attempt dangerous braking, internal engine, or electrical repairs without proper certified equipment and qualifications.\n3. Always wear certified safety gear: full-face helmet, armored jacket, riding pants, gloves, and sturdy boots.",
                        color = MutedSlate,
                        fontSize = 11.sp,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}
