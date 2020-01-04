package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private var newNumber: EditText? = null
    private var operation: TextView? = null
    private var result: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val divide = findViewById<Button>(R.id.buttonDivide)
        val multiply = findViewById<Button>(R.id.buttonMultiply)
        val minus = findViewById<Button>(R.id.buttonMinus)
        val add = findViewById<Button>(R.id.buttonPlus)

        divide.setOnClickListener{ onClick(divide) }
        multiply.setOnClickListener{ onClick(multiply) }
        minus.setOnClickListener{ onClick(minus) }
        add.setOnClickListener{ onClick(add) }

        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)

        button0.setOnClickListener{ onClick(button0) }
        button1.setOnClickListener{ onClick(button1) }
        button2.setOnClickListener{ onClick(button2) }
        button3.setOnClickListener{ onClick(button3) }
        button4.setOnClickListener{ onClick(button4) }
        button5.setOnClickListener{ onClick(button5) }
        button6.setOnClickListener{ onClick(button6) }
        button7.setOnClickListener{ onClick(button7) }
        button8.setOnClickListener{ onClick(button8) }
        button9.setOnClickListener{ onClick(button9) }


    }
    override fun onClick(v: View){
        newNumber = findViewById(R.id.newNumber)
        operation = findViewById(R.id.operation)
        result = findViewById(R.id.result)
        when (v.id) {
            R.id.buttonDivide -> {
                operation?.text = "/"
            }
            R.id.buttonMultiply -> {
                operation?.text = "*"
            }
            R.id.buttonMinus -> {
                operation?.text = "-"
            }
            R.id.buttonPlus -> {
                operation?.text = "+"
            }

            R.id.button0 -> {
                newNumber?.append("0")
            }
            R.id.button1 -> {
                newNumber?.append("1")
            }
            R.id.button2 -> {
                newNumber?.append("2")
            }
            R.id.button3 -> {
                newNumber?.append("3")
            }
            R.id.button4 -> {
                newNumber?.append("4")
            }
            R.id.button5 -> {
                newNumber?.append("5")
            }
            R.id.button6 -> {
                newNumber?.append("6")
            }
            R.id.button7 -> {
                newNumber?.append("7")
            }
            R.id.button8 -> {
                newNumber?.append("8")
            }
            R.id.button9 -> {
                newNumber?.append("9")
            }
        }
    }
}
