package com.example.baslangishmatematika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_finsh.*

class FinshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finsh)
        tvCount.text = "Sorawlar sani: " + intent.getIntExtra("countQuestions", 0).toString()
        countRight.text = "Duris juwaplar sani: " + intent.getIntExtra("countRightQuestions", 0).toString()
        countWrong.text = "Qate juwaplar sani: " + intent.getIntExtra("countWrongQuestions", 0).toString()
        prossent.text = intent.getDoubleExtra("prossent", 0.0).toString() + "%"

        buttonNewGame.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        KeyEvent.KEYCODE_BACK.apply {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        var intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}