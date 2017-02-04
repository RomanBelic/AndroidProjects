package com.esgi.al1.nearbymsg.broadcast_receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.esgi.al1.nearbymsg.MainActivity;
import com.esgi.al1.nearbymsg.R;

/**
 * Created by Romaaan on 24/01/2017.
 */


public class MessageReceiver extends BroadcastReceiver {

    public static final String Tag = "On_Message_Received";
    public static final int Id = 0x66;
    public MessageReceiver (){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Tag)){
            Intent resultIntent = new Intent(context, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.cast_ic_notification_small_icon)
                            .setContentIntent(resultPendingIntent)
                            .setPriority(Notification.PRIORITY_MAX)
                            .setContentTitle("Message : ")
                            .setContentText(intent.getStringExtra("message"));
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(Id, mBuilder.build());

        }
    }
}
