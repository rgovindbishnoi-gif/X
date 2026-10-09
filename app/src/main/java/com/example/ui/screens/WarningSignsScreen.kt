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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.WarningSeverity
import com.example.data.model.WarningSignItem
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun WarningSignsScreen(
    warningSigns: List<WarningSignItem>
) {
    var selectedCategory by remember { mutableStateOf("All") }
    var expandedSignId by remember { mutableStateOf<String?>(null) }

    val categories = remember(warningSigns) {
        listOf("All") + warningSigns.map { it.category }.distinct()
    }

    val filteredSigns = remember(warningSigns, selectedCategory) {
        if (selectedCategory == "All") warningSigns else warningSigns.filter { it.category == selectedCategory }
    }

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
                    text = "SAFETY FIRST REFERENCE",
                    color = StatusOverdue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Warning Signs Library",
                    color = TitaniumSilver,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Know when it is safe to ride home vs when to stop the motorcycle immediately to prevent crashes or engine destruction.",
                    color = MutedSlate,
                    fontSize = 13.sp,
                    lineHeight = 17.sp
                )
            }
        }

        // Category Filter Chips
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(categories) { cat ->
                    val isSelected = selectedCategory == cat
                    Surface(
                        onClick = { selectedCategory = cat },
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSelected) RacingAmber else GunmetalSurface,
                        border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) RacingAmber else GunmetalBorder)
                    ) {
                        Text(
                            text = cat,
                            color = if (isSelected) Color.Black else TitaniumSilver,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }

        // Warning Signs List
        items(filteredSigns) { sign ->
            val isExpanded = expandedSignId == sign.id

            val (badgeColor, badgeText) = when (sign.severity) {
                WarningSeverity.CRITICAL -> Pair(StatusOverdue, "CRITICAL HAZARD")
                WarningSeverity.MEDIUM -> Pair(StatusDueSoon, "ATTENTION REQUIRED")
                WarningSeverity.LOW -> Pair(StatusGood, "LOW RISK")
            }

            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedSignId = if (isExpanded) null else sign.id },
                borderColor = if (sign.avoidRidingImmediately) StatusOverdue.copy(alpha = 0.5f) else GunmetalBorder
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = badgeColor.copy(alpha = 0.15f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, badgeColor)
                        ) {
                            Text(
                                text = badgeText,
                                color = badgeColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        if (sign.avoidRidingImmediately) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = StatusOverdue
                            ) {
                                Text(
                                    text = "STOP RIDING IMMEDIATELY",
                                    color = Color.White,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = sign.title,
                                color = TitaniumSilver,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Category: ${sign.category}",
                                color = TechCyan,
                                fontSize = 11.sp
                            )
                        }

                        Icon(
                            imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = TitaniumSilver,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Indicates: ${sign.whatItIndicates}",
                        color = MutedSlate,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    AnimatedVisibility(visible = isExpanded) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            HorizontalDivider(color = GunmetalBorder)
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "WHAT THE RIDER SHOULD INSPECT:",
                                color = RacingAmber,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            sign.whatRiderShouldCheck.forEach { check ->
                                Row(
                                    modifier = Modifier.padding(vertical = 2.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(text = "• ", color = RacingAmber, fontWeight = FontWeight.Bold)
                                    Text(text = check, color = TitaniumSilver, fontSize = 12.sp, lineHeight = 16.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "PROFESSIONAL INSPECTION RECOMMENDATION:",
                                color = TechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = sign.professionalInspectionRecommendation,
                                color = TitaniumSilver,
                                fontSize = 12.sp,
                                lineHeight = 17.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
