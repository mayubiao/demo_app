package com.bs.demo.ui.adapter

import android.widget.ImageView
import com.bs.demo.R
import com.bs.demo.extension.load
import com.bs.demo.model.OrderBean
import com.bs.demo.model.SjBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class OrderListAdapter(data: List<OrderBean>?) : BaseQuickAdapter<OrderBean, BaseViewHolder>
        (R.layout.item_order, data) {

    override fun convert(helper: BaseViewHolder, item: OrderBean) {
        helper.setText(R.id.tv_item_title,item.sjBean.title)
        if (item.baojia==null){
            helper.setText(R.id.tv_item_price,"回收报价: 暂无报价")

        }else{
            helper.setText(R.id.tv_item_price,"回收报价: ¥"+item.baojia)

        }
        helper.setText(R.id.tv_item_id,"订单号: "+item.oid)
        helper.setText(R.id.tv_item_status,item.status)

    }

}

