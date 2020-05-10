package com.bs.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.common.config.ActKeyConstants
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.extension.toBeans
import com.bs.demo.model.AnnBean
import com.bs.demo.ui.adapter.MsgListAdapter
import com.bs.demo.utils.HttpUtil
import com.bs.demo.widget.BottomListDialog
import kotlinx.android.synthetic.main.activity_add_order.*
import kotlinx.android.synthetic.main.fragment_ann.*

class AddOrderActivity : BaseActivity() {
    var id=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)
        setNavBarTitle("发布")
        id=intent.getStringExtra(ActKeyConstants.KEY_ID)
        navigationBar?.setRightStyleBtnText("提交")
        navigationBar?.setNavBarRightBtnListener {
            HttpUtil.newInstance()
                .addParam("sjid",id)
                .addParam("id",LocalBeanInfo.userInfo?.id.toString())
                .addParam("xt",tv_add_order_xt.text.toString())
                .addParam("neicun",tv_add_order_neicun.text.toString())
                .addParam("xinghao",tv_add_order_xinghao.text.toString())
                .addParam("yanse",tv_add_order_yanse.text.toString())
                .addParam("pingmuwaiguang",tv_add_order_pingmuwaiguang.text.toString())
                .addParam("shexiangtou",tv_add_order_shexiangtou.text.toString())
                .addParam("jishenwaiguang",tv_add_order_jishenwaiguang.text.toString())
                .addParam("weixiushi",tv_add_order_weixiushi.text.toString())
                .post("addOrder", object : HttpUtil.HttpListener{
                    override fun onSuccess(t: String) {
                        showToast("提交成功!")
                        finish()
                    }

                    override fun onError(msg: String?) {
                        showToast("提交失败!")
                    }
                })
        }
        tv_add_order_jishenwaiguang.setOnClickListener {
            BottomListDialog.newInstance()
                .apply { this.title="请选择" }
                .addData("完好")
                .addData("有划痕")
                .addData("明显掉漆/磕碰")
                .addData("弯曲/变形/凸起")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(postion: Int, title: String) {
                        tv_add_order_jishenwaiguang.text=title
                    }
                }).show(supportFragmentManager)
        }
        tv_add_order_neicun.setOnClickListener {
            BottomListDialog.newInstance()
                .apply { this.title="请选择" }
                .addData("32G")
                .addData("64G")
                .addData("128G")
                .addData("256G")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(postion: Int, title: String) {
                        tv_add_order_neicun.text=title
                    }
                }).show(supportFragmentManager)
        }
        tv_add_order_shexiangtou.setOnClickListener {
            BottomListDialog.newInstance()
                .apply { this.title="请选择" }
                .addData("正常")
                .addData("后摄像头坏")
                .addData("前摄像头坏")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(postion: Int, title: String) {
                        tv_add_order_shexiangtou.text=title
                    }
                }).show(supportFragmentManager)
        }
        tv_add_order_pingmuwaiguang.setOnClickListener {
            BottomListDialog.newInstance()
                .apply { this.title="请选择" }
                .addData("无划痕")
                .addData("有细微划痕")
                .addData("有明显划痕")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(postion: Int, title: String) {
                        tv_add_order_pingmuwaiguang.text=title
                    }
                }).show(supportFragmentManager)
        }
        tv_add_order_weixiushi.setOnClickListener {
            BottomListDialog.newInstance()
                .apply { this.title="请选择" }
                .addData("无")
                .addData("更换屏幕")
                .addData("更换部件")
                .addData("更换cup/主板")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(postion: Int, title: String) {
                        tv_add_order_weixiushi.text=title
                    }
                }).show(supportFragmentManager)
        }
        tv_add_order_xinghao.setOnClickListener {
            BottomListDialog.newInstance()
                .apply { this.title="请选择" }
                .addData("A")
                .addData("B")
                .addData("")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(postion: Int, title: String) {
                        tv_add_order_xinghao.text=title
                    }
                }).show(supportFragmentManager)
        }
        tv_add_order_xt.setOnClickListener {
            BottomListDialog.newInstance()
                .apply { this.title="请选择" }
                .addData("8.0")
                .addData("9.0")
                .addData("10.0")
                .addData("11.0")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(postion: Int, title: String) {
                        tv_add_order_xt.text=title
                    }
                }).show(supportFragmentManager)
        }
        tv_add_order_yanse.setOnClickListener {
            BottomListDialog.newInstance()
                .apply { this.title="请选择" }
                .addData("黑")
                .addData("灰")
                .addData("白")
                .addData("金")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(postion: Int, title: String) {
                        tv_add_order_yanse.text=title
                    }
                }).show(supportFragmentManager)
        }


    }
}
