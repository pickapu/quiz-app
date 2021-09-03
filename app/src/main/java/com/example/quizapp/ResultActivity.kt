package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var bind:ActivityResultBinding
    private var usern:String?=null
    private var cans:Int?=0
    private var tques:Int?=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usern=intent.getStringExtra(Constant.mUsername)
         cans=intent.getIntExtra(Constant.correctAnswer,0)
         tques=intent.getIntExtra(Constant.totalQuestion,0)
        bind= ActivityResultBinding.inflate(LayoutInflater.from(this))
        setContentView(bind.root)
        bind.rusername.text=usern
        bind.result.text="correct answer $cans out of $tques"
        bind.resultbtn.setOnClickListener(){
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}