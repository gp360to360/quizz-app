package com.example.quizapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestions : AppCompatActivity() , View.OnClickListener {

    private var mquestionsList:ArrayList<Question>? = null

    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestion : TextView? = null
    private var ivImage : ImageView ? = null

    private var tvoptionOne : TextView? = null
    private var tvoptionTwo : TextView? = null
    private var tvoptionThree : TextView? = null
    private var tvoptionFour : TextView? = null
    private var btnSubmit : Button? = null
    private var mcurretnpostion: Int = 1
    private var mquestionList : ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mcorrectOption : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvoptionOne = findViewById(R.id.tv_option_one)
        tvoptionTwo = findViewById(R.id.tv_option_two)
        tvoptionThree = findViewById(R.id.tv_option_three)
        tvoptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)
        mquestionsList = Constants.getquestion()
        setQuestion()
        tvoptionOne?.setOnClickListener(this)
        tvoptionTwo?.setOnClickListener(this)
        tvoptionThree?.setOnClickListener(this)
        tvoptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)




    }

    private fun setQuestion() {


        val mcurrentposition:     Int =1
        val question: Question = mquestionsList!![mcurrentposition - 1]
        defaultOptionsViews()

        if(mcurrentposition == mquestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }
        else
        {
            btnSubmit?.text = "SUBMIT"
        }
            ivImage?.setImageResource(question.image)
            progressBar?.progress = mcurrentposition
        ("$mcurrentposition" + "/" + progressBar?.max).also { tvProgress?.text = it }
            tvQuestion?.text = question.question
            tvoptionOne?.text = question.optionOne
            tvoptionTwo?.text = question.optionTwo
            tvoptionThree?.text = question.optionThree
            tvoptionFour?.text = question.oprtionFour

        }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                tvoptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option_two -> {
                tvoptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option_three -> {
                tvoptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option_four -> {
                tvoptionFour?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mcurretnpostion++
                    when {
                        mcurretnpostion <= mquestionsList!!.size -> {
                            setQuestion()
                        }
                        else ->{
                            Toast.makeText(this@QuizQuestions, "You have successfully completed the quiz.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else
                {
                    val question = mquestionsList?.get(mcurretnpostion-1)
                    if(question!!.correctAnswere!= mSelectedOptionPosition){
                        answereView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    }
                    else
                    {
                        mcorrectOption++
                    }
                    answereView(question.correctAnswere,R.drawable.correct_option_border_bg)
                    if(mcurretnpostion == mquestionsList!!.size)
                    {
                        btnSubmit?.text = "FINISH"
                    }
                    else
                    {
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }


            }



        }

    }
    private fun answereView(answer: Int,drawableView : Int){
        when(answer){
            1 -> {
                tvoptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tvoptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvoptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tvoptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
    private fun selectedOptionView(tv:TextView,SelectedOptionNum: Int)
    {
        defaultOptionsViews()
        mSelectedOptionPosition = SelectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestions,
            R.drawable.selected_option_bg
        )
    }

    private  fun defaultOptionsViews(){
        val options = ArrayList<TextView>()
       tvoptionOne?.let {
           options.add(0,it)
       }
        tvoptionTwo?.let {
            options.add(1,it)
        }
        tvoptionThree?.let {
            options.add(2,it)
        }
        tvoptionFour?.let {
            options.add(3,it)
        }
        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestions,
                        R . drawable . default_option_border_bg

            )
        }

    }





}


