package com.yabahddou.pagersnaphelper

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 ***************************************
 * Created by Abdelhadi on 2019-06-08.
 ***************************************
 */
data class ScreenSize(val widthPx: Int, val heightPx: Int)

fun Context.screenSize(): ScreenSize {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    val displayMetrics = DisplayMetrics()
    windowManager?.defaultDisplay?.getMetrics(displayMetrics)
    val h = displayMetrics.heightPixels
    val w = displayMetrics.widthPixels
    return ScreenSize(w, h)
}

val Int.dp2px: Int
    get() = (this * (App.INSTANCE.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
