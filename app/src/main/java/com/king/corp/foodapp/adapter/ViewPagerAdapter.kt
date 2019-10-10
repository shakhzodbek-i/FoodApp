package com.king.corp.foodapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.king.corp.foodapp.models.Food
import com.king.corp.foodapp.views.FoodMenuFragment

class ViewPagerAdapter(fm: FragmentManager,
                       private val favFoods: ArrayList<Food>,
                       private val latestFood: ArrayList<Food>
) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            FoodMenuFragment.getInstance(favFoods)
        } else {
            FoodMenuFragment.getInstance(latestFood)
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0)
            "Favorites"
        else
            "Latest"
    }
}