package edu.utad.md_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import edu.utad.md_calculator.databinding.CalculatorBinding

class Calculator : AppCompatActivity() {

    private lateinit var binding: CalculatorBinding

    var operator = ""
    var previous_value = ""
    lateinit var display: TextView
    lateinit var operationHolder: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        display = binding.text
        operationHolder = binding.currentOp

        val digitListener = View.OnClickListener { view ->
            val buttonText = (view as Button).text.toString()
            display.text = display.text.toString() + buttonText
            operationHolder.text = operationHolder.text.toString() + buttonText
        }

        val operatorListener = View.OnClickListener { view ->
            val operation = (view as Button).text.toString()
            previous_value = display.text.toString()
            operator = operation
            operationHolder.text = operationHolder.text.toString() + operation
            display.text = ""
        }

        val digitButtons = arrayOf(
            binding.num0,
            binding.num1,
            binding.num2,
            binding.num3,
            binding.num4,
            binding.num5,
            binding.num6,
            binding.num7,
            binding.num8,
            binding.num9,
            binding.dot,
            binding.pi,
            binding.euler
        )

        val operatorButtons = arrayOf(
            binding.add,
            binding.minus,
            binding.times,
            binding.divide,
        )

        digitButtons.forEach { digitButton ->
            digitButton?.setOnClickListener(digitListener)
        }

        operatorButtons.forEach { operatorButton ->
            operatorButton?.setOnClickListener(operatorListener)
        }

        binding.equal.setOnClickListener {
            var result = 0.0
            when (operator) {
                "+" -> result = previous_value.toDouble() + display.text.toString().toDouble()
                "-" -> result = previous_value.toDouble() - display.text.toString().toDouble()
                "*" -> result = previous_value.toDouble() * display.text.toString().toDouble()
            }
            display.text = result.toString()
            operationHolder.text = ""
        }
        binding.reset?.setOnClickListener {
            display.text = ""
            previous_value = ""
            operator = ""
            operationHolder.text = ""
        }

    }
}