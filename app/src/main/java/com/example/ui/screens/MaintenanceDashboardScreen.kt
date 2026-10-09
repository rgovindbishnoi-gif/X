package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.data.model.*
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun MaintenanceDashboardScreen(
    motorcycle: UserMotorcycleEntity?,
    spec: MotorcycleSpec,
    reminders: List<ServiceReminderItem>,
    serviceRecords: List<ServiceHistoryEntity>,
    onAddRecord: (ServiceHistoryEntity) -> Unit,
    onDeleteRecord: (ServiceHistoryEntity) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Reminders, 1: Service Logbook
    var showAddDialog by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var filterCategory by remember { mutableStateOf("All") }

    val filteredRecords = remember(serviceRecords, searchQuery, filterCategory) {
        serviceRecords.filter { record ->
            val matchesQuery = searchQuery.isBlank() ||
                    record.serviceType.contains(searchQuery, ignoreCase = true) ||
                    record.workshop.contains(searchQuery, ignoreCase = true) ||
                    record.notes.contains(searchQuery, ignoreCase = true) ||
                    record.partsReplaced.contains(searchQuery, ignoreCase = true)

            val matchesFilter = filterCategory == "All" || record.serviceType.equals(filterCategory, ignoreCase = true)
            matchesQuery && matchesFilter
        }
    }

    Scaffold(
        containerColor = CarbonBlack,
        floatingActionButton = {
            if (selectedTab == 1) {
                FloatingActionButton(
                    onClick = { showAddDialog = true },
                    containerColor = RacingAmber,
                    contentColor = Color.Black
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Log Service")
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item {
                Column {
                    Text(
                        text = "MAINTENANCE & SERVICE CENTER",
                        color = RacingAmber,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Service Intervals & Records",
                        color = TitaniumSilver,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            // Tabs: Scheduled Reminders vs Service History Logbook
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = GunmetalSurface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(4.dp)) {
                        TabButton(
                            title = "Active Reminders (${reminders.size})",
                            selected = selectedTab == 0,
                            modifier = Modifier.weight(1f),
                            onClick = { selectedTab = 0 }
                        )
                        TabButton(
                            title = "Service Logbook (${serviceRecords.size})",
                            selected = selectedTab == 1,
                            modifier = Modifier.weight(1f),
                            onClick = { selectedTab = 1 }
                        )
                    }
                }
            }

            if (selectedTab == 0) {
                // 1. Odometer & Next Scheduled Service Card
                item {
                    val nextServiceDueKm = (motorcycle?.lastServiceKm ?: 1000) + spec.serviceIntervalKm
                    val kmRemaining = nextServiceDueKm - (motorcycle?.currentKm ?: 1250)

                    GlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        borderColor = RacingAmber.copy(alpha = 0.5f)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "NEXT SCHEDULED SERVICE",
                                    color = RacingAmber,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (kmRemaining > 500) StatusGood.copy(alpha = 0.15f) else StatusOverdue.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = if (kmRemaining > 0) "IN $kmRemaining KM" else "OVERDUE",
                                        color = if (kmRemaining > 0) StatusGood else StatusOverdue,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Target: $nextServiceDueKm km",
                                color = TitaniumSilver,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "Official Interval: ${spec.serviceIntervalKm} km or 12 Months (whichever comes first).",
                                color = MutedSlate,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                // 2. Status Breakdown Badges
                item {
                    val goodCount = reminders.count { it.status == ReminderStatus.GOOD }
                    val dueSoonCount = reminders.count { it.status == ReminderStatus.DUE_SOON }
                    val overdueCount = reminders.count { it.status == ReminderStatus.OVERDUE }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatusMetric(label = "🟢 Good", count = goodCount, color = StatusGood, modifier = Modifier.weight(1f))
                        StatusMetric(label = "🟡 Due Soon", count = dueSoonCount, color = StatusDueSoon, modifier = Modifier.weight(1f))
                        StatusMetric(label = "🔴 Overdue", count = overdueCount, color = StatusOverdue, modifier = Modifier.weight(1f))
                    }
                }

                // 3. Reminders list
                items(reminders) { item ->
                    FullReminderItemCard(reminder = item)
                }

            } else {
                // SERVICE LOGBOOK TAB
                item {
                    // Search & Filter Row
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search service records, parts, workshop...") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = MutedSlate) },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(Icons.Default.Clear, contentDescription = null, tint = MutedSlate)
                                }
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RacingAmber,
                            unfocusedBorderColor = GunmetalBorder,
                            focusedTextColor = TitaniumSilver,
                            unfocusedTextColor = TitaniumSilver
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (filteredRecords.isEmpty()) {
                    item {
                        GlassCard(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ReceiptLong,
                                    contentDescription = null,
                                    tint = MutedSlate,
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "No Service Records Found",
                                    color = TitaniumSilver,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Text(
                                    text = "Tap '+' below to log an oil change, maintenance, or repair.",
                                    color = MutedSlate,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                } else {
                    items(filteredRecords) { record ->
                        ServiceRecordCard(
                            record = record,
                            onDelete = { onDeleteRecord(record) }
                        )
                    }
                }
            }
        }
    }

    // Add Service Record Dialog
    if (showAddDialog) {
        AddServiceRecordDialog(
            currentKm = motorcycle?.currentKm ?: 1250,
            onDismiss = { showAddDialog = false },
            onConfirm = { newRecord ->
                onAddRecord(newRecord)
                showAddDialog = false
            }
        )
    }
}

@Composable
private fun TabButton(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        color = if (selected) RacingAmber else Color.Transparent,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = title,
                color = if (selected) Color.Black else TitaniumSilver,
                fontSize = 12.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
            )
        }
    }
}

@Composable
private fun StatusMetric(
    label: String,
    count: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = GunmetalSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$count", color = color, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = label, color = TitaniumSilver, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun FullReminderItemCard(reminder: ServiceReminderItem) {
    val (statusColor, badgeText) = when (reminder.status) {
        ReminderStatus.OVERDUE -> Pair(StatusOverdue, "OVERDUE")
        ReminderStatus.DUE_SOON -> Pair(StatusDueSoon, "DUE SOON")
        ReminderStatus.GOOD -> Pair(StatusGood, "HEALTHY")
    }

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = statusColor.copy(alpha = 0.4f)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(statusColor)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = reminder.name,
                            color = TitaniumSilver,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = reminder.importance,
                            color = TechCyan,
                            fontSize = 11.sp
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = statusColor.copy(alpha = 0.15f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, statusColor)
                ) {
                    Text(
                        text = badgeText,
                        color = statusColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(CharcoalDark)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("INTERVAL", color = MutedSlate, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    Text("Every ${reminder.intervalKm} km", color = TitaniumSilver, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                }
                Column {
                    Text("LAST DONE", color = MutedSlate, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    Text("${reminder.lastDoneKm} km", color = TitaniumSilver, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                }
                Column {
                    Text("STATUS", color = MutedSlate, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = if (reminder.dueKmRemaining <= 0) "${-reminder.dueKmRemaining} km past due" else "In ${reminder.dueKmRemaining} km",
                        color = statusColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun ServiceRecordCard(
    record: ServiceHistoryEntity,
    onDelete: () -> Unit
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = GunmetalBorder
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = record.serviceType,
                        color = RacingAmber,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "📅 ${record.date}  •  ${record.mileageKm} km",
                        color = TitaniumSilver,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (record.cost > 0) {
                        Text(
                            text = "$${"%.2f".format(record.cost)}",
                            color = TechCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(end = 6.dp)
                        )
                    }
                    IconButton(onClick = onDelete, modifier = Modifier.size(24.dp)) {
                        Icon(imageVector = Icons.Default.DeleteOutline, contentDescription = "Delete", tint = StatusOverdue)
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            if (record.partsReplaced.isNotBlank()) {
                Text(
                    text = "Parts: ${record.partsReplaced}",
                    color = TitaniumSilver,
                    fontSize = 11.sp
                )
            }
            if (record.oilReplaced.isNotBlank()) {
                Text(
                    text = "Oil: ${record.oilReplaced}",
                    color = TitaniumSilver,
                    fontSize = 11.sp
                )
            }
            if (record.notes.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Notes: ${record.notes}",
                    color = MutedSlate,
                    fontSize = 11.sp,
                    lineHeight = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = GunmetalSurface
            ) {
                Text(
                    text = "Facility: ${record.workshop}",
                    color = MutedSlate,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
private fun AddServiceRecordDialog(
    currentKm: Int,
    onDismiss: () -> Unit,
    onConfirm: (ServiceHistoryEntity) -> Unit
) {
    var serviceType by remember { mutableStateOf("Regular Service") }
    var mileageText by remember { mutableStateOf(currentKm.toString()) }
    var dateText by remember { mutableStateOf("2024-06-15") }
    var partsText by remember { mutableStateOf("Engine oil, Oil filter") }
    var oilText by remember { mutableStateOf("10W-40 JASO MA2 Full-Synthetic") }
    var costText by remember { mutableStateOf("65.00") }
    var workshopText by remember { mutableStateOf("DIY (Self)") }
    var notesText by remember { mutableStateOf("Chain cleaned, tensioned, and lubed. All bolts torqued.") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = CharcoalDark,
        title = {
            Text("Log Service Record", color = TitaniumSilver, fontWeight = FontWeight.Bold)
        },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    OutlinedTextField(
                        value = serviceType,
                        onValueChange = { serviceType = it },
                        label = { Text("Service Type") },
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
                        value = mileageText,
                        onValueChange = { mileageText = it.filter { c -> c.isDigit() } },
                        label = { Text("Odometer at Service (km)") },
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
                        value = dateText,
                        onValueChange = { dateText = it },
                        label = { Text("Date (YYYY-MM-DD)") },
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
                        value = partsText,
                        onValueChange = { partsText = it },
                        label = { Text("Parts Replaced") },
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
                        value = oilText,
                        onValueChange = { oilText = it },
                        label = { Text("Oil Brand & Viscosity") },
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
                        value = costText,
                        onValueChange = { costText = it },
                        label = { Text("Cost ($)") },
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
                        value = workshopText,
                        onValueChange = { workshopText = it },
                        label = { Text("Workshop / Facility") },
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
                        value = notesText,
                        onValueChange = { notesText = it },
                        label = { Text("Service Notes") },
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
                    onConfirm(
                        ServiceHistoryEntity(
                            date = dateText.trim(),
                            mileageKm = mileageText.toIntOrNull() ?: currentKm,
                            serviceType = serviceType.trim(),
                            partsReplaced = partsText.trim(),
                            oilReplaced = oilText.trim(),
                            cost = costText.toDoubleOrNull() ?: 0.0,
                            workshop = workshopText.trim(),
                            notes = notesText.trim()
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = RacingAmber)
            ) {
                Text("Save to Logbook", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = MutedSlate)
            }
        }
    )
}
