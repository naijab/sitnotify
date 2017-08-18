package com.naijab.sitnotify

import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.naijab.sitnotify.base.BaseActivity
import com.naijab.sitnotify.newsmenu.ActivityFragment
import com.naijab.sitnotify.newsmenu.CalendarFragment
import com.naijab.sitnotify.newsmenu.MoreFragment
import com.naijab.sitnotify.newsmenu.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_activity_main.*

class MainActivity : BaseActivity() {

    lateinit internal var fragmentNews: NewsFragment
    lateinit internal var fragmentActivity: ActivityFragment
    lateinit internal var fragmentCalendar: CalendarFragment
    lateinit internal var fragmentMore: MoreFragment

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
        fragmentActivity = ActivityFragment.newInstance()
        fragmentCalendar = CalendarFragment.newInstance()
        fragmentMore = MoreFragment.newInstance()
    }

    private fun initBottomNavBar() {
        bottomNavigation
                .addItem(BottomNavigationItem(
                        R.drawable.ic_news, getString(R.string.news_menu)))
                .addItem(BottomNavigationItem(
                        R.drawable.ic_activity, getString(R.string.activity_menu)))
                .addItem(BottomNavigationItem(
                        R.drawable.ic_calendar, getString(R.string.calendar_menu)))
                .addItem(BottomNavigationItem(
                        R.drawable.ic_more, getString(R.string.more_menu)))
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
                1 -> replace(R.id.container, fragmentActivity)
                2 -> replace(R.id.container, fragmentCalendar)
                3 -> replace(R.id.container, fragmentMore)
            }
        }.commitAllowingStateLoss()
    }
}