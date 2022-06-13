package com.example.composetutorial.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import com.example.composetutorial.models.Todo

@Composable
fun HomeScreen() {

    val todoList = remember {
        mutableStateListOf<Todo>()
    }

    val text = remember {
        mutableStateOf("")
    }

    Card(
        elevation = 5.dp,
        modifier = Modifier
            .background(color = Transparent, shape = RoundedCornerShape(0.dp))
            .padding(16.dp)
    ) {
        Column() {
            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = 0.dp,
                    vertical = 8.dp
                )
            ) {

                itemsIndexed(todoList){index, item ->
                    TodoItem(todo = item, onChecked = {
                        todoList[index].isCompleted = true
                    }, onDelete = {
                        val deletedItem = todoList[index]
                        todoList.remove(deletedItem)
                    }
                    )

                }
            }

            Column(
                modifier = Modifier.padding(start = 16.dp, end = 0.dp, bottom = 16.dp)
            ) {
                Text(text = "Add")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextField(
                        value = text.value,
                        modifier = Modifier
                            .padding(0.dp)
                            .weight(1f),
                        onValueChange = {
                            text.value = it
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                        ),
                    )
                    IconButton(
                        onClick = {
                            val todo = Todo(
                                todo = text.value,
                                isCompleted = false
                            )
                            todoList.add(todo)
                        },
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Delete")
                    }
                }
            }

        }
    }

}


