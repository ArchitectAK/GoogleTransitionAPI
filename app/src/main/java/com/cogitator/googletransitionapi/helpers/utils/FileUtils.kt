package com.cogitator.googletransitionapi.helpers.utils

import android.content.Context
import android.os.Environment
import timber.log.Timber
import java.io.File

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
/**
 * Helper method to determine if external storage is available
 *
 * @return                  return if external storage is available
 */
fun isExternalStorageAvailable(): Boolean {
    return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
}

/**
 * Extension function to get access to file or generate it if not exists
 *
 * @param folderName        the firstName of the folder
 * @param fileName          the firstName of the file
 * @return                  created or accessed file
 */
fun generateExternalFile(folderName: String, fileName: String): File? {
    var file: File? = null
    if (isExternalStorageAvailable()) {
        // Access folder
        val root = File(Environment.getExternalStorageDirectory(), folderName)
        // Try to create the folder if it doesn't exist
        if (root.exists() || root.mkdirs()) {
            file = File(root, fileName)
        }
    }
    return file
}

/**
 * Extension function to get access to file or generate it if not exists
 *
 * @param folderName        the firstName of the folder
 * @param fileName          the firstName of the file
 * @return                  created or accessed file
 */
fun Context.generateInternalFile(folderName: String, fileName: String): File? {
    var file: File? = null
    try {
        // Access folder
        val root = File(this.filesDir, folderName)

        // Try to create the folder if it doesn't exist
        if (root.exists() || root.mkdirs()) {
            file = File(root, fileName)
        }

    } catch (e: Exception) {
        Timber.e(e, "Could not create file!")
    }
    return file
}