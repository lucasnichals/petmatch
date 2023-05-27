package br.com.projects.petbite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val dataSet: List<PetDTO>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPetName: TextView
        val tvPetCreationDate: TextView
        val tvPetBirthday: TextView

        init {
            tvPetName = view.findViewById(R.id.tv_pet_name)
            tvPetCreationDate = view.findViewById(R.id.tv_pet_creation_date)
            tvPetBirthday = view.findViewById(R.id.tv_pet_birthday)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvPetName.text = dataSet[position].name
        holder.tvPetCreationDate.text = dataSet[position].creationDate
        holder.tvPetBirthday.text = dataSet[position].birthday

    }
}

