package com.example.lab4_retrofit

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_retrofit.databinding.DataBinding

class PokemonViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = DataBinding.bind(view)

    fun bind(dato:String){
        binding.vista
    }

}