package com.example.phonelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import kotlinx.android.synthetic.main.activity_verify.*

class VerifyActivity : AppCompatActivity() {
    //保存所有显示验证码的textView
    private val verifyViews:Array<TextView> by lazy {
        arrayOf(mv1,mv2,mv3,mv4,mv5,mv6)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        //获取数据
        intent.getStringExtra("phone").also {
            //显示号码
            mPhone.text = it
        }

        //监听文本框内容改变的事件
        mOrigin.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //将输入的内容拆分到每一个textView中
                for ((i,item) in s?.withIndex()!!){
                    //获取i对应的哪个textView
                    verifyViews[i].text = item.toString()
                }

                //如果位数小于6个 后面的文本框不显示任何内容
                for (i in s.length..5){
                    verifyViews[i].text = ""
                }

                //判断输入的验证码个数是不是6个
                if (s.length == 6){
                    //发起验证的请求
                    BmobUtil.verifySmsCode(mPhone.text.toString(),s.toString()){
                        if (it == BmobUtil.SUCCESS){
                            //跳转到主页
                            startActivity(Intent(this@VerifyActivity,HomeActivity::class.java))
                        }else{
                            Toast.makeText(this@VerifyActivity,"验证失败",Toast.LENGTH_SHORT).show()
                            mOrigin.text.clear()
                        }
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        BmobUtil.requestSMSCode(mPhone.text.toString()){
            if (it == BmobUtil.SUCCESS){
                Toast.makeText(this,"发送验证码成功",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"发送验证码失败",Toast.LENGTH_SHORT).show()
            }
        }

    }
}