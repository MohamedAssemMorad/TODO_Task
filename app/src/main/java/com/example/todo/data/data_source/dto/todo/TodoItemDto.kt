package com.example.todo.data.data_source.dto.todo

import com.example.todo.domain.model.todo.Todo

data class TodoItemDto(
    val title: String,
    val description: String,
    val priority: String,
    val date: String,
    val completed: Boolean
) {
    fun toTodo(): Todo {
        return Todo(id = 0, title = title, description, priority, date, completed)
    }
}