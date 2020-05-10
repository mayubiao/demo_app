package com.bs.demo.common


import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.multidex.MultiDex

import com.bs.demo.common.base.BasePreference
import com.bs.demo.utils.LogUtil


class TApplication : Application(){

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)//突破64k限制
    }


    override fun onCreate() {
        super.onCreate()
        LogUtil.isDebug = true
        mApplication = this
        preference = BasePreference(this)
    }

    fun getPreference(): BasePreference {
        if (preference == null) {
            preference = BasePreference(this)
        }
        return preference as BasePreference
    }

    companion object {
        private var mApplication: TApplication? = null
        private var preference: BasePreference? = null
        val instance: TApplication
            get() {
                if (mApplication == null) {
                    mApplication = TApplication()
                }
                return mApplication as TApplication
            }

    }


}
