package com.example.todo.data.data_source

import com.example.todo.data.data_source.dto.todo.TodoItemDto
import com.example.todo.data.data_source.dto.user.UserDto

interface TODOApi {

    suspend fun createUser(user: UserDto) {}
    suspend fun getUser(): UserDto {
        return UserDto("", "")
    }

    suspend fun createTodo(todo: TodoItemDto) {}
}