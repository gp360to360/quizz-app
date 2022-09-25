package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnstart : Button  = findViewById<Button>(R.id.btn_start)
        val etname : AppCompatEditText = findViewById(R.id.et_name)
        btnstart.setOnClickListener{

            if(etname.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please enter the your name",Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, QuizQuestions::class.java)
                startActivity(intent)

            }
        }
    }
}