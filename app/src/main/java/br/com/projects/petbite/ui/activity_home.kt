package br.com.projects.petbite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.projects.petbite.R

class activity_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val btnVoltar: Button = findViewById(R.id.btn_main_back)

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}