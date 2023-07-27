package com.example.mywidgetest

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.mywidgetest.zujian.MyAppWidgetProvider
import com.example.mywidgetest.zujian.MyAppWidgetProvider2

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button1_zp).setOnClickListener {
            if (!checkInstall(MyAppWidgetProvider::class.java)) {
                requestCompoent()
            }
        }

        findViewById<Button>(R.id.button2_zp).setOnClickListener {
            if (!checkInstall(MyAppWidgetProvider2::class.java)) {
                requestCompoent2()
            }
        }
    }

    private fun checkInstall(classProvider: Class<*>): Boolean {
        val appWidgetManager = ContextCompat.getSystemService(this, AppWidgetManager::class.java)
        val componentName = ComponentName(this, classProvider)
//        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
//        intent.component = componentName
        val resolveInfos =
            appWidgetManager?.getAppWidgetIds(componentName)

        resolveInfos?.forEach {
            Log.i(TAG, "resolveInfos $it")
        }
        if (resolveInfos != null && !resolveInfos.isEmpty()) {
            Log.i(TAG, "have installed")
            return true
        } else {
            // 小组件未安装到桌面
            Log.i(TAG, "have not installed")
            return false
        }
    }


    fun requestCompoent() {
        Log.i(TAG, "requestCompoent requestCompoent requestCompoent")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mAppWidgetManager =
                ContextCompat.getSystemService(this, AppWidgetManager::class.java)

            val  myProvider = ComponentName(this, MyAppWidgetProvider::class.java)
            val b = Bundle()
            b.putString("ggg", "ggg")
            if (mAppWidgetManager?.isRequestPinAppWidgetSupported == true) {
                val pinnedWidgetCallbackIntent = Intent(this, MyAppWidgetProvider::class.java)
                val successCallback = PendingIntent.getBroadcast(
                    this, 0,
                    pinnedWidgetCallbackIntent, PendingIntent.FLAG_IMMUTABLE
                );

                mAppWidgetManager.requestPinAppWidget(myProvider, b, successCallback)
            }
        }
    }
    fun requestCompoent2() {
        Log.i(TAG, "requestCompoent requestCompoent requestCompoent")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mAppWidgetManager =
                ContextCompat.getSystemService(this, AppWidgetManager::class.java)

            val  myProvider = ComponentName(this, MyAppWidgetProvider2::class.java)
            val b = Bundle()
            b.putString("ggg", "ggg")
            if (mAppWidgetManager?.isRequestPinAppWidgetSupported == true) {
                val pinnedWidgetCallbackIntent = Intent(this, MyAppWidgetProvider2::class.java)
                val successCallback = PendingIntent.getBroadcast(
                    this, 0,
                    pinnedWidgetCallbackIntent, PendingIntent.FLAG_IMMUTABLE
                );

                mAppWidgetManager.requestPinAppWidget(myProvider, b, successCallback)
            }
        }
    }
}