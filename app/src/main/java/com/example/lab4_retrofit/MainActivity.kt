package com.example.lab4_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab4_retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.temporal.TemporalQuery


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PokemonAdapter
    private val pokemonData = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Search.setOnQueryTextListener(this)
        initTextView()
    }

    private fun initTextView() {
        adapter = PokemonAdapter(pokemonData)
        binding.vistaInfo.layoutManager = LinearLayoutManager(this)
        binding.vistaInfo.adapter = adapter
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(Urls::class.java).getPokemonByName("$query")
            val poke = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    //mostrar datos
                    val datos = poke?.datos ?: emptyList()
                    pokemonData.clear()
                    pokemonData.addAll(datos)
                    adapter.notifyDataSetChanged()
                }else{
                    //mostrar error
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}

/*
ApiResponse = DogsResponse
Urls = APIService
PokemonAdapter = DogAdapter
*/