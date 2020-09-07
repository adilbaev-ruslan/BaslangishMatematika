package com.example.baslangishmatematika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var countQuestions: Int = 10
    private var countAnswerQuestions: Int = 1
    private var countRightQuestions: Int = 0
    private var countWrongQuestions: Int = 0
    private var firstNumber: Int = 0
    private var secondNumber: Int = 0
    private var argument: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playGame()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("firstNumber", firstNumber)
            putInt("secondNumber", secondNumber)
            putString("argument", argument)
            putInt("countQuestions", countQuestions)
            putInt("countAnswerQuestions", countAnswerQuestions)
            putInt("countRightQuestions", countRightQuestions)
            putInt("countWrongQuestions", countWrongQuestions)
            putString("tvCountQuestions", tvCountQuestions.text.toString())
            putString("tvFirstNumber", tvFirstNumber.text.toString())
            putString("tvOrgument", tvOrgument.text.toString())
            putString("tvSecondNumber", tvSecondNumber.text.toString())
            putString("buttonA", buttonA.text.toString())
            putString("buttonB", buttonB.text.toString())
            putString("buttonC", buttonC.text.toString())
            putString("buttonD", buttonD.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        firstNumber = savedInstanceState.getInt("firstNumber")
        secondNumber = savedInstanceState.getInt("secondNumber")
        argument = savedInstanceState.getString("argument").toString()
        countQuestions = savedInstanceState.getInt("countQuestions")
        countAnswerQuestions = savedInstanceState.getInt("countAnswerQuestions")
        countRightQuestions = savedInstanceState.getInt("countRightQuestions")
        countWrongQuestions = savedInstanceState.getInt("countWrongQuestions")
        tvFirstNumber.text = savedInstanceState.getString("tvFirstNumber")
        tvOrgument.text = savedInstanceState.getString("tvOrgument")
        tvSecondNumber.text = savedInstanceState.getString("tvSecondNumber")
        buttonA.text = savedInstanceState.getString("buttonA")
        buttonB.text = savedInstanceState.getString("buttonB")
        buttonC.text = savedInstanceState.getString("buttonC")
        buttonD.text = savedInstanceState.getString("buttonD")
        tvCountQuestions.text = savedInstanceState.getString("tvCountQuestions")
    }
    private fun playGame(){
        if (countAnswerQuestions <= countQuestions) {
            argument = generateRandomOrgument()
            if (argument == "/") {
                firstNumber = generateRandomNumber(10, 100)
                var secondNumberArrayes: ArrayList<Int> = arrayListOf()
                for (i in 1 .. firstNumber) {
                    if (firstNumber % i == 0) {
                        secondNumberArrayes.add(i)
                    }
                }
                val id = generateRandomNumber(0, secondNumberArrayes.size - 1)
                secondNumber = secondNumberArrayes.get(id)
            } else {
                firstNumber = generateRandomNumber(10, 100)
                secondNumber = generateRandomNumber(10, firstNumber)
            }
            tvFirstNumber.text = firstNumber.toString()
            tvOrgument.text = argument
            tvSecondNumber.text = secondNumber.toString()
            tvCountQuestions.text = (countAnswerQuestions).toString() + "/" + countQuestions.toShort()
            getButtonAnswer()
            setButtonAnswer()
        } else {
            var intent = Intent(this, FinshActivity::class.java)
            intent.putExtra("countQuestions", countQuestions)
            intent.putExtra("countRightQuestions", countRightQuestions)
            intent.putExtra("countWrongQuestions", countWrongQuestions)
            intent.putExtra("prossent", (countRightQuestions / (countQuestions * 1.0)) * 100)
            startActivity(intent)
        }
    }

    fun variantClick(button: View) {
        if (countAnswerQuestions <= countQuestions) {
            if ((button as Button).text == getAnswer().toString()) {
                countAnswerQuestions++
                countRightQuestions++
                Toast.makeText(this, "Duris", Toast.LENGTH_SHORT).show()
            } else {
                countAnswerQuestions++
                countWrongQuestions++
                Toast.makeText(this, "Qate", Toast.LENGTH_SHORT).show()
            }
            playGame()
        }
    }
    fun generateRandomNumber(start: Int, end: Int): Int = Random.nextInt(start, end)

    private fun generateRandomOrgument(): String{
        return when(generateRandomNumber(0, 4)) {
            0 -> "+"
            1 -> "-"
            2 -> "*"
            3 -> "/"
            else -> "+"
        }
    }
    private fun getAnswer(): Int {
        return when(argument) {
            "+" -> (firstNumber + secondNumber)
            "-" -> (firstNumber - secondNumber)
            "*" -> (firstNumber * secondNumber)
            "/" -> (firstNumber / secondNumber)
            else -> (firstNumber + secondNumber)
        }
    }
    private fun getButtonAnswer(){
        var answer: Int = getAnswer()
        val firstButton = when (generateRandomNumber(0, 2)) {
            0 -> (abs(answer - generateRandomNumber(1, 10))).toString()
            1 -> (answer + generateRandomNumber(1, 10)).toString()
            else -> (answer + generateRandomNumber(1, 10)).toString()
        }
        val secondButton = when (generateRandomNumber(0, 2)) {
            0 -> (abs(answer - generateRandomNumber(1, 10))).toString()
            1 -> (answer + generateRandomNumber(1, 10)).toString()
            else -> (answer + generateRandomNumber(1, 10)).toString()
        }
        val threeButton = when (generateRandomNumber(0, 2)) {
            0 -> (abs(answer - generateRandomNumber(1, 10))).toString()
            1 -> (answer + generateRandomNumber(1, 10)).toString()
            else -> (answer + generateRandomNumber(1, 10)).toString()
        }
        val fourButton = when (generateRandomNumber(0, 2)) {
            0 -> (abs(answer - generateRandomNumber(1, 10))).toString()
            1 -> (answer + generateRandomNumber(1, 10)).toString()
            else -> (answer + generateRandomNumber(1, 10)).toString()
        }
        buttonA.text = firstButton
        buttonB.text = secondButton
        buttonC.text = threeButton
        buttonD.text = fourButton
    }
    private fun setButtonAnswer(){
        var randomButton:Int = generateRandomNumber(0, 4)
        val answer = getAnswer()
        return when(randomButton) {
            0 -> buttonA.text = answer.toString()
            1 -> buttonB.text = answer.toString()
            2 -> buttonC.text = answer.toString()
            3 -> buttonD.text = answer.toString()
            else -> buttonA.text = answer.toString()
        }
    }

}