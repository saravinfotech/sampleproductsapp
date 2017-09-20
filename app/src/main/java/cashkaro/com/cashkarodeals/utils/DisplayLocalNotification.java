package cashkaro.com.cashkarodeals.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import cashkaro.com.cashkarodeals.R;

/**
 * Class to handle local notifications
 */

public class DisplayLocalNotification {


    /**
     * Method to display the local Notificaiton
     *
     * @param context               Current context of the Activity
     * @param couponDetails         from which the image description/retailer title is fetched
     * @param activityToBeDisplayed Activity to be launched when the notification is clicked
     */
    public void displayLocalNotification(Context context, String couponDetails, Class activityToBeDisplayed) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.cashkaro_icon);
        builder.setContentTitle(Constants.NOTIFICATION_TITLE);
        builder.setContentText(Constants.NOTIFICATION_TEXT + couponDetails);

        Intent intent = new Intent(context, activityToBeDisplayed);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(activityToBeDisplayed);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(Constants.NOTIFICATION_ID, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(Constants.NOTIFICATION_ID, builder.build());
    }
}
