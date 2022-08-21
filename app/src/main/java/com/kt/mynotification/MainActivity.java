package com.kt.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_send_notification).setOnClickListener(view ->{
            sendNotificationChannel1();
        });

        findViewById(R.id.btn_send_notification_2).setOnClickListener(view ->{
            sendNotificationChannel2();
        });
    }

    //Nơi gửi notification tới cái notification channel id mà ta có.
    private void sendNotificationChannel1(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //Sử dụn Notification
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle("Title push notification channel 1")
                .setContentText("Message push notification channel 1")
                .setSmallIcon(R.drawable.ic_baseline_location_on_24)
                .setLargeIcon(bitmap);   //set icon to cho notification
                //.setColor(getResources().getColor(R.color.purple_200));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //set notification cho notificationManager
        notificationManager.notify(getNotificationId(), notification.build());
    }

    //Nơi gửi notification tới cái notification channel id mà ta có.
    private void sendNotificationChannel2(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //Sử dụn Notification
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID_2)
                .setContentTitle("Title push notification channel 2")
                .setContentText("Message push notificatio channel 2")
                .setSmallIcon(R.drawable.ic_baseline_location_on_24)
                //.setLargeIcon(bitmap)   //set icon to cho notification
                .setColor(getResources().getColor(R.color.purple_200));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //set notification cho notificationManager
        notificationManager.notify(getNotificationId(), notification.build());
    }
    //nó sẽ get ra integer khác nhau để ném cho id
    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}