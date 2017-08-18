package com.naijab.sitnotify

import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.naijab.sitnotify.base.BaseActivity
import com.naijab.sitnotify.newsmenu.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_activity_main.*

class MainActivity : BaseActivity() {

    lateinit internal var fragmentNews: NewsFragment
    lateinit internal var fragmentService: ServiceFragment
    lateinit internal var fragmentCalendar: CalendarFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = this.getString(R.string.app_name)
        setupFragment()
        replaceFragments(0)
        initBottomNavBar()
        setupBottomNavBar()
    }

    private fun setupFragment() {
        fragmentNews = NewsFragment.newInstance()
        fragmentService = ServiceFragment.newInstance()
        fragmentCalendar = CalendarFragment.newInstance()
    }

    private fun initBottomNavBar() {
        bottomNavigation
                .addItem(BottomNavigationItem(
                        R.drawable.ic_news, getString(R.string.news_menu)))
                .addItem(BottomNavigationItem(
                        R.drawable.ic_calendar, getString(R.string.calendar_menu)))
                .addItem(BottomNavigationItem(
                        R.drawable.ic_public, getString(R.string.service_menu)))
                .initialise()
    }

    private fun setupBottomNavBar() {
        bottomNavigation.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                replaceFragments(position)
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabReselected(position: Int) {
            }
        })
    }

    private fun replaceFragments(position: Int) {
        supportFragmentManager.beginTransaction().apply {
            when (position) {
                0 -> replace(R.id.container, fragmentNews)
                2 -> replace(R.id.container, fragmentCalendar)
                3 -> replace(R.id.container, fragmentService)
            }
        }.commitAllowingStateLoss()
    }
}