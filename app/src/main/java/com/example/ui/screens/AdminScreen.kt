package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CustomGuideEntity
import com.example.data.model.MotorcycleSpec
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun AdminScreen(
    customGuides: List<CustomGuideEntity>,
    allSpecs: List<MotorcycleSpec>,
    onAddCustomGuide: (CustomGuideEntity) -> Unit,
    onDeleteCustomGuide: (Long) -> Unit
) {
    var isAuthenticated by remember { mutableStateOf(false) }
    var pinInput by remember { mutableStateOf("") }
    var pinError by remember { mutableStateOf(false) }

    var showAddGuideDialog by remember { mutableStateOf(false) }

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
                    text = "CONTENT MANAGEMENT PORTAL",
                    color = StatusDueSoon,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Admin Dashboard",
                    color = TitaniumSilver,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Restricted technician and fleet management console.",
                    color = MutedSlate,
                    fontSize = 13.sp
                )
            }
        }

        if (!isAuthenticated) {
            // PIN Authentication Gate
            item {
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    borderColor = StatusDueSoon.copy(alpha = 0.5f)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = StatusDueSoon,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Admin Passcode Required",
                            color = TitaniumSilver,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Enter secure PIN (Default: 1234)",
                            color = MutedSlate,
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = pinInput,
                            onValueChange = {
                                pinInput = it.filter { c -> c.isDigit() }.take(6)
                                pinError = false
                            },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            label = { Text("Security PIN") },
                            isError = pinError,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = StatusDueSoon,
                                unfocusedBorderColor = GunmetalBorder,
                                focusedTextColor = TitaniumSilver,
                                unfocusedTextColor = TitaniumSilver
                            ),
                            modifier = Modifier.width(200.dp)
                        )

                        if (pinError) {
                            Text(
                                text = "Incorrect Passcode. Try 1234.",
                                color = StatusOverdue,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                if (pinInput == "1234" || pinInput == "0000") {
                                    isAuthenticated = true
                                } else {
                                    pinError = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = StatusDueSoon),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Unlock Admin Portal", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            // AUTHENTICATED ADMIN CONSOLE
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = StatusGood.copy(alpha = 0.15f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, StatusGood)
                    ) {
                        Text(
                            text = "SESSION: AUTHENTICATED ADMINISTRATOR",
                            color = StatusGood,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    TextButton(onClick = { isAuthenticated = false; pinInput = "" }) {
                        Text("Log Out", color = StatusOverdue, fontSize = 12.sp)
                    }
                }
            }

            // System Statistics
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AdminStatCard(title = "Models", count = "${allSpecs.size}", color = TechCyan, modifier = Modifier.weight(1f))
                    AdminStatCard(title = "Custom Guides", count = "${customGuides.size}", color = RacingAmber, modifier = Modifier.weight(1f))
                    AdminStatCard(title = "System Status", count = "100%", color = StatusGood, modifier = Modifier.weight(1f))
                }
            }

            // Custom Technical Guides Management
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "PUBLISHED TECHNICAL BULLETINS",
                        color = MutedSlate,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Button(
                        onClick = { showAddGuideDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = RacingAmber),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.Black, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("+ New Bulletin", color = Color.Black, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            if (customGuides.isEmpty()) {
                item {
                    GlassCard(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "No custom service bulletins published yet. Click '+ New Bulletin' to broadcast new service recall or DIY notes.",
                            color = MutedSlate,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            } else {
                items(customGuides) { guide ->
                    GlassCard(modifier = Modifier.fillMaxWidth(), borderColor = GunmetalBorder) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = guide.title, color = TitaniumSilver, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text(text = "Category: ${guide.category} • By ${guide.author}", color = TechCyan, fontSize = 11.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = guide.content, color = MutedSlate, fontSize = 11.sp, maxLines = 2)
                            }
                            IconButton(onClick = { onDeleteCustomGuide(guide.id) }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = StatusOverdue)
                            }
                        }
                    }
                }
            }
        }
    }

    // Add Guide Dialog
    if (showAddGuideDialog) {
        var titleText by remember { mutableStateOf("") }
        var categoryText by remember { mutableStateOf("Maintenance") }
        var contentText by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddGuideDialog = false },
            containerColor = CharcoalDark,
            title = { Text("Publish Service Bulletin", color = TitaniumSilver, fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = titleText,
                        onValueChange = { titleText = it },
                        label = { Text("Bulletin Title") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RacingAmber,
                            unfocusedBorderColor = GunmetalBorder,
                            focusedTextColor = TitaniumSilver,
                            unfocusedTextColor = TitaniumSilver
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = categoryText,
                        onValueChange = { categoryText = it },
                        label = { Text("Category") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RacingAmber,
                            unfocusedBorderColor = GunmetalBorder,
                            focusedTextColor = TitaniumSilver,
                            unfocusedTextColor = TitaniumSilver
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = contentText,
                        onValueChange = { contentText = it },
                        label = { Text("Detailed Bulletin / Advisory") },
                        maxLines = 4,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RacingAmber,
                            unfocusedBorderColor = GunmetalBorder,
                            focusedTextColor = TitaniumSilver,
                            unfocusedTextColor = TitaniumSilver
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (titleText.isNotBlank() && contentText.isNotBlank()) {
                            onAddCustomGuide(
                                CustomGuideEntity(
                                    title = titleText.trim(),
                                    category = categoryText.trim(),
                                    content = contentText.trim(),
                                    author = "Head Technician",
                                    isPublished = true
                                )
                            )
                            showAddGuideDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RacingAmber)
                ) {
                    Text("Publish", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddGuideDialog = false }) {
                    Text("Cancel", color = MutedSlate)
                }
            }
        )
    }
}

@Composable
private fun AdminStatCard(title: String, count: String, color: Color, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = GunmetalSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = count, color = color, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = title, color = MutedSlate, fontSize = 10.sp)
        }
    }
}
