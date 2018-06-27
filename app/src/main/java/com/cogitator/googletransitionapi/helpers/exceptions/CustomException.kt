package com.cogitator.googletransitionapi.helpers.exceptions

import android.content.Context
import com.cogitator.googletransitionapi.R

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
sealed class CustomException(message: String = "") : RuntimeException(message)

class NoDataFoundException : CustomException()

class DbFetchException : CustomException()

/**
 * Extension function to convert error type into string
 *
 * @param context   context object to get string resource
 */
fun CustomException.toMessage(context: Context?): String {
    context?.let {
        return when (this) {
            is NoDataFoundException -> it.getString(R.string.no_data_found_error_msg)
            is DbFetchException -> it.getString(R.string.db_fetch_error_msg)
        }
    }
    return ""
}