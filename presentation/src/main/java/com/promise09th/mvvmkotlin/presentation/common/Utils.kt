package com.promise09th.mvvmkotlin.presentation.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.convertKakaoDateFormat(): String {
    return try {
        val parseFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
            Locale.getDefault()
        )
        val parseDate = parseFormat.parse(this)
        val sdp = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        sdp.format(parseDate!!)
    } catch (e: ParseException) {
        this
    }
}
