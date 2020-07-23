package com.promise09th.mvvmkotlin.presentation.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        fun convertKakaoDateFormat(date: String): String {
            return try {
                val parseFormat = SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                    Locale.getDefault()
                )
                val parseDate = parseFormat.parse(date)
                val sdp = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
                sdp.format(parseDate!!)
            } catch (e: ParseException) {
                date
            }
        }
    }
}
