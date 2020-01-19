package com.arctouch.codechallenge.base.presentation.ui

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.arctouch.codechallenge.R


open class BaseFragment : Fragment(){


    protected fun showToast(message: String) {
        context?.let {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun navigateTo(direction: NavDirections): Boolean{
        findNavController().navigate(direction)
        return true
    }

    protected fun showDialog(
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