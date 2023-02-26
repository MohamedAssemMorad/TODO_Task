package com.example.todo.data.repository

import com.example.todo.data.data_source.TODOApi
import com.example.todo.data.data_source.TODODao
import com.example.todo.data.data_source.dto.user.UserDto
import com.example.todo.domain.model.user.User
import com.example.todo.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val appApi: TODOApi,
    private val todoDao: TODODao
) : UserRepository {
    override suspend fun createUserDB(user: User) = todoDao.createUser(user)

    override suspend fun getUserDB(userName: String): User = todoDao.getUser(userName)

    override suspend fun createUser(user: UserDto) = appApi.createUser(user)

    override suspend fun getUser(): UserDto {
        return appApi.getUser()
    }

}