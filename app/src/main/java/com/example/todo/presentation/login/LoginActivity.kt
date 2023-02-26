package com.example.todo.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.R
import com.example.todo.presentation.todo.TodoListActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var etUserName: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView
    private lateinit var tvError: TextView
    private lateinit var progressBar: ProgressBar

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUserName = findViewById(R.id.etUserName)
        etPass = findViewById(R.id.etPassword)
        tvSignUp = findViewById(R.id.tvSignUp)
        tvError = findViewById(R.id.tvError)
        btnLogin = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.progressBar)

        btnLogin.setOnClickListener {
            getUser()
        }
        tvSignUp.setOnClickListener {
            val i = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(i)
        }

    }

    private fun getUser() {
        progressBar.visibility = View.VISIBLE
        loginViewModel.getUser(etUserName.text.toString()).invokeOnCompletion {
            CoroutineScope(Dispatchers.Main).launch {
                progressBar.visibility = View.GONE

                if (loginViewModel._loginValue.value.user?.userName.isNullOrEmpty())
                    tvError.visibility = View.VISIBLE
                else {
                    val i = Intent(this@LoginActivity, TodoListActivity::class.java)
                    startActivity(i)
                }
            }
        }
    }
}