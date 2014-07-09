package com.welaika.menadi;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.welaika.menadi.fragments.NotificationReceiverFragment;

public class CreateNotificationPROVA extends Activity {

    public static final String FRAGMENT_CLASS = "fragment_class";
    public final String notificationClass = "NotificationReceiverFragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prova_main);
    }


    public void createNotification(View view) {
        // Prepare intent which is triggered if the notification is selected

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        notificationIntent.putExtra("NotificationMessage", notificationClass);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification noti = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.drawable.menadi)
                .setContentIntent(pendingNotificationIntent)
                .addAction(R.drawable.menadi, "Call", pendingNotificationIntent)
                .addAction(R.drawable.menadi, "More", pendingNotificationIntent)
                .addAction(R.drawable.menadi, "And more", pendingNotificationIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
    }
}
