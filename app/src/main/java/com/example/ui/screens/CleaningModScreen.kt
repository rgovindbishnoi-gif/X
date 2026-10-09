package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CareGuideItem
import com.example.data.model.ModificationItem
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun CleaningModScreen(
    careGuides: List<CareGuideItem>,
    modifications: List<ModificationItem>
) {
    var selectedSection by remember { mutableIntStateOf(0) } // 0: Care Guides, 1: Modifications
    var expandedGuideId by remember { mutableStateOf<String?>(careGuides.firstOrNull()?.id) }
    var expandedModId by remember { mutableStateOf<String?>(modifications.firstOrNull()?.id) }

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
                    text = "MAINTENANCE CRAFT & UPGRADES",
                    color = RacingAmber,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Cleaning, Detailing & Mods",
                    color = TitaniumSilver,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Pro wash and chain care techniques, plus objective evaluations of aftermarket exhausts, suspension, and brakes.",
                    color = MutedSlate,
                    fontSize = 13.sp
                )
            }
        }

        // Section Tabs
        item {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = GunmetalSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(4.dp)) {
                    Surface(
                        onClick = { selectedSection = 0 },
                        shape = RoundedCornerShape(8.dp),
                        color = if (selectedSection == 0) RacingAmber else Color.Transparent,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(
                                text = "Cleaning & Care (${careGuides.size})",
                                color = if (selectedSection == 0) Color.Black else TitaniumSilver,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Surface(
                        onClick = { selectedSection = 1 },
                        shape = RoundedCornerShape(8.dp),
                        color = if (selectedSection == 1) RacingAmber else Color.Transparent,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(
                                text = "Modifications (${modifications.size})",
                                color = if (selectedSection == 1) Color.Black else TitaniumSilver,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        if (selectedSection == 0) {
            // CARE GUIDES
            items(careGuides) { guide ->
                val isExpanded = expandedGuideId == guide.id

                GlassCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedGuideId = if (isExpanded) null else guide.id },
                    borderColor = if (isExpanded) RacingAmber.copy(alpha = 0.5f) else GunmetalBorder
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = guide.title,
                                    color = TitaniumSilver,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Text(
                                    text = guide.category,
                                    color = TechCyan,
                                    fontSize = 11.sp
                                )
                            }
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = if (isExpanded) RacingAmber else TitaniumSilver,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = guide.summary,
                            color = MutedSlate,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )

                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.padding(top = 12.dp)) {
                                HorizontalDivider(color = GunmetalBorder)
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "STEP-BY-STEP PROCEDURE:",
                                    color = RacingAmber,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                guide.steps.forEachIndexed { idx, step ->
                                    Row(
                                        modifier = Modifier.padding(vertical = 3.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Text(
                                            text = "${idx + 1}. ",
                                            color = RacingAmber,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = step,
                                            color = TitaniumSilver,
                                            fontSize = 12.sp,
                                            lineHeight = 16.sp
                                        )
                                    }
                                }

                                if (guide.warnings.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "CRITICAL SAFETY WARNINGS:",
                                        color = StatusOverdue,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 1.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    guide.warnings.forEach { warn ->
                                        Row(
                                            modifier = Modifier.padding(vertical = 2.dp),
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Text(text = "⚠️ ", fontSize = 11.sp)
                                            Text(text = warn, color = StatusOverdue, fontSize = 12.sp, lineHeight = 16.sp)
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "RECOMMENDED TOOLS & SUPPLIES:",
                                    color = TechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = guide.recommendedTools.joinToString(" • "),
                                    color = TitaniumSilver,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }
        } else {
            // MODIFICATIONS
            items(modifications) { mod ->
                val isExpanded = expandedModId == mod.id

                GlassCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedModId = if (isExpanded) null else mod.id },
                    borderColor = if (isExpanded) TechCyan.copy(alpha = 0.5f) else GunmetalBorder
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = mod.title,
                                    color = TitaniumSilver,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Text(
                                    text = "System: ${mod.category}",
                                    color = TechCyan,
                                    fontSize = 11.sp
                                )
                            }
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = if (isExpanded) TechCyan else TitaniumSilver,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = mod.purpose,
                            color = MutedSlate,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )

                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.padding(top = 12.dp)) {
                                HorizontalDivider(color = GunmetalBorder)
                                Spacer(modifier = Modifier.height(10.dp))

                                // Potential Benefits
                                Text(
                                    text = "POTENTIAL BENEFITS:",
                                    color = StatusGood,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                                mod.potentialBenefits.forEach { ben ->
                                    Text(text = "✓ $ben", color = TitaniumSilver, fontSize = 12.sp, modifier = Modifier.padding(vertical = 1.dp))
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Possible Drawbacks
                                Text(
                                    text = "POSSIBLE DRAWBACKS:",
                                    color = StatusDueSoon,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                                mod.possibleDrawbacks.forEach { draw ->
                                    Text(text = "✕ $draw", color = TitaniumSilver, fontSize = 12.sp, modifier = Modifier.padding(vertical = 1.dp))
                                }

                                Spacer(modifier = Modifier.height(10.dp))
                                Text(text = "COMPATIBILITY:", color = TechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                Text(text = mod.compatibilityNotes, color = TitaniumSilver, fontSize = 12.sp)

                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "SAFETY CONSIDERATIONS:", color = RacingAmber, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                Text(text = mod.safetyConsiderations, color = TitaniumSilver, fontSize = 12.sp)

                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "LEGAL & REGULATORY:", color = MutedSlate, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                Text(text = mod.legalRegulatoryNotes, color = TitaniumSilver, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
