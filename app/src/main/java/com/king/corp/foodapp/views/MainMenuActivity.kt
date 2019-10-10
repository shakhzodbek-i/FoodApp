package com.king.corp.foodapp.views

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.king.corp.foodapp.Constants
import com.king.corp.foodapp.R
import com.king.corp.foodapp.adapter.ViewPagerAdapter
import com.king.corp.foodapp.models.Food
import com.king.corp.foodapp.models.User
import com.king.corp.foodapp.presenters.MainMenuPresenter
import com.king.corp.foodapp.views.interfaces.MainMenuView
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainMenuActivity : AppCompatActivity(), MainMenuView {

    private lateinit var mPresenter: MainMenuPresenter
    private lateinit var mUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        setSupportActionBar(toolbar)

        mUser = intent.getParcelableExtra(Constants.USER_INFO_KEY)

        mPresenter = MainMenuPresenter(this)

        mPresenter.loadFoodsList()


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val header = nav_view.inflateHeaderView(R.layout.nav_header)

        header.user_name.text = mUser.name
        Glide.with(this)
            .load(mUser.avatarUrl)
            .centerCrop()
            .into(header.user_img)
    }

    override fun showFoods(favoriteFoods: ArrayList<Food>, latestFoods: ArrayList<Food>) {
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager, favoriteFoods, latestFoods)
        view_pager.adapter = pagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}