package com.example.miperfil.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.miperfil.R
import com.example.miperfil.data.model.Pet

class PetAdapter(
    private val onPetClick: (Pet) -> Unit
) : ListAdapter<Pet, PetAdapter.PetViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pet_card, parent, false)
        return PetViewHolder(view, onPetClick)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PetViewHolder(itemView: View, private val onPetClick: (Pet) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val tvPetName: TextView = itemView.findViewById(R.id.tvPetName)
        private val tvPetBreed: TextView = itemView.findViewById(R.id.tvPetBreed)
        private val tvPetMeta: TextView = itemView.findViewById(R.id.tvPetMeta)
        private var currentPet: Pet? = null

        init {
            itemView.setOnClickListener {
                currentPet?.let(onPetClick)
            }
        }

        fun bind(pet: Pet) {
            currentPet = pet
            tvPetName.text = pet.name
            tvPetBreed.text = pet.breed
            tvPetMeta.text = "${pet.age} años · ${String.format("%.1f", pet.weight)} kg"
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Pet>() {
            override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean = oldItem == newItem
        }
    }
}


