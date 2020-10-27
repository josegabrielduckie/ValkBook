package com.jgduckie.socialing

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.*

class Landing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.IO){
                startActivity( Intent(this@Landing,MainActivity::class.java))
            }

        }
    }
}