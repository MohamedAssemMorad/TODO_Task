package com.example.todo.presentation.todo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.R
import com.example.todo.domain.model.todo.Todo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var desEditText: EditText
    private lateinit var btnCreate: Button
    private lateinit var progressBar: ProgressBar

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        titleEditText = findViewById(R.id.etTitle)
        desEditText = findViewById(R.id.etDes)
        btnCreate = findViewById(R.id.btnCreateTodo)
        progressBar = findViewById(R.id.progressBar)

        btnCreate.setOnClickListener {
            createTodo()
        }
    }

    private fun createTodo() {
        progressBar.visibility = View.VISIBLE
        var todo = Todo(0, titleEditText.text.toString(), desEditText.text.toString(), "", "", true)
        todoViewModel.createTodo(todo).invokeOnCompletion {
            CoroutineScope(Dispatchers.Main).launch {
                if (todoViewModel._todoValue.value.success)
                    progressBar.visibility = View.GONE
            }
        }
    }
}