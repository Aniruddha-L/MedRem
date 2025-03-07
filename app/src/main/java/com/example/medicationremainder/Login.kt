package com.example.medicationremainder

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        val username = findViewById<EditText>(R.id.email)
        val passwd = findViewById<EditText>(R.id.passwd)
        val loginBtn = findViewById<Button>(R.id.login)

        loginBtn.setOnClickListener {
            val emailText = username.text.toString().trim()
            val passwdText = passwd.text.toString().trim()

            if (emailText.isEmpty() || passwdText.isEmpty()) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(emailText, passwdText)
            }
        }
    }
    private fun loginUser(email:String, passwd:String){
        auth.signInWithEmailAndPassword(email, passwd).
        addOnCompleteListener(this) { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Login Failed:${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}