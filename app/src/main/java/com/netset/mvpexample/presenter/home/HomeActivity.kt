package com.netset.mvpexample.presenter.home

import android.os.Bundle

import com.netset.mvpexample.R
import com.netset.mvpexample.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private var adapter: HomeSlidingImagesPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /**
         * Adding animation to ViewPager
         * */
        setPagerAnim(home_view_pager)
        /**
         * Setting View Pager Adapter
         **/
        adapter = HomeSlidingImagesPagerAdapter(this)
        home_view_pager.adapter = adapter;

    }
}
