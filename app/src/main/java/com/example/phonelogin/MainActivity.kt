package com.example.phonelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var shouldAutoSplit = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPhoneEdeitText.addTextChangedListener(object : LoginTextWatcher(){
            override fun afterTextChanged(s: Editable?) {
                //设置按钮是否可以点击
                mLogin.isEnabled = (s.toString().length == 13)

                //判断是在删除还是输入
                if (!shouldAutoSplit)return

                s.toString().length.also {
                    if (it == 3 || it == 8){
                        //134 2345
                        //需要自动添加空格
                        s?.append(' ')
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                shouldAutoSplit = (count == 1)
            }
        })

        //按钮的点击事件
        mLogin.setOnClickListener{
            Intent().apply {
                //跳转方向
                setClass(this@MainActivity,VerifyActivity::class.java)
                //配置跳转携带的数据
                putExtra("phone",getPhoneNumber(mPhoneEdeitText.text))
                //启动
                startActivity(this)
            }
        }
    }

    //将格式化的内容转化为正常数据
    //123 4567 8901  -> 12345678901
    private fun getPhoneNumber(editable: Editable):String{
        //创建一个新的对象 用于操作editable对象里面的内容
        SpannableStringBuilder(editable.toString()).also {
            //1234566 2341
            it.delete(3,4)
            //1234566 2341
            it.delete(7,8)
            return it.toString()
        }

    }
}

open class LoginTextWatcher: TextWatcher{
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}