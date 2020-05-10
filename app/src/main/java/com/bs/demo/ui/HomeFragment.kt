package com.bs.demo.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bs.demo.R
import com.bs.demo.common.base.BaseFragment
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.extension.addSpaceDivider
import com.bs.demo.extension.loadCircle
import com.bs.demo.extension.toBean
import com.bs.demo.extension.toBeans
import com.bs.demo.model.AnnBean
import com.bs.demo.model.SjBean
import com.bs.demo.model.UserInfoBean
import com.bs.demo.ui.adapter.HomeFunsListAdapter
import com.bs.demo.ui.adapter.MsgListAdapter
import com.bs.demo.ui.adapter.SjListAdapter
import com.bs.demo.utils.HttpUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_ann.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.rlv

/**
 * 主页tab
 */

class HomeFragment : BaseFragment() {
    lateinit var sjListAdapter: SjListAdapter
    val list= mutableListOf<SjBean>()
    override fun initView() {
        sjListAdapter= SjListAdapter(list)
        rlv.adapter=sjListAdapter
        rlv.addSpaceDivider(10f,true)
        sjListAdapter.setOnItemClickListener { adapter, view, position ->
            startActWithId(AddOrderActivity::class.java,list[position].sjid.toString())
        }
        iv_home_search.setOnClickListener {
            loadData()
        }
    }
    fun loadData(){
        HttpUtil.newInstance()
            .addParam("key",edt_home_search_input.text.toString())
            .post("getSjList", object : HttpUtil.HttpListener{
                override fun onSuccess(t: String) {
                    val beans=t.toBeans(SjBean::class.java)
                    list.clear()
                    list.addAll(beans)
                    sjListAdapter.notifyDataSetChanged()
                }

                override fun onError(msg: String?) {
                    showToast(msg)
                }
            })
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden.not()){
            loadData()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        loadData()

    }


    companion object {
        fun newInstance() = HomeFragment().apply {}
    }
}
