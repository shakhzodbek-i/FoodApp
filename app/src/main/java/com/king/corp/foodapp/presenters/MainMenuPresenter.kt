package com.king.corp.foodapp.presenters

import com.google.firebase.database.*
import com.king.corp.foodapp.models.Food
import com.king.corp.foodapp.views.interfaces.MainMenuView

class MainMenuPresenter(private val mView: MainMenuView) {
    private lateinit var mFavoritesReference: DatabaseReference
    private lateinit var mLatestReference: DatabaseReference
    private val mFavoriteFoods: ArrayList<Food> = ArrayList()
    private val mLatestFoods: ArrayList<Food> = ArrayList()

    fun loadFoodsList(){
        mFavoritesReference = FirebaseDatabase.getInstance().getReference("favorites")
        mLatestReference = FirebaseDatabase.getInstance().getReference("latest")

        mFavoritesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val favFood = dataSnapshot.value as Map<String, HashMap<String, String>>
                for ((_, value) in favFood){
                    val food = Food(
                        value["img_url"],
                        value["name"]
                    )
                    mFavoriteFoods.add(food)
                }

                mLatestReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val latestFood = dataSnapshot.value as Map<String, HashMap<String, String>>
                        for ((_, value) in latestFood){
                            val food = Food(
                                value["img_url"],
                                value["name"]
                            )
                            mLatestFoods.add(food)
                        }
                        mView.showFoods(mFavoriteFoods, mLatestFoods)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        error.toException().printStackTrace()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }
        })




    }
}