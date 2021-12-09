package com.sevenpeakssoftware.farhan.utils

import android.content.Context
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by FarhanAhmed
 */

object CommonUtils {
    fun formatDate(date: String, context: Context): String {
        val apiDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.UK)
        val apiDate = apiDateFormat.parse(date);
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        calendar.timeInMillis = apiDate.time
        val apiDateYear = calendar.get(Calendar.YEAR)
        val dateFormat = DateFormat.is24HourFormat(context)
        var newDateFormatWithYear = SimpleDateFormat("dd MMMM yyyy, hh:mm a", Locale.UK)
        var newDateFormat = SimpleDateFormat("dd MMMM, hh:mm a", Locale.UK)
        if(dateFormat){
            newDateFormatWithYear = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.UK)
            newDateFormat = SimpleDateFormat("dd MMMM, HH:mm", Locale.UK)
        }
        apiDate?.let {
            if(currentYear == apiDateYear) {
                return newDateFormat.format(it)
            }else{
                return newDateFormatWithYear.format(it)
            }
        }
        return ""
    }
}
