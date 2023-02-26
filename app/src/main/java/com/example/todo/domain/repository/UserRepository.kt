package com.example.todo.domain.repository

import com.example.todo.data.data_source.dto.user.UserDto
import com.example.todo.domain.model.user.User

interface UserRepository {

    // for room db
    suspend fun createUserDB(user: User)
    suspend fun getUserDB(userName : String): User

    // remote repo
    suspend fun createUser(user: UserDto)
    suspend fun getUser(): UserDto
}