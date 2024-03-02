package br.com.projects.petbite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.com.projects.petbite.view.ClientActivity



class MainActivity : AppCompatActivity() {
    val USERNAME = "u"
    val PASSWORD = "p"

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etUsername: EditText = findViewById(R.id.et_main_username)
        val etPassword: EditText = findViewById(R.id.et_main_password)
        val btnLogin: Button = findViewById(R.id.btn_main_login)
        val tvCadastro: TextView = findViewById(R.id.tv_main_cadastro)

        btnLogin.setOnClickListener {
            val enteredUsername = etUsername.text.toString()
            val enteredPassword = etPassword.text.toString()
            if (enteredUsername == USERNAME && enteredPassword == PASSWORD) {
                showToast("Login bem-sucedido", Toast.LENGTH_LONG)
            } else {
                showToast("Nome de usuário ou senha incorretos", Toast.LENGTH_LONG)
            }
        }


        tvCadastro.setOnClickListener {
            val intent = Intent(this, ClientActivity::class.java)
            startActivity(intent)
        }

        fun showToast(message: String, duration: Int) {
            runOnUiThread {
                Toast.makeText(this, message, duration).show()
            }
        }


        btnLogin.setOnClickListener {
            val intent = Intent(this, PetListActivity::class.java)
            startActivity(intent)

        }
    }

    private fun showToast(message: String, duration: Int) {
        val toast = Toast.makeText(this, message, duration)
        toast.setGravity(
            android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 10, 10
        )
        toast.show()
    }

}
