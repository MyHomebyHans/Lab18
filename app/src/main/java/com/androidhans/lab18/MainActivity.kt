package com.androidhans.lab18

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_show).setOnClickListener { //建立按鈕監聽器
            val nm = NotificationManagerCompat.from(this) //建立通知管理物件
            //若Android8.0以上版本必需先建立通知頻道
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //設定頻道ID、名稱及訊息優先權
                val name = "My Channel"
                val importance = NotificationManager.IMPORTANCE_DEFAULT //通知管理器.重要性_默認值
                val channel = NotificationChannel("Lab18", name, importance)
                //建立通知頻道
                nm.createNotificationChannel(channel)
                }
            //建立Intent(意圖)、PendingIntent(待定的意图), 當通知被點選時開啟應用程式
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK //旗幟活動新任務或旗幟活動清除任務
            val pendingIntent = PendingIntent.getActivities(this, 0, intent, 0)
            //定義通知的內容並發送
            val text = "您還有一張五折折價卷，滿額消費即贈現金回饋"
            val builder = NotificationCompat.Builder(this,"Lab18") //通知兼容生成器
                .setSmallIcon(android.R.drawable.btn_star_big_on) //通知圖示
                .setContentText("折價卷")                          //通知標題
                .setContentText(text)                             //通知內容
                .setContentIntent(pendingIntent)                  //通知被點選後的意圖
                .setAutoCancel(true)                              //通知蚾點選後自動消失
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) //優先權
            nm.notify(0, builder.build())  //發送通知於裝置


        }
    }
}