package com.king.corp.foodapp.presenters

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.king.corp.foodapp.R
import com.king.corp.foodapp.views.interfaces.SignInView

class SignInPresenter(
    context: Context,
    private val mView: SignInView,
    private val mAuth: FirebaseAuth
) {
    private var mGoogleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun authWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)

        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                mView.hideProgress()
                mView.openMainMenu(mAuth.currentUser)
            }
        }
    }

    fun signIn(){
        mView.showProgress()
        mView.startAuthActivityResult(mGoogleSignInClient.signInIntent)
    }

    fun getCurrentUser(): FirebaseUser?{
        return mAuth.currentUser
    }

}