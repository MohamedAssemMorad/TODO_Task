package com.example.todo.di

import android.content.Context
import androidx.room.Room
import com.example.todo.data.data_source.TODOApi
import com.example.todo.data.data_source.TODODao
import com.example.todo.data.data_source.room.AppDatabase
import com.example.todo.data.repository.TodoRepositoryImp
import com.example.todo.data.repository.UserRepositoryImp
import com.example.todo.domain.repository.TodoRepository
import com.example.todo.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // returns retrofit instance
    @Provides
    @Singleton
    fun provideAppApi(): TODOApi {
        return Retrofit.Builder().baseUrl("https://bommers-6b534.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TODOApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app, AppDatabase::class.java, "Todo.db"
    ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideAppDao(db: AppDatabase) = db.todoDao()

    @Provides
    @Singleton
    fun provideUserRepository(appApi: TODOApi, todoDao: TODODao): UserRepository {
        return UserRepositoryImp(appApi, todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoRepository(appApi: TODOApi, todoDao: TODODao): TodoRepository {
        return TodoRepositoryImp(appApi, todoDao)
    }
}