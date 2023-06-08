package br.com.projects.petbite

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_list)
        val rvPetList: RecyclerView = findViewById(R.id.rv_pet_list)
        val network = Network.getInstance("https://64690bde03bb12ac2084f15c.mockapi.io/")
        val petAPI = network.create(MockAPI::class.java)
        val listPets = petAPI.listPets()

        listPets.enqueue(object : Callback<List<PetDTO>> {
            override fun onResponse(
                call: Call<List<PetDTO>>, response: Response<List<PetDTO>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { rvPetList.adapter = CustomAdapter(it) }
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
                val intent = Intent(this@PetListActivity, DetailActivity::class.java)
                startActivity(intent)
            }
        }).attachToRecyclerView(rvPetList)
    }
}
