package com.example.uts24552011072

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // 1. Tangkap semua data dari Intent
        val nama = intent.getStringExtra("EXTRA_NAMA")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val phone = intent.getStringExtra("EXTRA_PHONE")
        val gender = intent.getStringExtra("EXTRA_GENDER")
        val seminar = intent.getStringExtra("EXTRA_SEMINAR")

        // TANGKAP DATA METODE DISINI
        val metode = intent.getStringExtra("EXTRA_METODE")

        // 2. Tampilkan ke TextView masing-masing
        findViewById<TextView>(R.id.res_nama).text = nama
        findViewById<TextView>(R.id.res_email).text = email
        findViewById<TextView>(R.id.res_phone).text = phone
        findViewById<TextView>(R.id.res_gender).text = gender
        findViewById<TextView>(R.id.res_seminar).text = seminar

        // SET TEXT SESUAI DATA YANG DITANGKAP (Online/Offline)
        findViewById<TextView>(R.id.res_metode).text = metode

        // 3. Tombol Kembali
        findViewById<Button>(R.id.btn_back_home).setOnClickListener {
            val intentHome = Intent(this, MainActivity::class.java)
            intentHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intentHome)
            finish()
        }
    }
}