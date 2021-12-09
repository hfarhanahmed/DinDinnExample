package com.sevenpeakssoftware.farhan

/**
 * Created by FarhanAhmed
 */
sealed class DataStatus {
    object Success : DataStatus()
    object Fail : DataStatus()
    object EmptyResponse : DataStatus()
}
