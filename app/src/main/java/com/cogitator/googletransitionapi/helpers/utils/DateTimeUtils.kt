package com.cogitator.googletransitionapi.helpers.utils

import java.text.DateFormat

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
/**
 * Helper method to get and change color of drawable
 *
 * @param timeInMillis  UNIX time in milliseconds
 * @return              return string representation of formatted time
 */
fun convertToDateTime(timeInMillis: Long): String {
    // Create a DateFormatter object for displaying date in specified format.
    val formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
    return formatter.format(timeInMillis)
}