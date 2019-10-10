package com.king.corp.foodapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.king.corp.foodapp.R
import com.king.corp.foodapp.adapter.FoodsAdapter
import com.king.corp.foodapp.models.Food
import kotlinx.android.synthetic.main.fragment_menu.view.*

class FoodMenuFragment : Fragment() {

    private lateinit var mFoods: ArrayList<Food>

    companion object{
        fun getInstance(foods: ArrayList<Food>): Fragment{
            val bundle = Bundle()
            bundle.putParcelableArrayList("food_list",foods)

            val fragment = FoodMenuFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFoods = arguments!!.getParcelableArrayList("food_list")
        view.recycler.adapter = FoodsAdapter(mFoods)
        view.recycler.layoutManager = GridLayoutManager(context, 2)
    }
}