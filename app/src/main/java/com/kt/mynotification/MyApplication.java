package com.kt.mynotification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
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
            //Uri âm thanh mặc định
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //Uri âm thanh mà ta set
            Uri sound = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sound_notification_custom);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            //set Description
            channel.setDescription(description);
            //set âm thanh mà ta tạo ra
            channel.setSound(sound,audioAttributes);

            //config channel 2
            CharSequence name_2 = getString(R.string.channel_name_2);
            String description_2 = getString(R.string.channel_description_2);
            //set độ ưu tiên với IMPORTTANCE_HIGH
            int importance_2 = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID_2, name_2, importance_2);
            channel_2.setDescription(description_2);
            //set âm thanh mặc định
            channel_2.setSound(uri,audioAttributes);

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
