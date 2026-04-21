package com.example.uts24552011072

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val card1 = findViewById<CardView>(R.id.card_seminar1)

        card1.setOnClickListener {
            val intent = Intent(this, RegistrasiActivity::class.java)

            intent.putExtra("NAMA_SEMINAR", "Seminar Basic UI/UX design")

            startActivity(intent)
        }
    }
}