package com.example.todo.data.data_source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.data.data_source.TODODao
import com.example.todo.domain.model.todo.Todo
import com.example.todo.domain.model.user.User
import com.example.todo.utils.Converters

@Database(entities = [User::class, Todo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TODODao
}