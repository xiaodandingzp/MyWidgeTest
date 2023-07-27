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


class MyAppWidgetProvider : AppWidgetProvider() {
    companion object {
        const val TAG = "MyAppWidgetProvider"
        private const val CLICK_ACTION = "com.barry.widgetapp.plus.CLICK"
        const val TEXT1 = "text1"
        const val TEXT2 = "text2"
        var text = TEXT1
    }

    var remoteViews: RemoteViews? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "onReceive onReceive onReceive")
        super.onReceive(context, intent)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        //当我们点击桌面上的widget按钮（这个按钮我们在onUpdate中已经为它设置了监听），widget就会发送广播
        //这个广播我们也在onUpdate中为它设置好了意图，设置了action，在这里我们接收到对应的action并做相应处理
        //当我们点击桌面上的widget按钮（这个按钮我们在onUpdate中已经为它设置了监听），widget就会发送广播
        //这个广播我们也在onUpdate中为它设置好了意图，设置了action，在这里我们接收到对应的action并做相应处理
//        if (intent!!.action.equals(CLICK_ACTION)) {
//            Log.i(TAG, "intent!!.action.equals(CLICK_ACTION)")
//            //因为点击按钮后要对布局中的文本进行更新，所以需要创建一个远程view
//            val remoteViews = RemoteViews(context!!.packageName, R.layout.example_appwidget)
//            //为对应的TextView设置文本
//            text = if (text == TEXT1) TEXT2
//            else TEXT1
//            Log.i(TAG, "$this    text: $text")
//            remoteViews.setTextViewText(R.id.text_view, text)
//            //更新widget
//            appWidgetManager.updateAppWidget(
//                ComponentName(
//                    context,
//                    MyAppWidgetProvider::class.java
//                ), remoteViews
//            )
//        }
    }
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        Log.i(TAG, "onUpdate onUpdate onUpdate")
//        super.onUpdate(context, appWidgetManager, appWidgetIds)
        // Perform this loop procedure for each App Widget that belongs to this provider
        appWidgetIds?.forEach { appWidgetId ->
            Log.i(TAG, "appWidgetId: $appWidgetId")
            // Create an Intent to launch ExampleActivity
            val intentClick = Intent()
            //这个必须要设置，不然点击效果会无效
            //这个必须要设置，不然点击效果会无效
            intentClick.setClass(context!!, MyAppWidgetProvider::class.java)
            intentClick.action = CLICK_ACTION
            //PendingIntent表示的是一种即将发生的意图，区别于Intent它不是立即会发生的
            //PendingIntent表示的是一种即将发生的意图，区别于Intent它不是立即会发生的
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intentClick,
                PendingIntent.FLAG_IMMUTABLE
            )


            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            val views = RemoteViews(
                context?.packageName,
                R.layout.example_appwidget
            ).apply {
//                setOnClickPendingIntent(R.id.button, pendingIntent)
            }

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
    }
}