package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener{
            if(binding.name.text.toString().isEmpty()){
                Toast.makeText(this, "no input", Toast.LENGTH_SHORT).show()
            }else{
                val intent= Intent(this,QuizQuestionActivity::class.java)
                intent.putExtra(Constant.mUsername,binding.name.text.toString())
                startActivity(intent)

                finish()
            }
        }
    }
}