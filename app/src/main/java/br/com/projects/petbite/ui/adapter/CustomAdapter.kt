package br.com.projects.petbite.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.projects.petbite.data.PetDTO
import br.com.projects.petbite.R

class CustomAdapter(private val dataSet: List<PetDTO>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public val tvPetName: TextView

        init {
            tvPetName = view.findViewById(R.id.tv_cv_pet_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvPetName.text = dataSet[position].id
    }

    fun getPet(int: Int): PetDTO {
        return dataSet[int]
    }
}