package com.rachel.newsapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rachel.newsapplication.databinding.ActivityForgotPassword2Binding

class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var forgotBinBinding: ActivityForgotPassword2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgotBinBinding = ActivityForgotPassword2Binding.inflate(layoutInflater)
        setContentView(forgotBinBinding.root)
        supportActionBar?.hide()
        forgotBinBinding.fbForgot.setOnClickListener(this)
    }

    companion object {
        fun getLaunchService(from: Context) =
            Intent(from, ForgotPasswordActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
    }




    override fun onClick(p0: View) {
        when(p0.id){
            R.id.fb_forgot->forgotPassword()
        }
    }

    private fun forgotPassword() {
        mAuth = FirebaseAuth.getInstance()
        val email = forgotBinBinding.etEmailForgot.text.toString()
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Tidak boleh kosong",Toast.LENGTH_SHORT).show()
        }else{
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Check email to reset password",
                    Toast.LENGTH_SHORT).show()
                    startActivity(SigninActivity.getLaunchService(this))
                }
            }
        }
    }
}