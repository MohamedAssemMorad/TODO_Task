package com.example.todo.domain.repository

import com.example.todo.data.data_source.dto.todo.TodoItemDto
import com.example.todo.domain.model.todo.Todo

interface TodoRepository {

    // room DB
    suspend fun addTodoToDB(todo: Todo)
    suspend fun getTodoListFromDB(): List<Todo>
    suspend fun updateTodo (todo: Todo)


    // remote api
    suspend fun addTodo(todoItemDto: TodoItemDto)

}