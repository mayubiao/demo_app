package com.bs.demo.ui

import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.model.UserInfoBean
import com.bs.demo.utils.HttpUtil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setNavBarTitle("登陆")
        navigationBar?.hideBack()
        navigationBar?.setRightStyleBtnText("注册")
        navigationBar?.setNavBarRightBtnListener {
            startAct(RegisterActivity::class.java)
        }
        tv_login_submit.setOnClickListener {
            var account=edt_login_tel.text.toString()
            var pwd=edt_login_pwd.text.toString()
            if (account.isNullOrEmpty()){
                showToast(edt_login_tel.hint.toString())
                return@setOnClickListener
            }
            if (pwd.isNullOrEmpty()){
                showToast(edt_login_pwd.hint.toString())
                return@setOnClickListener
            }
            HttpUtil.newInstance()
                .addParam("account", account)
                .addParam("password", pwd)
                .post("login", object : HttpUtil.HttpListener {
                    override fun onSuccess(t: String?) {
                        showToast("登陆成功!")
                        LocalBeanInfo.refreshUserInfo(Gson().fromJson(t, UserInfoBean::class.java))
                        startAct(MainActivity::class.java)
                        finish()
                    }

                    override fun onError(msg: String?) {
                        showToast("登陆失败!")
                    }
                })
        }

    }
}
