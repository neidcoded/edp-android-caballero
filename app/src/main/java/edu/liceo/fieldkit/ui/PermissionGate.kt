package edu.liceo.fieldkit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.liceo.fieldkit.permissions.*

@Composable
fun PermissionGate(
    state: PermissionState,
    feature: String,
    reason: String,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        when (state.status) {
            PermStatus.Granted -> content()
            PermStatus.NotAsked -> Button(onClick = state.request) { Text("Allow $feature") }
            PermStatus.NeedsRationale -> {
                Text(reason)
                Button(onClick = state.request) { Text("Try again") }
            }
            PermStatus.Denied -> {
                Text("$feature is blocked. Turn it on in Settings.")
                Button(onClick = { context.openAppSettings() }) { Text("Open Settings") }
            }
        }
    }
}
