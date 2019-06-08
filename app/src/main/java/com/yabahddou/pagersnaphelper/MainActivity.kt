package com.yabahddou.pagersnaphelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = AccountsAdapter(5)
        recyclerView.adapter = adapter
        recyclerView.addPageChangeListener {
            txtPage.text = "Page ${it + 1}"
        }
    }
}

