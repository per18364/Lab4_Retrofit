package com.example.lab4_retrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        val numeros = "0123456789"
        val espacio = " "

        btn_buscar.setOnClickListener {
            run {

                val retrofit2 = Retrofit2()

                val busqueda = search.text.toString().toLowerCase()

                if(search!!.text.any {it in numeros}) {
                    val toast = Toast.makeText(applicationContext, "La busqueda no puede contener numeros", Toast.LENGTH_SHORT)
                    toast.show()
                    vista.text = ""
                }
                if (busqueda.length > 50) {
                    val toast = Toast.makeText(applicationContext, "La busqueda no puede exceder los 50 caracteres", Toast.LENGTH_SHORT)
                    toast.show()
                    vista.text = ""
                }
                if(search!!.text.any {it in espacio}) {
                    val toast = Toast.makeText(applicationContext, "La busqueda no puede contener espacios", Toast.LENGTH_SHORT)
                    toast.show()
                    vista.text = ""
                }

                val call: Call<JsonObject> = retrofit2.getService().getPokemonById(busqueda)

                call.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        if (response.isSuccessful){
                            val pokemon: JsonObject? = response.body()
                            val name = pokemon!!["name"].asString.capitalize()

                            assert(pokemon != null)

                            var datos = ("\n Nombre: " + name
                                    + "\n Altura: ${pokemon!!["height"]}"
                                    + "\n Peso: ${pokemon!!["weight"]}"
                                    + "\n Experiencia Inicial: ${pokemon!!["base_experience"]}")
                            vista.text = datos
                            Log.i("detalle", pokemon.toString())
                        } else{0
                            Log.e("error", "Hubo un error!")
                        }

                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.e("error", t.toString())
                    }

                })

            }
        }

    }


}

