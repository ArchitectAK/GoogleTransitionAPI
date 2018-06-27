package com.cogitator.googletransitionapi.helpers.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
/**
 * Helper method to get and change color of drawable
 *
 * @param context       the context
 * @param resId         resource id of color
 * @return              return the drawable resource
 */
fun getDrawable(context: Context?, resId: Int): Drawable? {
    context?.let {
        return ResourcesCompat.getDrawable(context.resources, resId, context.theme)
    }
    return null
}

/**
 * Helper method to get and change color of drawable
 *
 * @param context       the context
 * @param resId         resource id of color
 * @param color         the color which will be used to paint drawable
 * @return              return the drawable resource
 */
fun getColoredDrawable(context: Context?, resId: Int, color: Int): Drawable? {
    context?.let {
        val drawable = ResourcesCompat.getDrawable(context.resources, resId, context.theme)
        drawable?.setColorFilter(getColor(context, color), PorterDuff.Mode.SRC_IN)
        return drawable
    }
    return null
}

/**
 * Helper method to get color resource
 *
 * @param context       the context
 * @param resId         resource id of color
 * @return              return the color resource
 */
fun getColor(context: Context, resId: Int) = ResourcesCompat.getColor(context.resources, resId,
        context.theme)