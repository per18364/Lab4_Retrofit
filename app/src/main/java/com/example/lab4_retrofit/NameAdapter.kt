package com.example.lab4_retrofit

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(val name:String):RecyclerView.Adapter<PokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = name.length

}