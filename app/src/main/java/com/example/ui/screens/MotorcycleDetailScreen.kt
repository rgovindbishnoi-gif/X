package com.example.ui.screens

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
import com.example.data.model.MotorcycleSpec
import com.example.data.model.UserMotorcycleEntity
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun MotorcycleDetailScreen(
    currentSpec: MotorcycleSpec,
    allSpecs: List<MotorcycleSpec>,
    userMotorcycle: UserMotorcycleEntity?,
    onSelectMotorcycle: (MotorcycleSpec) -> Unit,
    onSaveUserDetails: (nickname: String, year: Int, currentKm: Int, purchaseDate: String, lastServiceDate: String, lastServiceKm: Int) -> Unit
) {
    var showEditDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CarbonBlack),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // 1. Selector Bar for Realistic Demo Motorcycles
        item {
            Column {
                Text(
                    text = "SELECT MOTORCYCLE PROFILE",
                    color = MutedSlate,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(allSpecs) { spec ->
                        val isSelected = spec.id == currentSpec.id
                        Surface(
                            onClick = { onSelectMotorcycle(spec) },
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) RacingAmber.copy(alpha = 0.18f) else GunmetalSurface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) RacingAmber else GunmetalBorder
                            ),
                            modifier = Modifier.width(170.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = spec.brand,
                                        color = if (isSelected) RacingAmber else MutedSlate,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                    if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = RacingAmber,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = spec.model,
                                    color = TitaniumSilver,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    maxLines = 1
                                )
                                Text(
                                    text = "${spec.modelYear} • ${spec.engineDisplacement}",
                                    color = TechCyan,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // 2. Active Motorcycle Header & User Profile Info
        item {
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                borderColor = RacingAmber.copy(alpha = 0.4f)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
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
                                        text = "CURRENT BIKE",
                                        color = Color.Black,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                if (currentSpec.isDemoData) {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = GunmetalSurface,
                                        border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder)
                                    ) {
                                        Text(
                                            text = "Demo Verified",
                                            color = TechCyan,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = userMotorcycle?.nickname?.takeIf { it.isNotBlank() }
                                    ?: "${currentSpec.brand} ${currentSpec.model}",
                                color = TitaniumSilver,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${currentSpec.category} | Model Year ${userMotorcycle?.year ?: currentSpec.modelYear}",
                                color = MutedSlate,
                                fontSize = 12.sp
                            )
                        }

                        Button(
                            onClick = { showEditDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = RacingAmber.copy(alpha = 0.15f)),
                            border = androidx.compose.foundation.BorderStroke(1.dp, RacingAmber),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = RacingAmber,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Edit Details", color = RacingAmber, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // User Details Grid
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(CharcoalDark)
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("ODOMETER", color = MutedSlate, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            Text("${userMotorcycle?.currentKm ?: 1250} km", color = TitaniumSilver, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("PURCHASE DATE", color = MutedSlate, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            Text(userMotorcycle?.purchaseDate ?: "2024-03-15", color = TitaniumSilver, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("LAST SERVICED", color = MutedSlate, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            Text("${userMotorcycle?.lastServiceKm ?: 1000} km", color = TitaniumSilver, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // 3. Official Notice
        item {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = GunmetalSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = TechCyan,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Always follow the motorcycle manufacturer's official service schedule. Specifications shown below are verified factory reference numbers.",
                        color = TitaniumSilver,
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )
                }
            }
        }

        // 4. Full Specifications Categorized
        item {
            Text(
                text = "FACTORY TECHNICAL SPECIFICATIONS",
                color = MutedSlate,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp
            )
        }

        item {
            SpecCategoryCard(
                categoryTitle = "POWERTRAIN & ENGINE",
                icon = Icons.Default.Speed,
                items = listOf(
                    "Engine Type" to currentSpec.engineType,
                    "Displacement" to currentSpec.engineDisplacement,
                    "Transmission" to currentSpec.transmission,
                    "Fuel System" to currentSpec.fuelSystem,
                    "Cooling System" to currentSpec.coolingSystem,
                    "Oil Capacity & Spec" to currentSpec.oilCapacity
                )
            )
        }

        item {
            SpecCategoryCard(
                categoryTitle = "CHASSIS, BRAKES & SUSPENSION",
                icon = Icons.Default.Build,
                items = listOf(
                    "Front Brakes" to currentSpec.frontBrake,
                    "Rear Brakes" to currentSpec.rearBrake,
                    "Front Suspension" to currentSpec.frontSuspension,
                    "Rear Suspension" to currentSpec.rearSuspension,
                    "Tyre Sizes" to currentSpec.tyreSizes
                )
            )
        }

        item {
            SpecCategoryCard(
                categoryTitle = "DIMENSIONS & CAPACITIES",
                icon = Icons.Default.Straighten,
                items = listOf(
                    "Fuel Tank Capacity" to currentSpec.fuelCapacity,
                    "Wet Kerb Weight" to currentSpec.weight,
                    "Dimensions & Seat Height" to currentSpec.dimensions,
                    "Regular Service Interval" to "${currentSpec.serviceIntervalKm} km / 12 Months",
                    "Chain Maintenance Interval" to if (currentSpec.chainServiceIntervalKm > 0)
                        "${currentSpec.chainServiceIntervalKm} km" else "Shaft Drive (no chain)"
                )
            )
        }
    }

    // Edit Motorcycle Details Dialog
    if (showEditDialog) {
        var nicknameInput by remember { mutableStateOf(userMotorcycle?.nickname ?: "") }
        var yearInput by remember { mutableStateOf((userMotorcycle?.year ?: currentSpec.modelYear).toString()) }
        var kmInput by remember { mutableStateOf((userMotorcycle?.currentKm ?: 1250).toString()) }
        var purchaseDateInput by remember { mutableStateOf(userMotorcycle?.purchaseDate ?: "2024-03-15") }
        var lastServiceDateInput by remember { mutableStateOf(userMotorcycle?.lastServiceDate ?: "2024-06-10") }
        var lastServiceKmInput by remember { mutableStateOf((userMotorcycle?.lastServiceKm ?: 1000).toString()) }

        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            containerColor = CharcoalDark,
            title = {
                Text(
                    text = "Edit Motorcycle Profile",
                    color = TitaniumSilver,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        OutlinedTextField(
                            value = nicknameInput,
                            onValueChange = { nicknameInput = it },
                            label = { Text("Motorcycle Nickname") },
                            placeholder = { Text("${currentSpec.brand} ${currentSpec.model}") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RacingAmber,
                                unfocusedBorderColor = GunmetalBorder,
                                focusedTextColor = TitaniumSilver,
                                unfocusedTextColor = TitaniumSilver
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = kmInput,
                            onValueChange = { kmInput = it.filter { c -> c.isDigit() } },
                            label = { Text("Current Odometer (km)") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RacingAmber,
                                unfocusedBorderColor = GunmetalBorder,
                                focusedTextColor = TitaniumSilver,
                                unfocusedTextColor = TitaniumSilver
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = lastServiceKmInput,
                            onValueChange = { lastServiceKmInput = it.filter { c -> c.isDigit() } },
                            label = { Text("Last Service Odometer (km)") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RacingAmber,
                                unfocusedBorderColor = GunmetalBorder,
                                focusedTextColor = TitaniumSilver,
                                unfocusedTextColor = TitaniumSilver
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = yearInput,
                            onValueChange = { yearInput = it.filter { c -> c.isDigit() } },
                            label = { Text("Registration Year") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RacingAmber,
                                unfocusedBorderColor = GunmetalBorder,
                                focusedTextColor = TitaniumSilver,
                                unfocusedTextColor = TitaniumSilver
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = purchaseDateInput,
                            onValueChange = { purchaseDateInput = it },
                            label = { Text("Purchase Date (YYYY-MM-DD)") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RacingAmber,
                                unfocusedBorderColor = GunmetalBorder,
                                focusedTextColor = TitaniumSilver,
                                unfocusedTextColor = TitaniumSilver
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = lastServiceDateInput,
                            onValueChange = { lastServiceDateInput = it },
                            label = { Text("Last Service Date (YYYY-MM-DD)") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RacingAmber,
                                unfocusedBorderColor = GunmetalBorder,
                                focusedTextColor = TitaniumSilver,
                                unfocusedTextColor = TitaniumSilver
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSaveUserDetails(
                            nicknameInput.trim(),
                            yearInput.toIntOrNull() ?: currentSpec.modelYear,
                            kmInput.toIntOrNull() ?: 1250,
                            purchaseDateInput.trim(),
                            lastServiceDateInput.trim(),
                            lastServiceKmInput.toIntOrNull() ?: 1000
                        )
                        showEditDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RacingAmber)
                ) {
                    Text("Save Changes", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancel", color = MutedSlate)
                }
            }
        )
    }
}

@Composable
private fun SpecCategoryCard(
    categoryTitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    items: List<Pair<String, String>>
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = GunmetalBorder
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = RacingAmber,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = categoryTitle,
                    color = RacingAmber,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            items.forEachIndexed { index, (label, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = label,
                        color = MutedSlate,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = value,
                        color = TitaniumSilver,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1.6f)
                    )
                }
                if (index < items.size - 1) {
                    HorizontalDivider(
                        color = GunmetalBorder.copy(alpha = 0.5f),
                        thickness = 0.8.dp
                    )
                }
            }
        }
    }
}
