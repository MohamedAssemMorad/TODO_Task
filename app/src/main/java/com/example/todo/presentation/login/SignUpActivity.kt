package com.example.todo.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.R
import com.example.todo.domain.model.user.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var etUserName: EditText
    private lateinit var etPass: EditText
    private lateinit var btnCreate: Button
    private lateinit var progressBar: ProgressBar

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etUserName = findViewById(R.id.etUserName)
        etPass = findViewById(R.id.etPassword)
        btnCreate = findViewById(R.id.btnCreateUser)
        progressBar = findViewById(R.id.progressBar)

        btnCreate.setOnClickListener {
            createUser()
        }

    }

    private fun createUser() {
        var user = User(0, etUserName.text.toString(), etPass.text.toString())
        loginViewModel.createUser(user).invokeOnCompletion {
            CoroutineScope(Dispatchers.Main).launch {
                if (loginViewModel._signUpValue.value.success) {
                    Toast.makeText(this@SignUpActivity, "User Created", Toast.LENGTH_LONG).show()
                    val i = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(i)
                }
            }
        }
    }
}