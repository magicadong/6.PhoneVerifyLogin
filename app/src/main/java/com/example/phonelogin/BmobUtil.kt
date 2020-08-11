package com.example.phonelogin

import android.util.Log
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener

object BmobUtil {
    const val SUCCESS = 0
    const val FAILURE = 1

    //向服务器请求..发送验证码  -> 发送成功 发送失败
    fun requestSMSCode(phone:String, callBack:(Int)->Unit){
        BmobSMS.requestSMSCode(phone,"", object : QueryListener<Int>(){
            override fun done(p0: Int?, p1: BmobException?) {
                if (p1 == null){
                    //发送验证码成功
                    callBack(SUCCESS)
                }else{
                    //发送验证码失败
                    Log.v("pxd", "错误码：${p1.errorCode}  message:${p1.message}")
                    callBack(FAILURE)
                }
            }
        })
    }

    //需要验证用户输入的验证码  -> 验证成功 验证失败
    fun verifySmsCode(phone: String,code: String, callBack: (Int) -> Unit){
        BmobSMS.verifySmsCode(phone, code, object : UpdateListener(){
            override fun done(p0: BmobException?) {
                if (p0 == null){
                    //验证成功
                    callBack(SUCCESS)
                }else{
                    //验证失败
                    Log.v("pxd", "错误码：${p0.errorCode}  message:${p0.message}")
                    callBack(FAILURE)
                }
            }
        })
    }
}
