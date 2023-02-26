package com.example.todo.presentation.todo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.domain.model.todo.Todo
import com.example.todo.presentation.adapter.RecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoListActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var createBtn: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    var todoList: ArrayList<Todo> = ArrayList()


    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        progressBar = findViewById(R.id.progressBar)
        createBtn = findViewById(R.id.btnCreateTodo)
        recyclerView = findViewById(R.id.recyclerView)
        constraintLayout = findViewById(R.id.constraintLayout)

        createBtn.setOnClickListener {
            val i = Intent(this@TodoListActivity, TodoActivity::class.java)
            startActivity(i)
        }

        getTodoList()

    }

    private fun getTodoList() {
        progressBar.visibility = View.VISIBLE
        todoViewModel.getTodoList().invokeOnCompletion {
            CoroutineScope(Dispatchers.Main).launch {
                progressBar.visibility = View.GONE
                if (!todoViewModel._todoListValue.value.list.isNullOrEmpty())
                    todoViewModel._todoListValue.value.list?.let {
                        todoList.addAll(it)
                        initAdapter()
                    }
                else
                    Snackbar.make(
                        constraintLayout,
                        todoViewModel._todoListValue.value.error,
                        Snackbar.LENGTH_LONG
                    ).show()
            }
        }
    }

    private fun initAdapter() {
        recyclerViewAdapter = RecyclerViewAdapter(todoList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.notifyDataSetChanged()
    }
}