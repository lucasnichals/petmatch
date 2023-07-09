package br.com.projects.petbite.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.projects.petbite.ui.adapter.CustomAdapter
import br.com.projects.petbite.data.PetDTO
import br.com.projects.petbite.R
import br.com.projects.petbite.domain.PetListState
import br.com.projects.petbite.domain.PetListViewModel


class PetListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_list)
        var petList: List<PetDTO>? = null
        val rvPetList: RecyclerView = findViewById(R.id.rv_pet_list)


        val viewModel: PetListViewModel by viewModels()

        viewModel.pets.observe(this, Observer { pets ->
            petList = pets
            rvPetList.adapter = CustomAdapter(pets)
        })
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                PetListState.LOADING -> showLoading()
                PetListState.FAILURE -> showError()
                PetListState.SUCCESS -> hideLoading()
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
                pet?.let {
                    val intent = Intent(this@PetListActivity, DetailActivity::class.java)
                    intent.putExtra("pet", pet)
                    startActivity(intent)
                }
            }
        }).attachToRecyclerView(rvPetList)
    }

    private fun hideLoading() {
        TODO("Not yet implemented")
    }

    private fun showError() {
        TODO("Not yet implemented")
    }

    private fun showLoading() {
        TODO("Not yet implemented")
    }
}

