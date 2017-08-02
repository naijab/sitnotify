package com.naijab.sitnotify

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SchemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}