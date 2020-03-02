

/**
 * Created by SAM on 11/27/2019.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
/*
*  From: /topics/notify
2->42: Message data payload: {moredata=dd, message={"sound":true,"title":"Order Updated","body":"Office Barcode Order Updated by mgf samir"}}
2ice->82: Message Notification Body: Office Barcode Order Updated by mgf samir
* */


        int requestID = (int) System.currentTimeMillis();

        Timber.d("From: %s", remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Timber.d("Message data payload: %s", remoteMessage.getData());

            String title = remoteMessage.getData().get("title");
            String message = remoteMessage.getData().get("message");



            //            intent.setAction(Constants.NOTIFY);


            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


            String channelId = "push_" + BuildConfig.APPLICATION_ID;
            String channelName = "Notification_" + this.getResources().getString(R.string.app_name);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW);
                mChannel.enableLights(true);
                mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                mChannel.setSound(getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), new AudioAttributes.Builder().build());
                Objects.requireNonNull(notificationManager).createNotificationChannel(mChannel);
            }

            Intent intent = new Intent(this, SplashActivity.class);
            intent.putExtra(Constants.NOTIFY, requestID);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            int uniqueInt = (int) (System.currentTimeMillis() & 0xfffffff);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, uniqueInt, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent, PendingIntent.FLAG_ONE_SHOT);
//            try {
//                // Perform the operation associated with our pendingIntent
//                pendingIntent.send();
//            } catch (PendingIntent.CanceledException e) {
//                e.printStackTrace();
//            }

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setContentIntent(pendingIntent)
                    .setSound(getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), AudioManager.STREAM_NOTIFICATION)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true)
                    .setSmallIcon(R.mipmap.ic_launcher);

//            Objects.requireNonNull(notificationManager).cancel(0);
            Objects.requireNonNull(notificationManager).notify(0, mBuilder.build());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Timber.d("Message Notification Body: %s", remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Timber.d("onDeletedMessages : ");
    }

    @Override
    public void onMessageSent(@NonNull String s) {
        super.onMessageSent(s);
        Timber.d("onMessageSent: %s", s);
    }

    @Override
    public void onSendError(@NonNull String s, @NonNull Exception e) {
        super.onSendError(s, e);
        Timber.d("onSendError: " + s + "\t\t\t" + e.getMessage());
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Timber.d("Refreshed token: %s", token);

        SharedPrefs.setString(getApplicationContext(), SharedPrefs.DEVICE_TOKEN, token);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(token);
    }
}
