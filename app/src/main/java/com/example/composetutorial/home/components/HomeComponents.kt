package com.example.composetutorial.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.composetutorial.models.Todo

@Composable
fun TodoItem(
    todo: Todo,
    onChecked: (Boolean) -> Unit,
    onDelete: (Todo) -> Unit,
) {
    var state = remember {
        mutableStateOf(todo.isCompleted)
    }

    Card(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,

    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = state.value,
                onCheckedChange = {
                    state.value = it
                    onChecked(it)
                },
                colors = CheckboxDefaults.colors(Color.Blue)
            )
            Column(modifier = Modifier.weight(1f)) {
                if (state.value){
                    Text(
                        text = todo.todo,
                        style = MaterialTheme.typography.subtitle2,
                        textDecoration = TextDecoration.LineThrough
                    )
                }else{
                    Text(
                        text = todo.todo,
                        style = MaterialTheme.typography.subtitle2,
                    )
                }

            }
            IconButton(
                onClick = { onDelete(todo) }
            ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Delete task")
            }
        }
    }
}