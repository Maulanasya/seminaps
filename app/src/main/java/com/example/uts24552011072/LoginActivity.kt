package com.example.uts24552011072

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Inisialisasi View
        val etEmail = findViewById<EditText>(R.id.email)
        val etPassword = findViewById<EditText>(R.id.password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val tvSignUp = findViewById<TextView>(R.id.sign_up)

        // 2. Aksi tombol Login
        btnLogin.setOnClickListener {
            val emailInput = etEmail.text.toString().trim()
            val passwordInput = etPassword.text.toString().trim()

            // --- TAHAP VALIDASI KOSONG ---
            if (emailInput.isEmpty()) {
                etEmail.error = "Email tidak boleh kosong!"
                etEmail.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                etEmail.error = "Format email salah!"
                etEmail.requestFocus()
            } else if (passwordInput.isEmpty()) {
                etPassword.error = "Password tidak boleh kosong!"
                etPassword.requestFocus()
            }

            // --- TAHAP CEK EMAIL & PASSWORD ---
            else {
                if (emailInput == "maulanasyawaludin@gmail.com" && passwordInput == "12345") {
                    Toast.makeText(this, "Selamat Datang!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Jika Salah (Muncul Notif Dialog)
                    showErrorDialog("Akses Ditolak", "Email atau Password yang kamu masukkan salah.")
                }
            }
        }

        // 3. Aksi pindah ke halaman Sign Up
        tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    // Fungsi untuk memunculkan notifikasi pop-up kalau salah
    private fun showErrorDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Coba Lagi") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }
}