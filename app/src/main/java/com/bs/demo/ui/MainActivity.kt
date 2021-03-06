package com.bs.demo.ui

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

class MainActivity : BaseActivity() {
    private val fragments = mutableListOf<Fragment>()
    var page = 0
    private val mFragmentContainerHelper = FragmentContainerHelper()
    private val icons = listOf(
        R.drawable.selector_tab_home,
        R.drawable.selector_tab_data,
        R.drawable.selector_tab_inspection,
        R.drawable.selector_tab_mine
    )
    lateinit var mineFragment: MineFragment
    lateinit var homeFragment: HomeFragment
    lateinit var annFragment: AnnFragment
    lateinit var orderFragment: OrderFragment
    private val titles = mutableListOf("主页","订单","公告","我的")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomMenu()
    }
    private fun initBottomMenu() {
        orderFragment= OrderFragment.newInstance()
        mineFragment = MineFragment.newInstance()
        homeFragment= HomeFragment.newInstance()
        annFragment= AnnFragment.newInstance()
        fragments.add(homeFragment)
        fragments.add(orderFragment)
        fragments.add(annFragment)
        fragments.add(mineFragment)
        bottom_navigation_bar.setBackgroundColor(Color.WHITE)
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return icons.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val commonPagerTitleView = CommonPagerTitleView(context)
                val customLayout =
                    LayoutInflater.from(context).inflate(R.layout.viewgroup_bottom_tab, null)
                val titleImg = customLayout.findViewById(R.id.iv_tab_logo) as ImageView
                val titleText = customLayout.findViewById(R.id.tv_tab_title) as TextView
                titleImg.setImageResource(icons[index])
                titleText.text = titles[index]
                commonPagerTitleView.setContentView(customLayout)
                commonPagerTitleView.setOnClickListener {
                    mFragmentContainerHelper.handlePageSelected(index)
                    showFragment(index)
                }
                commonPagerTitleView.onPagerTitleChangeListener =
                    object : CommonPagerTitleView.OnPagerTitleChangeListener {

                        override fun onSelected(index: Int, totalCount: Int) {
                            commonPagerTitleView.isActivated = true
                        }

                        override fun onDeselected(index: Int, totalCount: Int) {

                        }

                        override fun onLeave(
                            index: Int,
                            totalCount: Int,
                            leavePercent: Float,
                            leftToRight: Boolean
                        ) {
                            commonPagerTitleView.isActivated = false
                        }

                        override fun onEnter(
                            index: Int,
                            totalCount: Int,
                            enterPercent: Float,
                            leftToRight: Boolean
                        ) {

                        }
                    }
                return commonPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator? {
                return null
            }
        }

        bottom_navigation_bar.navigator = commonNavigator
        mFragmentContainerHelper.attachMagicIndicator(bottom_navigation_bar)
        showFragment(page)
    }
    fun showFragment(index: Int) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        var fragment: Fragment
        var i = 0
        val j = fragments.size
        while (i < j) {
            if (i == index) {
                i++
                continue
            }
            fragment = fragments[i]
            if (fragment.isAdded) {
                fragmentTransaction.hide(fragment)
            }
            i++
        }
        fragment = fragments[index]
        if (fragment.isAdded) {
            fragmentTransaction.show(fragment)
        } else {
            fragmentTransaction.add(R.id.frame_layout, fragment)
        }
        fragmentTransaction.commitAllowingStateLoss()

    }
}
