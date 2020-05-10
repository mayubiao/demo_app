package com.bs.demo.ui.adapter

import android.widget.ImageView
import com.bs.demo.R
import com.bs.demo.extension.load
import com.bs.demo.model.SjBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class SjListAdapter(data: List<SjBean>?) : BaseQuickAdapter<SjBean, BaseViewHolder>
        (R.layout.item_sj, data) {

    override fun convert(helper: BaseViewHolder, item: SjBean) {
        helper.setText(R.id.tv_item_title,item.title)
        helper.setText(R.id.tv_item_price,"回收价: ¥"+item.price)
        val iv=helper.getView<ImageView>(R.id.iv_item_logo)
        iv.load(item.pic)
    }

}

