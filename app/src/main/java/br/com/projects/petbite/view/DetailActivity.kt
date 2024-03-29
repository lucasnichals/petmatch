package br.com.projects.petbite.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.projects.petbite.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val petName = intent.getStringExtra("petName")
        val petCreationDate = intent.getStringExtra("petCreationDate")
        val petBirthday = intent.getStringExtra("petBirthday")

        val tvPetName: TextView = findViewById(R.id.tv_cv_pet_name)
        val tvPetCreationDate: TextView = findViewById(R.id.tv_cv_creation_date)
        val tvBirthday: TextView = findViewById(R.id.tv_cv_birthday)

        tvPetName.text = "Name: $petName"
        tvPetCreationDate.text = "Creation Date: $petCreationDate"
        tvBirthday.text = "Birthday: $petBirthday"

    }
}