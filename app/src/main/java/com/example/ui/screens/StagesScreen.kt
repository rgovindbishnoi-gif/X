package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.data.model.StageInfo
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun StagesScreen(
    stages: List<StageInfo>
) {
    var expandedStageNumber by remember { mutableIntStateOf(1) }

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
                    text = "MOTORCYCLE LIFE & MAINTENANCE STAGES",
                    color = RacingAmber,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ownership Lifecycle Timeline",
                    color = TitaniumSilver,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "From showroom delivery and engine break-in to periodic maintenance, long-term preservation, and full rebuilds.",
                    color = MutedSlate,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
        }

        // Timeline Items
        itemsIndexed(stages) { index, stage ->
            val isExpanded = expandedStageNumber == stage.stageNumber
            val isLast = index == stages.size - 1

            Row(modifier = Modifier.fillMaxWidth()) {
                // Visual Timeline indicator
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(36.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(if (isExpanded) RacingAmber else GunmetalSurface)
                            .border(
                                2.dp,
                                if (isExpanded) RacingAmberLight else GunmetalBorder,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${stage.stageNumber}",
                            color = if (isExpanded) Color.Black else TitaniumSilver,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp
                        )
                    }

                    if (!isLast) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(if (isExpanded) 280.dp else 90.dp)
                                .background(GunmetalBorder)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Stage Content Card
                GlassCard(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            expandedStageNumber = if (isExpanded) -1 else stage.stageNumber
                        },
                    borderColor = if (isExpanded) RacingAmber.copy(alpha = 0.6f) else GunmetalBorder
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = stage.title,
                                    color = if (isExpanded) RacingAmber else TitaniumSilver,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = stage.subtitle,
                                    color = MutedSlate,
                                    fontSize = 12.sp
                                )
                            }
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = if (isExpanded) RacingAmber else MutedSlate,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Badges
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = if (stage.diyFriendly) StatusGood.copy(alpha = 0.15f) else StatusOverdue.copy(alpha = 0.15f),
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (stage.diyFriendly) StatusGood else StatusOverdue
                                )
                            ) {
                                Text(
                                    text = if (stage.diyFriendly) "DIY Friendly" else "Workshop / Specialist Job",
                                    color = if (stage.diyFriendly) StatusGood else StatusOverdue,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        // Expanded Details
                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.padding(top = 14.dp)) {
                                Text(
                                    text = "KEY CHECKLIST & PROCEDURES:",
                                    color = TechCyan,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    letterSpacing = 1.sp
                                )
                                Spacer(modifier = Modifier.height(6.dp))

                                stage.keyItems.forEach { item ->
                                    Row(
                                        modifier = Modifier.padding(vertical = 3.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = RacingAmber,
                                            modifier = Modifier
                                                .size(14.dp)
                                                .padding(top = 2.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = item,
                                            color = TitaniumSilver,
                                            fontSize = 12.sp,
                                            lineHeight = 16.sp
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))
                                HorizontalDivider(color = GunmetalBorder)
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "TECHNICAL BREAKDOWN:",
                                    color = TechCyan,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    letterSpacing = 1.sp
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = stage.fullExplanation,
                                    color = TitaniumSilver.copy(alpha = 0.9f),
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp
                                )

                                Spacer(modifier = Modifier.height(12.dp))
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = CharcoalDark,
                                    border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Info,
                                            contentDescription = null,
                                            tint = RacingAmber,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = stage.manufacturerNotice,
                                            color = MutedSlate,
                                            fontSize = 11.sp,
                                            lineHeight = 15.sp
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
}
