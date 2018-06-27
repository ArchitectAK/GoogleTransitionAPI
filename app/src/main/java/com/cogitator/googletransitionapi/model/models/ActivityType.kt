package com.cogitator.googletransitionapi.model.models

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
enum class ActivityType(val value: Int) {
    IN_VEHICLE(0),
    ON_BICYCLE(1),
    UNKNOWN(2),
    STILL(3),
    WALKING(7),
    RUNNING(8)
}