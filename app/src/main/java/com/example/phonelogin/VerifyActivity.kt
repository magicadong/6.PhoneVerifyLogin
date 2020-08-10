package com.example.phonelogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class VerifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        //获取数据
        intent.getStringExtra("phone").also {
            Log.v("pxd",it)
        }
    }
}