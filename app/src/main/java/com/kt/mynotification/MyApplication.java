package com.kt.mynotification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

//Nơi tạo channel Id
public class MyApplication extends Application {
    public static final String CHANNEL_ID = "CHANNEL_ID_1";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }
    //Tạo một cái notification channel
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        //O : api 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            //
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            //set Description
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null){
                //create notification channel truyền vào channel
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}

//nhấn ctrl + trỏ vào hàm muốn xem thông tin
