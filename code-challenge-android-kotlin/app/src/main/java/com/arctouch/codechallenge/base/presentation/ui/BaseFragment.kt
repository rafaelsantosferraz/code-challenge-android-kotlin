package com.arctouch.codechallenge.base.presentation.ui

import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.arctouch.codechallenge.R


open class BaseFragment : Fragment(){


    fun showDialog(
            @StringRes messageId: Int,
            isCancelable: Boolean = true,
            @StringRes textId: Int = R.string.ok,
            functionBlock : (() -> Unit)? = null){

        AlertDialog.Builder(context!!)
                .setMessage(messageId)
                .setCancelable(isCancelable)
                .setPositiveButton(textId){ _,_ ->
                    functionBlock?.invoke()
                }
                .show()
    }
}