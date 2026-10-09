package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

enum class AppDestination(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val isPrimaryTab: Boolean = false
) {
    HOME("home", "Home", Icons.Default.Home, isPrimaryTab = true),
    PARTS("parts", "Parts", Icons.Default.PrecisionManufacturing, isPrimaryTab = true),
    STAGES("stages", "Stages", Icons.Default.Timeline, isPrimaryTab = true),
    MAINTENANCE("maintenance", "Service", Icons.Default.Build, isPrimaryTab = true),
    ASSISTANT("assistant", "AI Guide", Icons.Default.Psychology, isPrimaryTab = true),
    
    // Sub-destinations
    SPECS("specs", "Motorcycle Specs", Icons.Default.TwoWheeler, isPrimaryTab = false),
    TROUBLESHOOTING("troubleshooting", "Troubleshooting", Icons.Default.BuildCircle, isPrimaryTab = false),
    WARNING_SIGNS("warning_signs", "Warning Signs", Icons.Default.Warning, isPrimaryTab = false),
    HEALTH_CHECK("health_check", "Health Check", Icons.Default.FactCheck, isPrimaryTab = false),
    CHECKLISTS("checklists", "Checklists", Icons.Default.Checklist, isPrimaryTab = false),
    CARE_MODS("care_mods", "Care & Mods", Icons.Default.AutoFixHigh, isPrimaryTab = false),
    SETTINGS("settings", "Settings", Icons.Default.Tune, isPrimaryTab = false),
    ADMIN("admin", "Admin", Icons.Default.AdminPanelSettings, isPrimaryTab = false)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotoTopAppBar(
    title: String,
    subtitle: String? = null,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    activeBikeName: String? = null,
    onBikeClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TitaniumSilver
                    )
                )
                if (!subtitle.isNullOrBlank()) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MutedSlate
                        )
                    )
                }
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TitaniumSilver
                    )
                }
            } else {
                IconButton(onClick = onBikeClick) {
                    Icon(
                        imageVector = Icons.Default.TwoWheeler,
                        contentDescription = "Motorcycle",
                        tint = RacingAmber
                    )
                }
            }
        },
        actions = {
            if (!activeBikeName.isNullOrBlank()) {
                Surface(
                    onClick = onBikeClick,
                    shape = RoundedCornerShape(20.dp),
                    color = GunmetalSurface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(RacingAmber)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = activeBikeName,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TitaniumSilver
                        )
                    }
                }
            }
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = TitaniumSilver
                )
            }
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Default.Tune,
                    contentDescription = "Settings",
                    tint = TitaniumSilver
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = CharcoalDark,
            scrolledContainerColor = CharcoalDark
        ),
        modifier = Modifier.border(
            width = 1.dp,
            color = GunmetalBorder.copy(alpha = 0.5f)
        )
    )
}

@Composable
fun MotoBottomBar(
    currentRoute: String,
    onNavigate: (AppDestination) -> Unit
) {
    NavigationBar(
        containerColor = CharcoalDark,
        tonalElevation = 0.dp,
        modifier = Modifier.border(
            width = 1.dp,
            color = GunmetalBorder.copy(alpha = 0.6f)
        )
    ) {
        AppDestination.entries.filter { it.isPrimaryTab }.forEach { destination ->
            val selected = currentRoute == destination.route
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.title,
                        tint = if (selected) RacingAmber else MutedSlate
                    )
                },
                label = {
                    Text(
                        text = destination.title,
                        fontSize = 11.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (selected) RacingAmber else MutedSlate
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = RacingAmber.copy(alpha = 0.15f),
                    selectedIconColor = RacingAmber,
                    unselectedIconColor = MutedSlate,
                    selectedTextColor = RacingAmber,
                    unselectedTextColor = MutedSlate
                )
            )
        }
    }
}
