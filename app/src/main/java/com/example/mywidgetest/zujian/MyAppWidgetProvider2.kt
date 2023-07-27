package com.example.mywidgetest.zujian

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.example.mywidgetest.R
import java.text.SimpleDateFormat
import java.util.Date

class MyAppWidgetProvider2 : AppWidgetProvider() {
    companion object {
        const val TAG = "MyAppWidgetProvider2"
        private const val CLICK_ACTION = "com.barry.widgetapp.plus.CLICK2"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "onReceive onReceive onReceive")
        super.onReceive(context, intent)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        if (intent!!.action.equals(CLICK_ACTION)) {
            Log.i(TAG, "intent!!.action.equals(CLICK_ACTION)")
            //因为点击按钮后要对布局中的文本进行更新，所以需要创建一个远程view
            val remoteViews = RemoteViews(context!!.packageName, R.layout.example_appwidget2)
            remoteViews.setTextViewText(R.id.text_view, getCurrentTime())
            //更新widget
            appWidgetManager.updateAppWidget(
                ComponentName(
                    context,
                    MyAppWidgetProvider2::class.java
                ), remoteViews
            )
        }
    }

    private fun getCurrentTime(): String {
        val timestamp = System.currentTimeMillis() // 获取当前时间戳
        val date = Date(timestamp) // 将时间戳转换为Date对象
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // 定义日期格式
        return sdf.format(date)
    }
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        Log.i(TAG, "onUpdate onUpdate onUpdate")
        val intentClick = Intent()
        //这个必须要设置，不然点击效果会无效
        //这个必须要设置，不然点击效果会无效
        intentClick.setClass(context!!, MyAppWidgetProvider2::class.java)
        intentClick.action = CLICK_ACTION
        //PendingIntent表示的是一种即将发生的意图，区别于Intent它不是立即会发生的
        //PendingIntent表示的是一种即将发生的意图，区别于Intent它不是立即会发生的
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intentClick,
            PendingIntent.FLAG_MUTABLE
        )
        appWidgetIds?.forEach { appWidgetId ->
            Log.i(TAG, "appWidgetId: $appWidgetId")

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            val views = RemoteViews(
                context?.packageName,
                R.layout.example_appwidget2
            ).apply {
                setOnClickPendingIntent(R.id.button, pendingIntent)
            }

            views.setTextViewText(R.id.text_view, getCurrentTime())
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
    }
}