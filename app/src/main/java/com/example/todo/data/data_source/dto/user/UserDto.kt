package com.example.todo.data.data_source.dto.user

import com.example.todo.domain.model.user.User

data class UserDto(
    val userName: String,
    val password: String
){
    fun toUser() : User {
        return User(id = 0, userName, password)
    }
}
