package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ComponentSystem
import com.example.ui.theme.*

@Composable
fun MotorcycleSchematicView(
    selectedSystem: ComponentSystem,
    onSystemSelected: (ComponentSystem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CharcoalDark)
            .border(1.dp, GunmetalBorder, RoundedCornerShape(20.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "INTERACTIVE SYSTEM SCHEMATIC",
                color = RacingAmber,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.TouchApp,
                    contentDescription = null,
                    tint = TechCyan,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Tap node to inspect",
                    color = MutedSlate,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Canvas Area with interactive hotspots
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(CarbonBlack)
                .border(1.dp, GunmetalBorder.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                drawMotorcycleBlueprint(
                    selectedSystem = selectedSystem
                )
            }

            // Hotspot Buttons overlayed on the schematic
            // Rear Wheel & Brakes
            HotspotNode(
                title = "Wheels",
                system = ComponentSystem.WHEELS,
                selected = selectedSystem == ComponentSystem.WHEELS,
                modifier = Modifier.align(Alignment.BottomStart).offset(x = 36.dp, y = (-26).dp),
                onClick = { onSystemSelected(ComponentSystem.WHEELS) }
            )

            // Drivetrain / Chain
            HotspotNode(
                title = "Drivetrain",
                system = ComponentSystem.DRIVETRAIN,
                selected = selectedSystem == ComponentSystem.DRIVETRAIN,
                modifier = Modifier.align(Alignment.BottomStart).offset(x = 88.dp, y = (-54).dp),
                onClick = { onSystemSelected(ComponentSystem.DRIVETRAIN) }
            )

            // Engine
            HotspotNode(
                title = "Engine",
                system = ComponentSystem.ENGINE,
                selected = selectedSystem == ComponentSystem.ENGINE,
                modifier = Modifier.align(Alignment.Center).offset(x = (-10).dp, y = 14.dp),
                onClick = { onSystemSelected(ComponentSystem.ENGINE) }
            )

            // Transmission & Clutch
            HotspotNode(
                title = "Clutch",
                system = ComponentSystem.TRANSMISSION,
                selected = selectedSystem == ComponentSystem.TRANSMISSION,
                modifier = Modifier.align(Alignment.Center).offset(x = (-42).dp, y = 30.dp),
                onClick = { onSystemSelected(ComponentSystem.TRANSMISSION) }
            )

            // Fuel Tank / Air
            HotspotNode(
                title = "Fuel & Air",
                system = ComponentSystem.FUEL_AIR,
                selected = selectedSystem == ComponentSystem.FUEL_AIR,
                modifier = Modifier.align(Alignment.TopCenter).offset(x = 8.dp, y = 20.dp),
                onClick = { onSystemSelected(ComponentSystem.FUEL_AIR) }
            )

            // Exhaust
            HotspotNode(
                title = "Exhaust",
                system = ComponentSystem.EXHAUST,
                selected = selectedSystem == ComponentSystem.EXHAUST,
                modifier = Modifier.align(Alignment.BottomCenter).offset(x = (-12).dp, y = (-12).dp),
                onClick = { onSystemSelected(ComponentSystem.EXHAUST) }
            )

            // Suspension (Front Forks)
            HotspotNode(
                title = "Suspension",
                system = ComponentSystem.SUSPENSION,
                selected = selectedSystem == ComponentSystem.SUSPENSION,
                modifier = Modifier.align(Alignment.TopEnd).offset(x = (-66).dp, y = 42.dp),
                onClick = { onSystemSelected(ComponentSystem.SUSPENSION) }
            )

            // Brakes (Front Rotor)
            HotspotNode(
                title = "Brakes",
                system = ComponentSystem.BRAKES,
                selected = selectedSystem == ComponentSystem.BRAKES,
                modifier = Modifier.align(Alignment.BottomEnd).offset(x = (-34).dp, y = (-26).dp),
                onClick = { onSystemSelected(ComponentSystem.BRAKES) }
            )

            // Electrical
            HotspotNode(
                title = "Electrical",
                system = ComponentSystem.ELECTRICAL,
                selected = selectedSystem == ComponentSystem.ELECTRICAL,
                modifier = Modifier.align(Alignment.TopStart).offset(x = 100.dp, y = 28.dp),
                onClick = { onSystemSelected(ComponentSystem.ELECTRICAL) }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Selected System Pills bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ACTIVE: ${selectedSystem.title.uppercase()}",
                color = TitaniumSilver,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = RacingAmber.copy(alpha = 0.15f),
                border = androidx.compose.foundation.BorderStroke(1.dp, RacingAmber)
            ) {
                Text(
                    text = "System Inspected",
                    color = RacingAmber,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }
    }
}

@Composable
private fun HotspotNode(
    title: String,
    system: ComponentSystem,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (selected) 1.2f else 1.0f,
        animationSpec = tween(200),
        label = "hotspot_scale"
    )
    val color by animateColorAsState(
        targetValue = if (selected) RacingAmber else TechCyan,
        animationSpec = tween(200),
        label = "hotspot_color"
    )

    Surface(
        modifier = modifier
            .clip(CircleShape)
            .clickable(onClick = onClick),
        shape = CircleShape,
        color = CharcoalDark.copy(alpha = 0.9f),
        border = androidx.compose.foundation.BorderStroke(2.dp, color),
        shadowElevation = if (selected) 6.dp else 2.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = title,
                color = if (selected) RacingAmber else TitaniumSilver,
                fontSize = 10.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
            )
        }
    }
}

