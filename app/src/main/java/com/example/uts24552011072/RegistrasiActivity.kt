package com.example.uts24552011072

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class RegistrasiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)

        val tvJudulSeminar = findViewById<TextView>(R.id.tv_nama_seminar)
        val etNama = findViewById<EditText>(R.id.et_nama)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPhone = findViewById<EditText>(R.id.et_phone)
        val rgGender = findViewById<RadioGroup>(R.id.rg_gender)
        val spSeminar = findViewById<Spinner>(R.id.sp_seminar)
        val cbSetuju = findViewById<CheckBox>(R.id.cb_setuju)
        val btnSubmit = findViewById<Button>(R.id.btn_submit)

        // Setup Spinner Pilihan (Online / Offline)
        val listPilihan = arrayOf("Online", "Offline")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listPilihan)
        spSeminar.adapter = adapter

        val namaSeminar = intent.getStringExtra("NAMA_SEMINAR") ?: "Seminar Umum"
        tvJudulSeminar.text = namaSeminar

        // 4. Real-time Validation untuk Nomor HP
        etPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                if (input.isNotEmpty()) {
                    if (!input.startsWith("08")) {
                        etPhone.error = "Harus diawali dengan 08"
                    } else if (input.length < 10 || input.length > 13) {
                        etPhone.error = "Panjang harus 10-13 digit"
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 5. Logika Tombol Submit
        btnSubmit.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val selectedGenderId = rgGender.checkedRadioButtonId

            if (nama.isEmpty()) {
                etNama.error = "Nama wajib diisi"
                etNama.requestFocus()
            }
            else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Email harus valid (mengandung @)"
                etEmail.requestFocus()
            }
            else if (phone.isEmpty() || etPhone.error != null) {
                etPhone.error = etPhone.error ?: "Nomor HP wajib diisi"
                etPhone.requestFocus()
            }
            else if (selectedGenderId == -1) {
                Toast.makeText(this, "Pilih jenis kelamin!", Toast.LENGTH_SHORT).show()
            }
            else if (!cbSetuju.isChecked) {
                Toast.makeText(this, "Centang persetujuan data benar!", Toast.LENGTH_SHORT).show()
            }
            else {
                AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah data yang Anda isi sudah benar?")
                    .setPositiveButton("Ya") { _, _ ->
                        val rbSelected = findViewById<RadioButton>(selectedGenderId)
                        val gender = rbSelected.text.toString()
                        val metode = spSeminar.selectedItem.toString()

                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("EXTRA_NAMA", nama)
                        intent.putExtra("EXTRA_EMAIL", email)
                        intent.putExtra("EXTRA_PHONE", phone)
                        intent.putExtra("EXTRA_GENDER", gender)
                        intent.putExtra("EXTRA_SEMINAR", "$namaSeminar ($metode)")
                        intent.putExtra("EXTRA_METODE", metode)

                        startActivity(intent)
                    }
                    .setNegativeButton("Tidak", null)
                    .show()
            }
        }
    }
}