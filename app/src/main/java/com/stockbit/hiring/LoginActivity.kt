package com.stockbit.hiring

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    val stringUtils = StringUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setHeaderLayout()
    }

    private fun setHeaderLayout(){
        findViewById<ImageView>(R.id.imgLeftHeader)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_back_arrow))

        findViewById<ImageView>(R.id.imgRightHeader)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_help))

//        show title and hide the logo
        val header = findViewById<TextView>(R.id.txtHeader)
        header.visibility = View.VISIBLE
        header.text = getText(R.string.masuk)

        findViewById<ImageView>(R.id.imgLogo).visibility = View.GONE
    }

    private fun login(){
        val username = findViewById<TextInputEditText>(R.id.username_tie)
        val password = findViewById<TextInputEditText>(R.id.password_tie)

//        check login criteria
        if (stringUtils.isEmpty(username.text.toString())){
            stringUtils.setMessage(this, getString(R.string.username_empty))
            return
        }

        if (stringUtils.isEmpty(password.text.toString())){
            stringUtils.setMessage(this, getString(R.string.username_empty))
            return
        }

        val intent = Intent(this,WatchlistActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.login_btn -> login()
            else -> stringUtils.setMessage(this, getString(R.string.feature_not_available))
        }
    }
}