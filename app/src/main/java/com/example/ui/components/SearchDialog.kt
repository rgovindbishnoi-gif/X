package com.example.ui.components

import androidx.compose.foundation.background
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
import com.example.data.model.MotorcyclePart
import com.example.data.model.StageInfo
import com.example.data.model.WarningSignItem
import com.example.ui.theme.*

data class SearchResultItem(
    val title: String,
    val category: String,
    val snippet: String,
    val targetType: SearchTargetType,
    val payloadId: String
)

enum class SearchTargetType {
    PART,
    STAGE,
    WARNING_SIGN,
    CARE_GUIDE
}

@Composable
fun SearchDialog(
    parts: List<MotorcyclePart>,
    stages: List<StageInfo>,
    warningSigns: List<WarningSignItem>,
    onDismiss: () -> Unit,
    onSelectTarget: (SearchTargetType, String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    val results = remember(query, parts, stages, warningSigns) {
        if (query.length < 2) return@remember emptyList<SearchResultItem>()
        val q = query.trim().lowercase()
        val list = mutableListOf<SearchResultItem>()

        // Search Parts
        parts.forEach { part ->
            if (part.name.lowercase().contains(q) ||
                part.function.lowercase().contains(q) ||
                part.commonProblems.any { it.lowercase().contains(q) } ||
                part.warningSigns.any { it.lowercase().contains(q) }
            ) {
                list.add(
                    SearchResultItem(
                        title = part.name,
                        category = "Part • ${part.system.title}",
                        snippet = part.function,
                        targetType = SearchTargetType.PART,
                        payloadId = part.id
                    )
                )
            }
        }

        // Search Stages
        stages.forEach { stage ->
            if (stage.title.lowercase().contains(q) ||
                stage.subtitle.lowercase().contains(q) ||
                stage.fullExplanation.lowercase().contains(q) ||
                stage.keyItems.any { it.lowercase().contains(q) }
            ) {
                list.add(
                    SearchResultItem(
                        title = stage.title,
                        category = "Motorcycle Stage ${stage.stageNumber}",
                        snippet = stage.subtitle,
                        targetType = SearchTargetType.STAGE,
                        payloadId = stage.stageNumber.toString()
                    )
                )
            }
        }

        // Search Warning Signs
        warningSigns.forEach { sign ->
            if (sign.title.lowercase().contains(q) ||
                sign.whatItIndicates.lowercase().contains(q) ||
                sign.whatRiderShouldCheck.any { it.lowercase().contains(q) }
            ) {
                list.add(
                    SearchResultItem(
                        title = sign.title,
                        category = "Warning Sign",
                        snippet = sign.whatItIndicates,
                        targetType = SearchTargetType.WARNING_SIGN,
                        payloadId = sign.id
                    )
                )
            }
        }

        list.take(25)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = CharcoalDark,
        title = {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Search chain, brake, oil, overheating...", fontSize = 13.sp) },
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = RacingAmber) },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = MutedSlate)
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RacingAmber,
                    unfocusedBorderColor = GunmetalBorder,
                    focusedTextColor = TitaniumSilver,
                    unfocusedTextColor = TitaniumSilver
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Box(modifier = Modifier.heightIn(max = 360.dp)) {
                if (query.length < 2) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Type at least 2 characters to search across parts, maintenance stages, and troubleshooting symptoms.", color = MutedSlate, fontSize = 12.sp, lineHeight = 16.sp)
                    }
                } else if (results.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "No matching guides or parts found for \"$query\"", color = MutedSlate, fontSize = 13.sp)
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(results) { item ->
                            Surface(
                                onClick = {
                                    onSelectTarget(item.targetType, item.payloadId)
                                    onDismiss()
                                },
                                shape = RoundedCornerShape(8.dp),
                                color = GunmetalSurface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, GunmetalBorder),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = item.title, color = TitaniumSilver, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                        Text(text = item.category, color = TechCyan, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(text = item.snippet, color = MutedSlate, fontSize = 11.sp, maxLines = 1)
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close", color = RacingAmber)
            }
        }
    )
}
