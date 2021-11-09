package com.androidhans.lab18

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.JsonToken
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


//建立一個類別，使其繼承 FirebaseMessagingService 類別
class MyMessagingService : FirebaseMessagingService() {
    //取得新的 Token 時被調用，通常會於第一次啟動應用程式時進入
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("onNewToken", token)
    }

    override fun onMessageReceived(msg: RemoteMessage) {
        super.onMessageReceived(msg)
        //藉由 forEach 將通知附帶的資料取出
        msg.data.entries.forEach(){
            Log.e("data", "key:${it.key}, value:${it.value}")
        }
    }
}