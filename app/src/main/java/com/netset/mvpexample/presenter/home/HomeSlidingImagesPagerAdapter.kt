package com.netset.mvpexample.presenter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

import com.netset.mvpexample.R
import com.netset.mvpexample.base.BaseActivity


class HomeSlidingImagesPagerAdapter(internal var baseActivity: BaseActivity) : PagerAdapter() {

    var inflater: LayoutInflater? = null

    override fun getCount(): Int {
        return 10
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, obje: Any) {
        (container as ViewPager).removeView(obje as RelativeLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = baseActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater!!.inflate(R.layout.adapter_sliding_images_layout, container, false)


        (container as ViewPager).addView(itemView)
        return itemView
    }

}
