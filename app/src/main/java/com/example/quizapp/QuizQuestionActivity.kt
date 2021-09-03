package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.os.IResultReceiver
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityMainBinding.bind
import com.example.quizapp.databinding.ActivityQuizQuestionBinding
import com.example.quizapp.databinding.ActivityQuizQuestionBinding.bind
import kotlin.reflect.typeOf

class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var bind:ActivityQuizQuestionBinding
    private var mCurrentPosition:Int=1
    private var mQuestionList:ArrayList<question>?=null
    private var mSelectedOptionPoistion:Int=0
    private var mcorresctAnswer:Int=0
   private  var usernam:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        bind= ActivityQuizQuestionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        usernam=intent.getStringExtra(Constant.mUsername)
        setContentView(bind.root)
        mQuestionList=Constant.getQuestion()
        setQuestion()
        bind.opt1.setOnClickListener(this)
        bind.opt2.setOnClickListener(this)
        bind.opt3.setOnClickListener(this)
        bind.opt4.setOnClickListener(this)
        bind.submit.setOnClickListener(this)

    }
    private fun setQuestion(){

defaultOptionView()
        if(mCurrentPosition==mQuestionList!!.size){
            bind.submit.text="finsih"

        }else{
            bind.submit.text="submit"
        }
        val ques=mQuestionList!![mCurrentPosition-1]
        bind.progressbar.progress=mCurrentPosition
        bind.progress.text="$mCurrentPosition"+"/"+bind.progressbar.max
        bind.ques.text=ques!!.question
        bind.flagimg.setImageResource(ques.image)
        bind.opt1.text=ques.optionOne
        bind.opt2.text=ques.optiontwo
        bind.opt3.text=ques.optionthree
        bind.opt4.text=ques.optionFour
    }
    private fun defaultOptionView(){
        val option=ArrayList<TextView>()
        option.add(0,bind.opt1)
        option.add(1,bind.opt2)
        option.add(2,bind.opt3)
        option.add(3,bind.opt4)
        for(option in option){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,

                R.drawable.default_option_border_bg
            )
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.opt1->{
                selectedOptionView(bind.opt1,1)
            }
            R.id.opt2->{
                selectedOptionView(bind.opt2,2)
            }
            R.id.opt3->{
                selectedOptionView(bind.opt3,3)
            }
            R.id.opt4-> {
                selectedOptionView(bind.opt4, 4)
            }
            R.id.submit->{
                if(mSelectedOptionPoistion==0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition<=mQuestionList!!.size->{
                            setQuestion()
                        }else->{
                          val intent= Intent(this ,ResultActivity::class.java)
                        intent.putExtra(Constant.mUsername,usernam)
                        intent.putExtra(Constant.correctAnswer,mcorresctAnswer)
                        intent.putExtra(Constant.totalQuestion, mQuestionList!!.size)
                        startActivity(intent)
                        finish()

                        }
                    }
                }else{
                    val question=mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer!=mSelectedOptionPoistion){
                        answerView(mSelectedOptionPoistion,R.drawable.wrong_option_border_bg)
                    }else{
                        mcorresctAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)
                    if (mCurrentPosition==mQuestionList!!.size){
                        bind.submit.text="finish"
                    }
                    else{
                        bind.submit.text="go to next question"
                    }
                    mSelectedOptionPoistion=0
                }

            }

        }

    }
    private fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1->{
                bind.opt1.background=ContextCompat.getDrawable(this,drawableView)
            }
            2->{
                bind.opt2.background=ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                bind.opt3.background=ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                bind.opt4.background=ContextCompat.getDrawable(this,drawableView)
            }

        }
    }
    private fun selectedOptionView(tv:TextView,selectoptionNum:Int){
        defaultOptionView()
        mSelectedOptionPoistion=selectoptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,

            R.drawable.selected_option_border_bg
        )

    }
}