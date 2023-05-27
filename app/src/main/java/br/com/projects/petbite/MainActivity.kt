package br.com.projects.petbite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val intent = Intent(this, activity_home::class.java)
            startActivity(intent)
        }

        fun showToast(message: String, duration: Int) {
            runOnUiThread {
                Toast.makeText(this, message, duration).show()
            }
        }
        val network = Network.getInstance("https://64690bde03bb12ac2084f15c.mockapi.io/")
        val petAPI = network.create(MockAPI::class.java)
        val listPets = petAPI.listPets()
        listPets.enqueue(object : Callback<List<PetDTO>> {
            override fun onResponse(call: Call<List<PetDTO>>, response: Response<List<PetDTO>>) {
                if (response.isSuccessful) {
                    val pets = response.body()
                    val petNames = pets?.joinToString(separator = "\n") { it.name }
                    showToast("Lista de Pets:\n$petNames", Toast.LENGTH_LONG)
                } else {
                    showToast("Falha ao obter a lista de pets. Código de resposta: ${response.code()}", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<List<PetDTO>>, t: Throwable) {
                showToast("Erro: ${t.message}", Toast.LENGTH_LONG)
            }

        })

    }

    private fun showToast(message: String, duration: Int) {
        val toast = Toast.makeText(this, message, duration)
        toast.setGravity(
            android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL,
            10,
            10
        )
        toast.show()
    }

}
