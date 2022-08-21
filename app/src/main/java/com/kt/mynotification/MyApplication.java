package com.kt.mynotification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

//Nơi tạo channel Id
public class MyApplication extends Application {
    public static final String CHANNEL_ID = "CHANNEL_ID_1";
    //thêm channel id 2
    public static final String CHANNEL_ID_2 = "CHANNEL_ID_2";
    @Override
    public void onCreate() {
        super.onCreate();
        //create notification channel
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
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            //set Description
            channel.setDescription(description);

            //config channel 2
            CharSequence name_2 = getString(R.string.channel_name_2);
            String description_2 = getString(R.string.channel_description_2);
            int importance_2 = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID_2, name_2, importance_2);
            channel_2.setDescription(description_2);

            //
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null){
                //create notification channel truyền vào channel
                notificationManager.createNotificationChannel(channel);
                //Thêm channel 2
                notificationManager.createNotificationChannel(channel_2);
            }
        }
    }
}

//nhấn ctrl + trỏ vào hàm muốn xem thông tin
