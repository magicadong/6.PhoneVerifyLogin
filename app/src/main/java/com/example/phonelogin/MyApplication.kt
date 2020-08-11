package com.example.phonelogin

import android.app.Application
import cn.bmob.v3.Bmob

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        //第一：默认初始化
        Bmob.initialize(this, "e22eb1572fa151ed205d212b10a432e7")
    }
}