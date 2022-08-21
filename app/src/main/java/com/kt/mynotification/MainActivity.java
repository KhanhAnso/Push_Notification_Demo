package com.kt.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //
    public static final String TITLE_NOTIFICATION = "Mỗi cá nhân, tập thể đứng trước tình trạng ô nhiễm môi trường trầm trọng thì mỗi chúng ta cần chung tay cùng nhau tuyên truyền bảo vệ môi trường để môi trường của chúng ta càng ngày một trong sạch.";
    public static final String TEXT_CONTENT_NOTIFICATION = "Vì sao cần tuyên truyền bảo vệ môi trường?\n" +
            "Môi trường bao gồm cả các yếu tố tự nhiên và nhân tạo và 02 yếu tố này có mối quan hệ mật thiết với nhau, chúng đều bao quanh cuộc sống của con người và môi trường có ảnh hưởng rất lớn đến sự tồn tại và phát triển của con người và thiên nhiên.\n" +
            "\n" +
            "Một môi trường trong lành sạch sẽ sẽ giúp con người đảm bảo về mặt sức khỏe song ngược lại nếu môi trường bị ô nhiễm về không khí, khói bụi, nước… sẽ khiến con người mắc rất nhiều bệnh nguy hiểm, ảnh hưởng đến chất lượng cuộc sống và đe dọa đến tính mạng của nhiều người.\n" +
            "\n" +
            "Chính vì thế bảo vệ môi trường là việc làm rất cần thiết và việc bảo vệ môi trường để hiệu quả cần đến yếu tố về vận động, tuyên truyền giáo dục về nhận thức, hành vi.\n" +
            "\n" +
            "Để việc tuyên truyền bảo vệ được thì chúng ta cần nắm vững môi trường ô nhiễm sẽ có ảnh hưởng Đối với sức khỏe con người như việc ảnh hưởng của khói bụi sẽ ảnh hưởng nghiêm trọng đến hệ hô hấp của con người có thể gây nên một số loại bệnh như viêm phổi, vô sinh..\n" +
            "\n" +
            "Tiếp đến là Ô nhiễm không khí đây chính là một trong những nguyên nhân gây ra tình trạng đau đầu, chóng mặt, bệnh tim mạch và nó cực kỳ nguy hiểm với người cao tuổi, phụ nữ mang thai, người đang mang bệnh, trẻ nhỏ.";

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

        findViewById(R.id.btn_custom_notification).setOnClickListener(view ->{
            sendCustomNotification();
        });
    }

    private void sendCustomNotification() {
        //set dữ liệu cho layout
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.layout_custom_notification);
        notificationLayout.setTextViewText(R.id.tv_title_custom_notification,"Title custom notification");
        notificationLayout.setTextViewText(R.id.tv_message_custom_notificaiton,"Content message custom notification");
        //
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(new Date());
        notificationLayout.setTextViewText(R.id.tv_time_custom_notificaiton,strDate);

        //expanded : đối với notification mở rộng
        RemoteViews notificationLayout_expanded = new RemoteViews(getPackageName(), R.layout.layout_custom_notification_expanded);
        notificationLayout_expanded.setTextViewText(R.id.tv_title_custom_notification__expanded,"Title custom notification _expanded");
        notificationLayout_expanded.setTextViewText(R.id.tv_message_custom_notificaiton_expanded,"Content message custom notification _expanded");
        notificationLayout_expanded.setImageViewResource(R.id.img_custom_notification_expanded,R.drawable.iconapp);
        //nếu ảnh quá to ta convert sang bitmap
        //notificationLayout_expanded.setImageViewResource(R.id.img_custom_notification_expanded_content,R.drawable.iconapp);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.iconapp);

        notificationLayout_expanded.setImageViewBitmap(R.id.img_custom_notification_expanded_content,bitmap);

        SimpleDateFormat sdf_expanded = new SimpleDateFormat("HH:mm:ss");
        String strDate_expanded = sdf_expanded.format(new Date());
        notificationLayout_expanded.setTextViewText(R.id.tv_time_custom_notificaiton_expanded,strDate_expanded);


        //Sử dụng Notification
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_location_on_24)
                .setCustomContentView(notificationLayout)  //set layout dạng nhỏ
                .setCustomBigContentView(notificationLayout_expanded);  //set layout dạng big

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //set notification cho notificationManager
        notificationManager.notify(getNotificationId(), notification.build());

    }

    //Nơi gửi notification tới cái notification channel id mà ta có.
    private void sendNotificationChannel1(){
        //ảnh ic_lancher, notification không thể convert ảnh dạng xml cho setLagrgeIcon()
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.iconapp);
        //âm thanh custom cho notification. get ra đường dẫn của file âm thanh
        Uri sound = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sound_notification_custom);

        //Sử dụng Notification
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle(TITLE_NOTIFICATION)
                .setContentText(TEXT_CONTENT_NOTIFICATION)
                //tạo big text cho content text với setStyle()
                .setStyle(new NotificationCompat.BigTextStyle().bigText(TEXT_CONTENT_NOTIFICATION))
                .setSmallIcon(R.drawable.ic_baseline_location_on_24)
                .setSound(sound)
                .setLargeIcon(bitmap);   //set icon to cho notification

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //set notification cho notificationManager
        notificationManager.notify(getNotificationId(), notification.build());
    }

    //Nơi gửi notification tới cái notification channel id mà ta có.
    private void sendNotificationChannel2(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.iconapp);
        //âm thanh mặc định của thông báo
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Sử dụn Notification
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID_2)
                .setContentTitle("Title push notification channel 2")
                .setContentText("Message push notificatio channel 2")
                .setSmallIcon(R.drawable.ic_baseline_location_on_24)
                .setLargeIcon(bitmap)
                //icon lớn vào trong title
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                //set âm thanh cho thông báo
                .setSound(uri)
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