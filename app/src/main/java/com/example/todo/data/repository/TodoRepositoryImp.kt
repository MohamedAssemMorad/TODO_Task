package com.example.todo.data.repository

import com.example.todo.data.data_source.TODOApi
import com.example.todo.data.data_source.TODODao
import com.example.todo.data.data_source.dto.todo.TodoItemDto
import com.example.todo.data.data_source.dto.user.UserDto
import com.example.todo.domain.model.todo.Todo
import com.example.todo.domain.model.user.User
import com.example.todo.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImp @Inject constructor(
    private val appApi: TODOApi,
    private val todoDao: TODODao
) : TodoRepository {

    override suspend fun addTodoToDB(todo: Todo) = todoDao.createTodo(todo)


    override suspend fun getTodoListFromDB(): List<Todo> = todoDao.getTodos()

    override suspend fun updateTodo(todo: Todo) = todoDao.updateTodo(todo)

    override suspend fun addTodo(todoItemDto: TodoItemDto) = appApi.createTodo(todoItemDto)


}