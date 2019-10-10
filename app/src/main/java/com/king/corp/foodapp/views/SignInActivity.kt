package com.king.corp.foodapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.king.corp.foodapp.Constants
import com.king.corp.foodapp.R
import com.king.corp.foodapp.models.User
import com.king.corp.foodapp.presenters.SignInPresenter
import com.king.corp.foodapp.views.interfaces.SignInView
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(), SignInView{

    private lateinit var mPresenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mPresenter = SignInPresenter(baseContext, this, FirebaseAuth.getInstance())

        google_sign_in.setOnClickListener {
            mPresenter.signIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constants.REQUEST_CODE_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                mPresenter.authWithGoogle(account)
            } catch (e: ApiException) {
                Log.e("Login", "Google Sign in Failed", e)
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        openMainMenu(mPresenter.getCurrentUser())
    }

    override fun startAuthActivityResult(signInIntent: Intent) {
        startActivityForResult(signInIntent, Constants.REQUEST_CODE_GOOGLE_SIGN_IN)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun openMainMenu(user: FirebaseUser?) {
        if (user != null) {
            val userInfo = User(user.displayName, user.email, user.photoUrl)
            val intent = Intent(this, MainMenuActivity::class.java)
            intent.putExtra(Constants.USER_INFO_KEY, userInfo)
            startActivity(intent)
            finish()
        }
    }
}