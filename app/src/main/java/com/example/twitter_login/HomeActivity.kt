package com.example.twitter_login

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    var name: TextView? = null
    var user: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        user = intent.getStringExtra("username")
        name = findViewById<View>(R.id.nametextView) as TextView
        name!!.text = user
    }
}