package br.com.projects.petbite

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetListActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_list)
        var petList: List<PetDTO>? = null
        val rvPetList: RecyclerView = findViewById(R.id.rv_pet_list)
        val network = Network.getInstance("https://64690bde03bb12ac2084f15c.mockapi.io/")
        val petAPI = network.create(MockAPI::class.java)
        val listPets = petAPI.listPets()

        listPets.enqueue(object : Callback<List<PetDTO>> {
            override fun onResponse(
                call: Call<List<PetDTO>>, response: Response<List<PetDTO>>
            ) {
                if (response.isSuccessful) {
                    val pets = response.body()
                    pets?.let {
                        petList = it
                        rvPetList.adapter = CustomAdapter(it)
                    }
                }
            }
            override fun onFailure(call: Call<List<PetDTO>>, t: Throwable) {

            }
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val pet = petList?.get(position)
                val petName = pet?.name
                val petCreationDate = pet?.creationDate
                val petBirthday = pet?.birthday
                val intent = Intent(this@PetListActivity, DetailActivity::class.java)
                intent.putExtra("petName", petName)
                intent.putExtra("petCreationDate", petCreationDate)
                intent.putExtra("petBirthday", petBirthday)
                startActivity(intent)
            }
        }).attachToRecyclerView(rvPetList)
    }
}

