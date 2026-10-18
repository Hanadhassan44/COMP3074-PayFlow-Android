package com.example.paycalculator

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView

class AboutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<TextView>(R.id.studentName).text = "Hanad Hassan"
        findViewById<TextView>(R.id.studentId).text = "101337849"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
