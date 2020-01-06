package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
// this import allows widgets to be accessed without declaration
import kotlinx.android.synthetic.main.activity_main.*

private const val STATE_PENDING_OPERATION = "PendingOperation"
private const val STATE_OPERAND1 = "Operand1"
private const val STATE_OPERAND1_STORED = "Operand1_Stored"

class MainActivity : AppCompatActivity(){
    // private var newNumber: EditText? = null
    // private var operation: TextView? = null
    // The question mark, marks the var as nullable
//    private lateinit var result: EditText
//    private lateinit var newNumber: EditText
    // Different way to assign value to display operation using a function
    // Only gets called when displayOperation is first accessed
//    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    // Variables to hold the operands and type of calculation
    private var operand1: Double? = null
    private var pendingOperation = "="
    private var isNegative = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        result = findViewById(R.id.result)
//        newNumber = findViewById(R.id.newNumber)
//
//        // Data input buttons
//        // button0: Button and findViewById<Button>
//        val button0: Button = findViewById(R.id.button0)
//        val button1: Button = findViewById(R.id.button1)
//        val button2: Button = findViewById(R.id.button2)
//        val button3: Button = findViewById(R.id.button3)
//        val button4: Button = findViewById(R.id.button4)
//        val button5: Button = findViewById(R.id.button5)
//        val button6: Button = findViewById(R.id.button6)
//        val button7: Button = findViewById(R.id.button7)
//        val button8: Button = findViewById(R.id.button8)
//        val button9: Button = findViewById(R.id.button9)
//        val buttonDot: Button = findViewById(R.id.buttonDot)
//
//        // Operation buttons
//        val equals = findViewById<Button>(R.id.buttonEquals)
//        val divide = findViewById<Button>(R.id.buttonDivide)
//        val multiply = findViewById<Button>(R.id.buttonMultiply)
//        val minus = findViewById<Button>(R.id.buttonMinus)
//        val add = findViewById<Button>(R.id.buttonPlus)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, op)
            } catch (e: NumberFormatException) {
                newNumber.setText("")
            }
            pendingOperation = op
            operation.text = pendingOperation
        }

        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)

        val negListener = View.OnClickListener {
            var numberInput = newNumber.text.toString()
            if (isNegative) {
                numberInput = numberInput.drop(1)
                Log.d("isNegative", numberInput)
                newNumber.setText(numberInput)
                isNegative = false
            } else {
                val neg = "-"
                numberInput = "$neg$numberInput"
                Log.d("notNegative", numberInput)
                newNumber.setText(numberInput)
                isNegative = true
            }
        }

        buttonNeg.setOnClickListener(negListener)

        val clearListener = View.OnClickListener {
            operand1 = null
            pendingOperation = "="
            newNumber.setText("")
            result.setText("")
            operation.text = ""
        }

        buttonClear?.setOnClickListener(clearListener)
    }

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = operation
                isNegative = false
            }

            when (pendingOperation) {
                "=" -> {
                    operand1 = value
                }
                "/" -> operand1 = if (value == 0.0) {
                                    Double.NaN // handle attempt to divide by zero
                                } else {
                                    operand1!! / value
                                }
                "*" -> operand1 = operand1!! * value // !! Bang bang operator (return non nullable type)
                "-" -> operand1 = operand1!! - value // Bang bang is safe to use because we check for null
                "+" -> operand1 = operand1!! + value
            }
        }
        result.setText(operand1.toString())
        newNumber.setText("")
        isNegative = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (operand1 != null) {
            // Question mark is safer than bang bang but we can be sure its not null because save state
            // is not null because it is created after createState
            outState.putDouble(STATE_OPERAND1, operand1!!)
            outState.putBoolean(STATE_OPERAND1_STORED, true)
        }
        outState.putString(STATE_PENDING_OPERATION, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        operand1 = if (savedInstanceState.getBoolean(STATE_OPERAND1_STORED, false)){
            savedInstanceState.getDouble(STATE_OPERAND1)
        } else {
            null
        }
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1)

        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION, "=")
        operation.text = pendingOperation
    }
}
