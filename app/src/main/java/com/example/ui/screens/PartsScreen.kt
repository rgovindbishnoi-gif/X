package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.data.model.ComponentSystem
import com.example.data.model.LearningLevel
import com.example.data.model.MotorcyclePart
import com.example.ui.components.GlassCard
import com.example.ui.components.MotorcycleSchematicView
import com.example.ui.theme.*

@Composable
fun PartsScreen(
    parts: List<MotorcyclePart>,
    currentLearningLevel: LearningLevel,
    onLearningLevelChanged: (LearningLevel) -> Unit
) {
    var selectedSystem by remember { mutableStateOf(ComponentSystem.ENGINE) }
    var inspectingPart by remember { mutableStateOf<MotorcyclePart?>(null) }

    val filteredParts = remember(selectedSystem, parts) {
        parts.filter { it.system == selectedSystem }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CarbonBlack),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Title & Learning Level Selector
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "MOTORCYCLE PARTS EXPLORER",
                        color = RacingAmber,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                    Text(
                        text = "Interactive Anatomy",
                        color = TitaniumSilver,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Learning Level Switcher
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = GunmetalSurface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder)
                ) {
                    Row(modifier = Modifier.padding(4.dp)) {
                        LearningLevel.entries.forEach { level ->
                            val isSelected = currentLearningLevel == level
                            Surface(
                                onClick = { onLearningLevelChanged(level) },
                                shape = RoundedCornerShape(6.dp),
                                color = if (isSelected) RacingAmber else Color.Transparent
                            ) {
                                Text(
                                    text = level.name.take(3),
                                    color = if (isSelected) Color.Black else MutedSlate,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // 2. Interactive Canvas Blueprint
        item {
            MotorcycleSchematicView(
                selectedSystem = selectedSystem,
                onSystemSelected = { selectedSystem = it }
            )
        }

        // 3. System Filter Horizontal Tabs
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(ComponentSystem.entries) { sys ->
                    val isSelected = selectedSystem == sys
                    Surface(
                        onClick = { selectedSystem = sys },
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) RacingAmber.copy(alpha = 0.2f) else GunmetalSurface,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isSelected) RacingAmber else GunmetalBorder
                        )
                    ) {
                        Text(
                            text = sys.title,
                            color = if (isSelected) RacingAmber else TitaniumSilver,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        // 4. Component List for Active System
        item {
            Text(
                text = "${selectedSystem.title.uppercase()} COMPONENTS (${filteredParts.size})",
                color = MutedSlate,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }

        if (filteredParts.isEmpty()) {
            item {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "More detailed component specs coming for this category.",
                        color = MutedSlate,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }
            }
        } else {
            items(filteredParts) { part ->
                PartCard(
                    part = part,
                    learningLevel = currentLearningLevel,
                    onClick = { inspectingPart = part }
                )
            }
        }
    }

    // Modal Inspection Sheet
    inspectingPart?.let { part ->
        PartInspectionDialog(
            part = part,
            learningLevel = currentLearningLevel,
            onDismiss = { inspectingPart = null }
        )
    }
}

@Composable
private fun PartCard(
    part: MotorcyclePart,
    learningLevel: LearningLevel,
    onClick: () -> Unit
) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        borderColor = GunmetalBorder
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = part.name,
                        color = TitaniumSilver,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = part.location,
                        color = TechCyan,
                        fontSize = 11.sp
                    )
                }
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = RacingAmber,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            val explanation = when (learningLevel) {
                LearningLevel.BEGINNER -> part.beginnerExplanation
                LearningLevel.INTERMEDIATE -> part.intermediateExplanation
                LearningLevel.ADVANCED -> part.advancedExplanation
            }

            Text(
                text = explanation,
                color = TitaniumSilver.copy(alpha = 0.85f),
                fontSize = 12.sp,
                lineHeight = 17.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = GunmetalSurface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder)
                ) {
                    Text(
                        text = "Tap to Inspect Full Tech Specs",
                        color = RacingAmber,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PartInspectionDialog(
    part: MotorcyclePart,
    learningLevel: LearningLevel,
    onDismiss: () -> Unit
) {
    var dialogLevel by remember { mutableStateOf(learningLevel) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = CharcoalDark,
        title = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = part.system.title.uppercase(),
                        color = TechCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp
                    )
                    IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = TitaniumSilver)
                    }
                }
                Text(
                    text = part.name,
                    color = TitaniumSilver,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "📍 Location: ${part.location}",
                    color = MutedSlate,
                    fontSize = 11.sp
                )
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Learning Level Selector within dialog
                item {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = GunmetalSurface,
                        border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            LearningLevel.entries.forEach { lvl ->
                                val active = dialogLevel == lvl
                                Surface(
                                    onClick = { dialogLevel = lvl },
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (active) RacingAmber else Color.Transparent,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = lvl.name,
                                            color = if (active) Color.Black else MutedSlate,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // 1. Function
                item {
                    DetailSection(title = "FUNCTION", content = part.function, icon = Icons.Default.ElectricBolt)
                }

                // 2. Explanation by selected level
                item {
                    val exp = when (dialogLevel) {
                        LearningLevel.BEGINNER -> part.beginnerExplanation
                        LearningLevel.INTERMEDIATE -> part.intermediateExplanation
                        LearningLevel.ADVANCED -> part.advancedExplanation
                    }
                    DetailSection(title = "${dialogLevel.name} EXPLANATION", content = exp, icon = Icons.Default.MenuBook)
                }

                // 3. Maintenance needed
                item {
                    DetailSection(title = "MAINTENANCE NEEDED", content = part.maintenance, icon = Icons.Default.Build)
                }

                // 4. Common problems
                item {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.ReportProblem, contentDescription = null, tint = StatusDueSoon, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "COMMON PROBLEMS", color = StatusDueSoon, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        part.commonProblems.forEach { prob ->
                            Text(text = "• $prob", color = TitaniumSilver, fontSize = 12.sp, lineHeight = 16.sp, modifier = Modifier.padding(vertical = 2.dp))
                        }
                    }
                }

                // 5. Warning signs
                item {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = StatusOverdue, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "WARNING SIGNS", color = StatusOverdue, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        part.warningSigns.forEach { sign ->
                            Text(text = "⚠️ $sign", color = TitaniumSilver, fontSize = 12.sp, lineHeight = 16.sp, modifier = Modifier.padding(vertical = 2.dp))
                        }
                    }
                }

                // 6. Replacement considerations
                item {
                    DetailSection(title = "REPLACEMENT CONSIDERATIONS", content = part.replacementConsiderations, icon = Icons.Default.Autorenew)
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = RacingAmber)
            ) {
                Text("Done", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    )
}

@Composable
private fun DetailSection(
    title: String,
    content: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = TechCyan, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = title, color = TechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            color = TitaniumSilver,
            fontSize = 12.sp,
            lineHeight = 17.sp
        )
    }
}
