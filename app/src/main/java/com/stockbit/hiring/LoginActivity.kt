package com.stockbit.hiring

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val stringUtils = StringUtils()
    private lateinit var tilUsername: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var tieUsername: TextInputEditText
    private lateinit var tiePassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        initialize view
        initView()
        setHeaderLayout()
    }

    fun initView() {
        tilUsername = findViewById(R.id.username_til)
        tieUsername = findViewById(R.id.username_tie)
        tilPassword = findViewById(R.id.password_til)
        tiePassword = findViewById(R.id.password_tie)
    }

    private fun setHeaderLayout() {
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

    private fun login() {

//        check login criteria with text watcher
        validationTextWatcher(tieUsername)
        validationTextWatcher(tiePassword)

        if (!validateUsername()) {
            return
        }

        if (!validatePassword()) {
            return
        }

        val intent = Intent(this, WatchlistActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.login_btn -> login()
            else -> stringUtils.setMessage(this, getString(R.string.feature_not_available))
        }
    }

    fun validatePassword(): Boolean {

        when {
            tiePassword.text.toString().isEmpty() -> {
                tilPassword.error = getString(R.string.password_empty)
                return false
            }
            tiePassword.text.toString().length < 6 -> {
                tilPassword.error = getString(R.string.password_digit)
                return false
            }
            else -> {
                tilPassword.isErrorEnabled = false
            }
        }

        return true
    }

    private fun validateUsername(): Boolean {
        when {
             tieUsername.text.toString().isEmpty() -> {
                tilUsername.error = getString(R.string.username_empty)
                return false
            }
            tieUsername.text.toString().length < 6 -> {
                tilUsername.error = getString(R.string.username_length)
                return false
            }
            else -> {
                tilUsername.isErrorEnabled = false
            }
        }

        return true
    }


    private fun validationTextWatcher(editText: TextInputEditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when (editText) {
                    tieUsername -> validateUsername()
                    tiePassword -> validatePassword()
                }
            }
        })
    }
}
