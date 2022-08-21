package com.kt.mynotification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_send_notification).setOnClickListener(view ->{
            sendNotification();
        });
    }

    //Android 8.0 trở lên ta phải có Channel ID
    //Nhỏ hơn 8.0 nên không hoạt động
    private void sendNotification(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //Sử dụn Notification
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Title push notification")
                .setContentText("Message push notification")
                .setSmallIcon(R.drawable.ic_baseline_location_on_24)
                //.setLargeIcon(bitmap)   //set icon to cho notification
                .setColor(getResources().getColor(R.color.purple_200))
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null){
            //set notification cho notificationManager
            notificationManager.notify(getNotificationId(), notification);
        }

    }
    //nó sẽ get ra integer khác nhau để ném cho id
    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}