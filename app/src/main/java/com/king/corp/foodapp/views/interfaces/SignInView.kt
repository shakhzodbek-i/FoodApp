package com.king.corp.foodapp.views.interfaces

import android.content.Intent
import com.google.firebase.auth.FirebaseUser

interface SignInView {
    fun startAuthActivityResult(signInIntent: Intent)

    fun openMainMenu(user: FirebaseUser?)

    fun showProgress()

    fun hideProgress()

}