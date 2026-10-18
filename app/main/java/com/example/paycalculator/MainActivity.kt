package com.example.paycalculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hoursInput = findViewById<EditText>(R.id.hoursInput)
        val rateInput = findViewById<EditText>(R.id.rateInput)
        val taxInput = findViewById<EditText>(R.id.taxInput)

        val payOutput = findViewById<TextView>(R.id.payOutput)
        val overtimeOutput = findViewById<TextView>(R.id.overtimeOutput)
        val totalOutput = findViewById<TextView>(R.id.totalOutput)
        val taxOutput = findViewById<TextView>(R.id.taxOutput)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val aboutButton = findViewById<Button>(R.id.aboutButton)

        calculateButton.setOnClickListener {
            val hours = hoursInput.text.toString().trim().toDoubleOrNull()
            val rate = rateInput.text.toString().trim().toDoubleOrNull()
            val taxRate = taxInput.text.toString().trim().toDoubleOrNull()

            if (hours == null || rate == null || taxRate == null) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!hours.isFinite() || !rate.isFinite() || !taxRate.isFinite() ||
                hours < 0 || rate < 0 || taxRate < 0 || taxRate > 100
            ) {
                Toast.makeText(this, "Please check your input values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pay: Double
            val overtimePay: Double

            if (hours <= 40) {
                pay = hours * rate
                overtimePay = 0.0
            } else {
                pay = 40 * rate
                overtimePay = (hours - 40) * rate * 1.5
            }

            val totalPay = pay + overtimePay
            val tax = pay * (taxRate / 100)

            payOutput.text = String.format(Locale.US, "$%.2f", pay)
            overtimeOutput.text = String.format(Locale.US, "$%.2f", overtimePay)
            totalOutput.text = String.format(Locale.US, "$%.2f", totalPay)
            taxOutput.text = String.format(Locale.US, "$%.2f", tax)
        }

        aboutButton.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
}
