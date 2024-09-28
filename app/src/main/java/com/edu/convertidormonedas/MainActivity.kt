package com.edu.convertidormonedas

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val items = listOf("€","$","£")
        /*MENU DESPLEGABLE DE DIVISAS*/
        val autoComplete: AutoCompleteTextView = findViewById(R.id.auto_Complete)
        val adapter = ArrayAdapter(this, R.layout.listitems, items)
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, i, _ ->
            val itemSelected = adapterView.getItemAtPosition(i)
            Toast.makeText(this, "Seleccionaste $itemSelected", Toast.LENGTH_SHORT).show()
        }
        /*MENU DESPLEGABLE DE CONVERSION*/
        val autoComplete2: AutoCompleteTextView = findViewById(R.id.auto_Complete2)
        val adapter2 = ArrayAdapter(this, R.layout.listitems, items)
        autoComplete2.setAdapter(adapter2)
        autoComplete2.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, i, _ ->
            val itemSelected = adapterView.getItemAtPosition(i)
            Toast.makeText(this, "Conversión a $itemSelected", Toast.LENGTH_SHORT).show()
        }
        /*BOTON CONVERTIR*/
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            var cantidadaconvertir=0
            var conversion=0
            val cantcon = findViewById<EditText>(R.id.cantcon)
            val converted = findViewById<EditText>(R.id.converted)

        /*CONDICION PARA IMPEDIR QUE EL PROGRAMA PARE DE FORMA ABRUPTA*/
            if (cantcon.text.toString().isNotEmpty()&&autoComplete2.text.toString().isNotEmpty()){

                val cantcon2 = cantcon.text.toString().toDouble()
                val opcionelegida =autoComplete.text.toString()
                val opcionelegida2 =autoComplete2.text.toString()
              var valor = String.format("%.2f",cantcon2)
                when (opcionelegida) {
                "€" -> {cantidadaconvertir=1}
                "$" -> {cantidadaconvertir=2}
                "£" -> {cantidadaconvertir=3}
            }
            when (opcionelegida2) {
                "€" -> {conversion=1}
                "$" -> {conversion=2}
                "£" -> {conversion=3}
            }
            if (cantidadaconvertir==1 && conversion==1){
                converted.setText(valor)
            }else if (cantidadaconvertir==1 && conversion==2){
                valor=String.format("%.2f",cantcon2*1.12)
                converted.setText(valor)
            }else if (cantidadaconvertir==1 && conversion==3){
                valor=String.format("%.2f",cantcon2*0.83)
                converted.setText(valor)
            }
            if (cantidadaconvertir==2 && conversion==1){
                valor=String.format("%.2f",cantcon2*0.88)
                converted.setText(valor)
            }else if (cantidadaconvertir==2 && conversion==2){
                valor=String.format("%.2f",cantcon2)
                converted.setText(valor)
            }else if (cantidadaconvertir==2 && conversion==3){
                valor=String.format("%.2f",cantcon2*1.25)
                converted.setText(valor)
            }
            if (cantidadaconvertir==3 && conversion==1){
                valor=String.format("%.2f",cantcon2*1.22)
                converted.setText(valor)
            }else if (cantidadaconvertir==3 && conversion==2){
                valor=String.format("%.2f",cantcon2*1.34)
                converted.setText(valor)
                }else if (cantidadaconvertir==3 && conversion==3){
                valor=String.format("%.2f",cantcon2)
                converted.setText(valor)
                }
            }
         }
        /* PONER LINK A UN TEXTVIEW */
        val jeje=findViewById<TextView>(R.id.jeje)
        jeje.setOnClickListener{
            jeje.setLinkTextColor(Color.RED)
            /*declaramos un intent para llamara a otro servicio y así abrir la página web*/
            /* y ahora convertimos la cadena url en un objeto uri mediante URI PARSE*/
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
            //y por ultimo llamamos a la start activity para empezar la actividad.
            startActivity(intent);
        }
    }
}
