package br.com.projects.petbite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val USERNAME = "u"
    val PASSWORD = "p"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etUsername: EditText = findViewById(R.id.et_main_username)
        val etPassword: EditText = findViewById(R.id.et_main_password)
        val btnLogin: Button = findViewById(R.id.btn_main_login)

        btnLogin.setOnClickListener {
            val enteredUsername = etUsername.text.toString()
            val enteredPassword = etPassword.text.toString()
            if (enteredUsername == USERNAME && enteredPassword == PASSWORD) {
                showToast("Login bem-sucedido", Toast.LENGTH_SHORT)
            } else {
                showToast("Nome de usuário ou senha incorretos", Toast.LENGTH_SHORT)
            }
        }
    }
    private fun showToast(message: String, duration: Int) {
        val toast = Toast.makeText(this, message, duration)
        toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 10, 10)
        toast.show()
    }
}
