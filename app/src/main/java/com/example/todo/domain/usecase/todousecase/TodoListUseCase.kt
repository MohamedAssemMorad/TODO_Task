package com.example.todo.domain.usecase.todousecase

import com.example.todo.domain.model.todo.Todo
import com.example.todo.domain.model.user.User
import com.example.todo.domain.repository.TodoRepository
import com.example.todo.domain.repository.UserRepository
import com.example.todo.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TodoListUseCase @Inject constructor(private val todoRepository: TodoRepository) {

    operator fun invoke(): Flow<ResponseState<List<Todo>>> = flow {
        try {
            emit(ResponseState.Loading<List<Todo>>())
            val todos = todoRepository.getTodoListFromDB()
            emit(ResponseState.Success<List<Todo>>(todos))
        } catch (e: HttpException) {
            emit(ResponseState.Error<List<Todo>>(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(ResponseState.Error<List<Todo>>("error Occurred"))
        }
    }
}