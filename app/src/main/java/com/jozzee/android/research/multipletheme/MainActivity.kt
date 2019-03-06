package com.jozzee.android.research.multipletheme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        //Create sharedPreference and get isDarkMode
        val sharedPreference = getSharedPreferences(PREFERENCES_NAME_KEY, Context.MODE_PRIVATE)
        val isDarkMode = sharedPreference.getBoolean(DARK_MODE_KEY, false)
        setTheme(if (isDarkMode) R.style.AppTheme_Dark else R.style.AppTheme_Light)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter = ScreenAdapter(supportFragmentManager)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottom_navigation.selectedItemId = R.id.action_account
                    1 -> bottom_navigation.selectedItemId = R.id.action_settings
                }
            }
        })

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_account -> view_pager.currentItem = 0
                R.id.action_settings -> view_pager.currentItem = 1
            }
            true
        }
    }

    class ScreenAdapter(private val fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        private val screenList = ArrayList<Fragment>().apply {
            add(AccountFragment())
            add(SettingFragment())
        }

        override fun getItem(position: Int): Fragment = screenList[position]

        override fun getCount(): Int = screenList.size
    }
}
