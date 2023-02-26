package com.example.todo.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.domain.model.todo.Todo
import com.example.todo.domain.model.user.User

@Dao
interface TODODao {

    @Insert
    fun createUser(user: User)

    @Query("Select * from user Where userName = :user ")
    fun getUser(user: String): User

    @Insert
    fun createTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("Select * from todo")
    fun getTodos(): List<Todo>
}