private fun DrawScope.drawMotorcycleBlueprint(
    selectedSystem: ComponentSystem
) {
    val width = size.width
    val height = size.height

    val rearWheelCenter = Offset(width * 0.18f, height * 0.72f)
    val frontWheelCenter = Offset(width * 0.82f, height * 0.72f)
    val wheelRadius = height * 0.23f

    // Grid lines for blueprint aesthetic
    val gridColor = Color(0x15FFFFFF)
    var x = 0f
    while (x <= width) {
        drawLine(gridColor, Offset(x, 0f), Offset(x, height), strokeWidth = 1f)
        x += 35f
    }
    var y = 0f
    while (y <= height) {
        drawLine(gridColor, Offset(0f, y), Offset(width, y), strokeWidth = 1f)
        y += 35f
    }

    // Rear Wheel
    val wheelColor = if (selectedSystem == ComponentSystem.WHEELS) RacingAmber else Color(0xFF4A5568)
    drawCircle(wheelColor, radius = wheelRadius, center = rearWheelCenter, style = Stroke(width = 6f))
    drawCircle(wheelColor, radius = wheelRadius * 0.55f, center = rearWheelCenter, style = Stroke(width = 3f))
    drawCircle(Color(0xFF718096), radius = 8f, center = rearWheelCenter)

    // Front Wheel
    drawCircle(wheelColor, radius = wheelRadius, center = frontWheelCenter, style = Stroke(width = 6f))
    drawCircle(wheelColor, radius = wheelRadius * 0.55f, center = frontWheelCenter, style = Stroke(width = 3f))
    drawCircle(Color(0xFF718096), radius = 8f, center = frontWheelCenter)

    // Brake Discs
    val brakeColor = if (selectedSystem == ComponentSystem.BRAKES) RacingAmber else TechCyan.copy(alpha = 0.7f)
    drawCircle(brakeColor, radius = wheelRadius * 0.62f, center = frontWheelCenter, style = Stroke(width = 4f))
    drawCircle(brakeColor, radius = wheelRadius * 0.50f, center = rearWheelCenter, style = Stroke(width = 4f))

    // Front Fork (Suspension)
    val forkColor = if (selectedSystem == ComponentSystem.SUSPENSION) RacingAmber else Color(0xFFCBD5E1)
    val headTube = Offset(width * 0.68f, height * 0.28f)
    val handlebar = Offset(width * 0.64f, height * 0.18f)
    drawLine(forkColor, headTube, frontWheelCenter, strokeWidth = 7f, cap = StrokeCap.Round)
    drawLine(forkColor, headTube, handlebar, strokeWidth = 5f, cap = StrokeCap.Round)

    // Chassis Frame & Swingarm
    val frameColor = Color(0xFF64748B)
    val swingarmPivot = Offset(width * 0.40f, height * 0.62f)
    val seatJunction = Offset(width * 0.38f, height * 0.42f)
    val tailEnd = Offset(width * 0.16f, height * 0.34f)

    val framePath = Path().apply {
        moveTo(rearWheelCenter.x, rearWheelCenter.y)
        lineTo(swingarmPivot.x, swingarmPivot.y)
        lineTo(seatJunction.x, seatJunction.y)
        lineTo(headTube.x, headTube.y)
        lineTo(swingarmPivot.x, swingarmPivot.y)
    }
    drawPath(framePath, frameColor, style = Stroke(width = 5f, cap = StrokeCap.Round, join = StrokeJoin.Round))

    // Subframe / Seat
    drawLine(frameColor, seatJunction, tailEnd, strokeWidth = 4f, cap = StrokeCap.Round)
    drawLine(frameColor, swingarmPivot, tailEnd, strokeWidth = 3f, cap = StrokeCap.Round)

    // Rear Shock
    val shockColor = if (selectedSystem == ComponentSystem.SUSPENSION) RacingAmber else Color(0xFFFF5722)
    val shockTop = Offset(width * 0.42f, height * 0.46f)
    drawLine(shockColor, swingarmPivot, shockTop, strokeWidth = 6f, cap = StrokeCap.Round)

    // Engine Block
    val engineColor = if (selectedSystem == ComponentSystem.ENGINE) RacingAmber else Color(0xFF94A3B8)
    val enginePath = Path().apply {
        moveTo(width * 0.44f, height * 0.52f)
        lineTo(width * 0.60f, height * 0.48f)
        lineTo(width * 0.56f, height * 0.74f)
        lineTo(width * 0.42f, height * 0.74f)
        close()
    }
    drawPath(enginePath, engineColor.copy(alpha = 0.25f))
    drawPath(enginePath, engineColor, style = Stroke(width = 4f, join = StrokeJoin.Round))

    // Fuel Tank
    val tankColor = if (selectedSystem == ComponentSystem.FUEL_AIR) RacingAmber else Color(0xFF38BDF8)
    val tankPath = Path().apply {
        moveTo(seatJunction.x, seatJunction.y)
        cubicTo(
            width * 0.46f, height * 0.22f,
            width * 0.60f, height * 0.20f,
            headTube.x, headTube.y
        )
        lineTo(width * 0.52f, height * 0.44f)
        close()
    }
    drawPath(tankPath, tankColor.copy(alpha = 0.2f))
    drawPath(tankPath, tankColor, style = Stroke(width = 4f))

    // Exhaust Pipe & Muffler
    val exhaustColor = if (selectedSystem == ComponentSystem.EXHAUST) RacingAmber else Color(0xFFF97316)
    val exhaustPath = Path().apply {
        moveTo(width * 0.58f, height * 0.56f)
        lineTo(width * 0.60f, height * 0.78f)
        lineTo(width * 0.40f, height * 0.80f)
        lineTo(width * 0.22f, height * 0.66f)
    }
    drawPath(exhaustPath, exhaustColor, style = Stroke(width = 5f, cap = StrokeCap.Round, join = StrokeJoin.Round))

    // Drive Chain
    val chainColor = if (selectedSystem == ComponentSystem.DRIVETRAIN) RacingAmber else Color(0xFFEAB308)
    drawLine(chainColor, rearWheelCenter, Offset(swingarmPivot.x, swingarmPivot.y + 10f), strokeWidth = 3f)
    drawLine(chainColor, Offset(rearWheelCenter.x, rearWheelCenter.y + 12f), Offset(swingarmPivot.x, swingarmPivot.y + 20f), strokeWidth = 3f)
}
