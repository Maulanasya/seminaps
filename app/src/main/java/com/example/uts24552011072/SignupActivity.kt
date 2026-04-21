package com.example.uts24552011072

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val etFullname = findViewById<EditText>(R.id.fullname)
        val etEmail = findViewById<EditText>(R.id.email)
        val etPassword = findViewById<EditText>(R.id.password)
        val etPhone = findViewById<EditText>(R.id.phonenumber)
        val rgGender = findViewById<RadioGroup>(R.id.gender)
        val cbTerm = findViewById<CheckBox>(R.id.checkbox)
        val btnRegister = findViewById<Button>(R.id.register)
        val spinner = findViewById<Spinner>(R.id.seminaropsi)

        // SetUp Spinner
        val seminar = arrayOf("Informatika", "Desain Grafis", "Bahasa Inggris", "Bahasa Jepang", "Teknik Mesin")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, seminar)

        // Validasi Realtime Email
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.error = "Email harus valid (ada @ dan .com)"
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Validasi Realtime Number Handphone (08 & 10-13 digit)
        etPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val hp = s.toString()
                if (hp.isNotEmpty()) {
                    if (!hp.startsWith("08")) {
                        etPhone.error = "Harus diawali 08"
                    } else if (hp.length < 10 || hp.length > 13) {
                        etPhone.error = "Panjang 10-13 digit"
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Tombol Register
        btnRegister.setOnClickListener {
            val name = etFullname.text.toString()

            if (name.isEmpty() || etEmail.text.isEmpty() || etPassword.text.isEmpty() || etPhone.text.isEmpty()) {
                Toast.makeText(this, "Wajib isi semua field!", Toast.LENGTH_SHORT).show()
            }
            else if (etEmail.error != null || etPhone.error != null) {
                Toast.makeText(this, "Mohon perbaiki data yang salah", Toast.LENGTH_SHORT).show()
            }
            else if (rgGender.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Pilih Jenis Kelamin!", Toast.LENGTH_SHORT).show()
            }
            else if (!cbTerm.isChecked) {
                Toast.makeText(this, "Centang syarat & ketentuan!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Registrasi Berhasil!, Silahkan lanjut login", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        findViewById<TextView>(R.id.login).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}