package cashkaro.com.cashkarodeals.utils;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;

/**
 * Class for handling the Analytics Tasks
 */

public class AnalyticsHelper {

    public static void logCrashlytics(String detailsToBeLogged) {
        if (Constants.isCrashLyticsEnabled)
            Crashlytics.log(detailsToBeLogged);
    }

    public void initCrashReporting(Context context) {
        if (Constants.isCrashLyticsEnabled)
            Fabric.with(context, new Crashlytics());
    }

    @SuppressWarnings("unused")
    /*
      Methd to include Answers
     */
    public void initFabricAnswers(Context context) {
        if (Constants.isAnswersEnabled)
            Fabric.with(context, new Answers());
    }
}
