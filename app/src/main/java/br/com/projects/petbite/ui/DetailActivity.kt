package br.com.projects.petbite.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import br.com.projects.petbite.data.PetDTO
import br.com.projects.petbite.R

class DetailActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val tvPetName: TextView = findViewById(R.id.tv_cv_pet_name)
        val tvPetCreationDate: TextView = findViewById(R.id.tv_cv_creation_date)
        val tvBirthday: TextView = findViewById(R.id.tv_cv_birthday)
        val pet = intent.getParcelableExtra("pet", PetDTO::class.java)
        pet?.let { value ->
            tvPetName.text = "Name: ${value.name}"
            tvPetCreationDate.text = "Creation Date: ${value.creationDate}"
            tvBirthday.text = "Birthday: ${value.birthday}"
        }
    }
}
