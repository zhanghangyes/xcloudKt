package com.example.xcloudkt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.xcloudkt.base.BaseActivity
import com.example.xcloudkt.constant.Constant
import com.example.xcloudkt.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var mHomeFragment: HomeFragment? = null
    var mCouponsFragment: CouponsFragment? = null
    var mMemberFragment: MemberFragment? = null
    var mMineFragment: MineFragment? = null
    var mChatFragment: ImFragment? = null

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initData() {
        setStatusBarColor(R.color.white, 1)
        mHomeFragment = HomeFragment()
        mMemberFragment = MemberFragment()
        mCouponsFragment = CouponsFragment()
        mMineFragment = MineFragment()
        mChatFragment = ImFragment()
        bottom_navigation_view.initView(
            this,
            R.id.main_frame,
            mHomeFragment as Fragment,
            mCouponsFragment as Fragment,
            mMemberFragment as Fragment,
            mChatFragment as Fragment,
            mMineFragment as Fragment
        )
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val home_main = intent.getIntExtra(Constant.HOME_MAIN, 0)
        bottom_navigation_view.disPlay(home_main)
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("index", bottom_navigation_view.index)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedIndex = savedInstanceState.getInt("index")
        if (savedIndex != bottom_navigation_view.index) {
            if (bottom_navigation_view.fragments[0].isAdded) {
                supportFragmentManager.beginTransaction().hide(bottom_navigation_view.fragments[0])
                    .commit()
            }
            if (savedIndex == 1) {
                bottom_navigation_view.disPlay(1)
            } else if (savedIndex == 2) {
                bottom_navigation_view.disPlay(2)
            } else if (savedIndex == 3) {
                bottom_navigation_view.disPlay(3)
            } else if (savedIndex == 4) {
                bottom_navigation_view.disPlay(4)
            }
        }
    }

    override fun onBackPressed() {
        onDoubleCheck()
    }
}