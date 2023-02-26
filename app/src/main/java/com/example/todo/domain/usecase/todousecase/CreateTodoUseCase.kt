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

class CreateTodoUseCase @Inject constructor(private val todoRepository: TodoRepository) {

    operator fun invoke(todo: Todo): Flow<ResponseState<Unit>> = flow {
        try {
            emit(ResponseState.Loading<Unit>())
            todoRepository.addTodoToDB(todo)
            emit(ResponseState.Success<Unit>(Unit))
        } catch (e: HttpException) {
            emit(ResponseState.Error<Unit>(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(ResponseState.Error<Unit>("error Occurred"))
        }
    }
}