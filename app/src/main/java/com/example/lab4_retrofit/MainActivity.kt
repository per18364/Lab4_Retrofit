package com.example.lab4_retrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lab4_retrofit.databinding.ActivityMainBinding
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btn_buscar: Button = findViewById(R.id.btn_buscar)
        val search: EditText = findViewById(R.id.Search)
        val vista: TextView = findViewById(R.id.vistaInfo)

        btn_buscar.setOnClickListener {
            run {

                val retrofit = Retrofit2()

                val busqueda = search.text.toString().toLowerCase()

                val call: Call<JsonObject> = retrofit.getService().getPokemonById(busqueda)

                call.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                        val pokemon: JsonObject? = response.body()
                        val name = pokemon?.get(10.toString())
                        val height = pokemon?.get(4.toString())
                        val weight = pokemon?.get(17.toString())
                        val base_exp = pokemon?.get(1.toString())

                        vista.setText("\n Nombre: " + name
                                + "\n Altura: " + height
                                + "\n Peso: " + weight
                                + "\n Experiencia Inicial: " + base_exp)

                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.e("error", "Algo ha fallado...")
                    }

                })

            }
        }

    }


}
