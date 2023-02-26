package com.example.todo.presentation.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.model.todo.Todo
import com.example.todo.domain.usecase.todousecase.CreateTodoUseCase
import com.example.todo.domain.usecase.todousecase.TodoListUseCase
import com.example.todo.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val createTodoUseCase: CreateTodoUseCase,
    private val todoListUseCase: TodoListUseCase
) :
    ViewModel() {

    private val todoValue = MutableStateFlow(TodoState())
    var _todoValue: StateFlow<TodoState> = todoValue

    private val todoListValue = MutableStateFlow(TodoListState())
    var _todoListValue: StateFlow<TodoListState> = todoListValue


    fun createTodo(todo: Todo) = viewModelScope.launch {
        createTodoUseCase(todo).collect {
            when (it) {
                is ResponseState.Success -> {
                    todoValue.value = TodoState(success = true)
                }
                is ResponseState.Loading -> {
                    todoValue.value = TodoState(isLoading = true)
                }
                is ResponseState.Error -> {
                    todoValue.value =
                        TodoState(error = it.message ?: "unExpected Error Occurred")
                }
            }

        }
    }

    fun getTodoList() = viewModelScope.launch {
        todoListUseCase().collect {
            when (it) {
                is ResponseState.Success -> {
                    todoListValue.value = TodoListState(list = it.data)
                }
                is ResponseState.Loading -> {
                    todoListValue.value = TodoListState(isLoading = true)
                }
                is ResponseState.Error -> {
                    todoListValue.value =
                        TodoListState(error = it.message ?: "unExpected Error Occurred")
                }
            }

        }
    }

}