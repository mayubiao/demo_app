package com.bs.demo.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bs.demo.R
import com.bs.demo.common.base.BaseFragment
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.extension.toBeans
import com.bs.demo.model.AnnBean
import com.bs.demo.model.OrderBean
import com.bs.demo.ui.adapter.MsgListAdapter
import com.bs.demo.ui.adapter.OrderListAdapter
import com.bs.demo.utils.HttpUtil
import com.bs.demo.widget.BottomListDialog
import kotlinx.android.synthetic.main.fragment_ann.*
import kotlinx.android.synthetic.main.fragment_ann.rlv
import kotlinx.android.synthetic.main.fragment_order.*

/**
 * 公告tab
 */
class OrderFragment : BaseFragment() {
    lateinit var orderListAdapter: OrderListAdapter
    val list = mutableListOf<OrderBean>()
    override fun initView() {
        orderListAdapter = OrderListAdapter(list)
        rlv.adapter = orderListAdapter
        orderListAdapter.setOnItemClickListener { adapter, view, position ->
            val bean = list[position]
            BottomListDialog.newInstance()
                .apply {
                    this.title = "请选择"
                }
                .addData("取消订单")
                .addData("邮寄验收")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(p: Int, title: String) {
                        when (p) {
                            0 -> {
                                HttpUtil.newInstance()
                                    .addParam("id", bean.oid.toString())
                                    .post("delOrder", object : HttpUtil.HttpListener {
                                        override fun onSuccess(t: String) {
                                            showToast("取消成功")
                                            loadData()
                                        }

                                        override fun onError(msg: String?) {
                                            showToast("取消失败")
                                        }
                                    })
                            }
                            1 -> {
                                HttpUtil.newInstance()
                                    .addParam("id", bean.oid.toString())
                                    .addParam("status","待验收")
                                    .post("setOrderStatus", object : HttpUtil.HttpListener {
                                        override fun onSuccess(t: String) {
                                            showToast("提交成功")
                                            loadData()
                                        }

                                        override fun onError(msg: String?) {
                                            showToast("提交失败")
                                        }
                                    })
                            }
                        }
                    }
                }).show(childFragmentManager)
        }
    }


    override fun onResume() {
        super.onResume()
        loadData()

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden.not()) {
            loadData()
        }
    }

    fun loadData() {
        HttpUtil.newInstance()
            .addParam("id", LocalBeanInfo.userInfo?.id.toString())
            .post("getOrderList", object : HttpUtil.HttpListener {
                override fun onSuccess(t: String) {
                    list.clear()
                    list.addAll(t.toBeans(OrderBean::class.java))
                    orderListAdapter.notifyDataSetChanged()
                }

                override fun onError(msg: String?) {
                    showToast(msg)
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    companion object {
        fun newInstance() = OrderFragment().apply {}
    }

}
