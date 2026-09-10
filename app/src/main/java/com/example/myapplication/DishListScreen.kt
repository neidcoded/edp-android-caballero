package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DishListScreen(
    viewModel: DishViewModel,
    onDishClick: (Int) -> Unit
) {
    // State flows DOWN from the ViewModel into this screen.
    val dishes by viewModel.dishes.collectAsStateWithLifecycle()

    // Local UI state: what is currently typed, and which dish is being renamed.
    var newDishName by remember { mutableStateOf("") }
    var dishBeingEdited by remember { mutableStateOf<Dish?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "My Dishes", 
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(12.dp))

        // ---------- CREATE (given) ----------
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newDishName,
                onValueChange = { newDishName = it },
                label = { Text("New dish name") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.addDish(newDishName)
                    newDishName = "" // clear the box after adding
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) { Text("Add", color = MaterialTheme.colorScheme.onSecondary) }
        }

        Spacer(Modifier.height(16.dp))

        // ---------- READ (given) ----------
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = dishes, key = { it.id }) { dish ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDishClick(dish.id) } // opens Screen 2
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                dish.name, 
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                "${dish.recipes.size} step(s)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        TextButton(
                            onClick = { dishBeingEdited = dish },
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                        ) { Text("Edit") }
                        TextButton(onClick = {
                            // TODO 7 (10 pts): delete THIS dish through the ViewModel.
                            viewModel.deleteDish(dish.id)
                        }, colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)) { Text("Delete") }
                    }
                }
            }
        }

        // ---------- UPDATE dialog ----------
        val editing = dishBeingEdited
        if (editing != null) {
            EditDialog(
                title = "Rename dish",
                initialText = editing.name,
                onConfirm = { newName ->
                    // TODO 8 (15 pts): rename this dish through the ViewModel,
                    // then close the dialog by setting dishBeingEdited = null.
                    viewModel.updateDish(editing.id, newName)
                    dishBeingEdited = null
                },
                onDismiss = { dishBeingEdited = null }
            )
        }
    }
}

// ---------- Reusable dialog: GIVEN, use it on BOTH screens ----------
@Composable
fun EditDialog(
    title: String,
    initialText: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var text by remember { mutableStateOf(initialText) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true
            )
        },
        confirmButton = { TextButton(onClick = { onConfirm(text) }) { Text("Save") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}
