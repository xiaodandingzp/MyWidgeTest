package com.example.mywidgetest

import java.text.SimpleDateFormat
import java.util.Date

object WidgetUtils {

    fun getCurrentTime(): String {
        val timestamp = System.currentTimeMillis() // 获取当前时间戳
        val date = Date(timestamp) // 将时间戳转换为Date对象
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // 定义日期格式
        return sdf.format(date)
    }
}