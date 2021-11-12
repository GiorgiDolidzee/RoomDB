package com.example.sum9.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(title:String) {
    Snackbar.make(
        this,
        title,
        Snackbar.LENGTH_SHORT
    ).show()
}