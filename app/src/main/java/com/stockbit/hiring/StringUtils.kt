package com.stockbit.hiring

import android.content.Context
import android.widget.Toast

class StringUtils {

    fun setMessage(context: Context, text: String){
        Toast.makeText(context, text , Toast.LENGTH_LONG).show()
    }

    fun isEmpty(string: String?): Boolean{
        if (string != null && string == ""){
            return true
        }
        return false
    }

}