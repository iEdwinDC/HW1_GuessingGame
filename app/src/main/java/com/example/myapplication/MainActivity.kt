package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    private var res = 0
    private var tries = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        res = (1..1000).random()
        initViews()
    }

    private fun initViews() {
        val buttonPlayAgain: Button = findViewById(R.id.button_playAgain)
        val text: TextView = findViewById(R.id.text)
        val triesText: TextView = findViewById(R.id.text_tries)
        val editText: EditText = findViewById(R.id.editText_algo)

        editText.addTextChangedListener {
            handler.removeCallbacksAndMessages(null)
            handler.postDelayed({
                if (it?.toString().orEmpty().isNotEmpty()) {
                    when {
                        (it.toString().toInt() < res) -> {
                            tries++
                            triesText.text = tries.toString()
                            text.text = "Hint: It's higher!"
                        }
                        (it.toString().toInt() > res) -> {
                            tries++
                            triesText.text = tries.toString()
                            text.text = "Hint: It's lower"
                        }
                        else -> {
                            text.text = "Winner!!"
                            buttonPlayAgain.visibility = View.VISIBLE

                        }
                    }
                }
            }, 800)
        }

        buttonPlayAgain.setOnClickListener {

            res = (1..1000).random()
            text.text = ""
            buttonPlayAgain.visibility = View.GONE
            tries = 0
            triesText.text = ""
            editText.text = Editable.Factory.getInstance().newEditable("")
        }
    }
}