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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.TroubleshootingCategory
import com.example.data.model.TroubleshootingStep
import com.example.data.repository.DemoDataProvider
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun TroubleshootingScreen(
    categories: List<TroubleshootingCategory>,
    steps: Map<String, TroubleshootingStep>
) {
    var activeCategory by remember { mutableStateOf<TroubleshootingCategory?>(null) }
    var currentStepId by remember { mutableStateOf<String?>(null) }
    var historyPath by remember { mutableStateOf(listOf<String>()) }

    val currentStep = currentStepId?.let { steps[it] }

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
                    text = "INTERACTIVE DIAGNOSTIC WIZARD",
                    color = RacingAmber,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (activeCategory == null) "Motorcycle Troubleshooting" else activeCategory!!.title,
                    color = TitaniumSilver,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Step-by-step diagnostic tree to isolate electrical, starting, braking, and engine symptoms.",
                    color = MutedSlate,
                    fontSize = 13.sp
                )
            }
        }

        // Mandatory Technician Warning Banner
        item {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = StatusOverdue.copy(alpha = 0.12f),
                border = androidx.compose.foundation.BorderStroke(1.dp, StatusOverdue.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = StatusOverdue,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "CRITICAL NOTICE: For serious mechanical, electrical or fuel-system problems, consult a qualified motorcycle technician.",
                        color = TitaniumSilver,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        if (activeCategory == null) {
            // Category Selection List
            item {
                Text(
                    text = "SELECT SYMPTOM CATEGORY",
                    color = MutedSlate,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            items(categories) { category ->
                GlassCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            activeCategory = category
                            currentStepId = category.rootStepId
                            historyPath = listOf(category.rootStepId)
                        },
                    borderColor = GunmetalBorder
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = RacingAmber.copy(alpha = 0.15f),
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Build,
                                    contentDescription = null,
                                    tint = RacingAmber,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = category.title,
                                color = TitaniumSilver,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = category.description,
                                color = MutedSlate,
                                fontSize = 12.sp
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = TechCyan,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        } else {
            // Active Step in Wizard
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            activeCategory = null
                            currentStepId = null
                            historyPath = emptyList()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = GunmetalSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = TitaniumSilver, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("All Categories", color = TitaniumSilver, fontSize = 12.sp)
                    }

                    Text(
                        text = "Step ${historyPath.size}",
                        color = TechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (currentStep != null) {
                if (currentStep.conclusionTitle != null) {
                    // Conclusion / Resolution Found!
                    item {
                        GlassCard(
                            modifier = Modifier.fillMaxWidth(),
                            borderColor = if (currentStep.isHazardous) StatusOverdue else StatusGood
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = if (currentStep.isHazardous) Icons.Default.ReportProblem else Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = if (currentStep.isHazardous) StatusOverdue else StatusGood,
                                        modifier = Modifier.size(28.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = currentStep.conclusionTitle,
                                        color = if (currentStep.isHazardous) StatusOverdue else StatusGood,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                Text(
                                    text = currentStep.conclusionAdvice.orEmpty(),
                                    color = TitaniumSilver,
                                    fontSize = 13.sp,
                                    lineHeight = 19.sp
                                )

                                Spacer(modifier = Modifier.height(18.dp))

                                Button(
                                    onClick = {
                                        currentStepId = activeCategory?.rootStepId
                                        historyPath = listOf(activeCategory?.rootStepId ?: "")
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = RacingAmber),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(imageVector = Icons.Default.RestartAlt, contentDescription = null, tint = Color.Black)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Restart Diagnostic", color = Color.Black, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                } else {
                    // Question Card with YES / NO buttons
                    item {
                        GlassCard(
                            modifier = Modifier.fillMaxWidth(),
                            borderColor = TechCyan.copy(alpha = 0.5f)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = TechCyan.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = "DIAGNOSTIC QUESTION",
                                        color = TechCyan,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                Text(
                                    text = currentStep.question,
                                    color = TitaniumSilver,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 24.sp
                                )

                                if (currentStep.contextInfo.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "💡 ${currentStep.contextInfo}",
                                        color = MutedSlate,
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp
                                    )
                                }

                                Spacer(modifier = Modifier.height(24.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                                ) {
                                    Button(
                                        onClick = {
                                            currentStep.yesTargetId?.let { nextId ->
                                                currentStepId = nextId
                                                historyPath = historyPath + nextId
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = StatusGood),
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(48.dp)
                                    ) {
                                        Text("YES", color = Color.Black, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
                                    }

                                    Button(
                                        onClick = {
                                            currentStep.noTargetId?.let { nextId ->
                                                currentStepId = nextId
                                                historyPath = historyPath + nextId
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = StatusOverdue),
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(48.dp)
                                    ) {
                                        Text("NO", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
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
