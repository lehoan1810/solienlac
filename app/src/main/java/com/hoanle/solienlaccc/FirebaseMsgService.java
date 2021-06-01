package com.hoanle.solienlaccc;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMsgService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage rm) {
        // Hiển thị thông báo
        createNotification(rm.getNotification().getBody());
    }
    // Xử lý hiển thị thông báo
    private void createNotification(String msg) {
        Intent i = new Intent(this, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
        Uri u = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder b = new NotificationCompat.Builder(this,"solienlac")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Hướng dẫn sử dụng Firebase Cloud Messaging")
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(u)
                .setContentIntent(pi);
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0, b.build());
    }
}
