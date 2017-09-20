package cashkaro.com.cashkarodeals.utils;

import cashkaro.com.cashkarodeals.BuildConfig;
import cashkaro.com.cashkarodeals.R;

/**
 * Constants for CashKaro application
 */

public class Constants {

    public static final boolean isCrashLyticsEnabled = BuildConfig.ENABLE_FABRICS_CRASHLYTICS;
    public static final boolean isAnswersEnabled = BuildConfig.ENABLE_FABRICS_ANSWERS;

    //Permission request request code to identify the response returned
    public static final int PERMISSION_REQUEST_CAMERA = 100;
    public static final int PERMISSION_REQUEST_LOCATION = 101;
    public static final int NOTIFICATION_ID = 102;
    public static final int CHROME_CUSTOM_TAB_REQUEST_CODE = 100;

    //Permission Names
    public static final String PERMISSION_NAME_CAMERA = "Camera permission";
    public static final String PERMISSION_NAME_LOCATION = "Location Permission";

    //Permission grant status text message
    public static final String PERMISSION_GRANTED_MESSAGE = " has been granted.";
    public static final String PERMISSION_DENIED_MESSAGE = " has been denied";

    //Tabs displayed on the Main Screen - Categories Header
    public static final String BEST_DEALS_CATEGORY = "Todays Best Cash Deals";
    public static final String TODAY_OFFER_CATEGORY = "Top Category Offers";
    public static final String REFERRAL_PROGRAM_CATEGORY = "Rewarding Referral Program";

    //Notificaiton Text
    public static final String NOTIFICATION_TITLE = "Cashkaro Deals";
    public static final String NOTIFICATION_TEXT = "Congratulations you have clicked on ";

    //OfferDetails
    public static final String OFFER_DETAILS_JSON = "cashkaro_offers.json";

    //Dummy placeholder images
    public static final int[] couponImages = new int[]{
            R.drawable.kraftly_coupon,
            R.drawable.ajio_coupon,
            R.drawable.makemytrip_coupon,
            R.drawable.nnnow_coupons,
            R.drawable.johnjacobs_coupons,
            R.drawable.magzter_coupon,
            R.drawable.aplava_coupons,
            R.drawable.pepperfry_coupon
    };

    public static final String STORE_URL = "storedetails_url";

    //Banner Deals
    public static final int[] BANNER_IMAGE_LIST = new int[]{R.drawable.jabong_deals, R.drawable.makemytrip,
            R.drawable.johnjacobs, R.drawable.nykaa
            , R.drawable.tata_cliq, R.drawable.mobile};

    public static final String[] BANNER_IMAGE_URL = new String[]{
            "https://cashkaro.com/stores/jabong",
            "https://cashkaro.com/stores/makemytrip-domesticflights",
            "https://cashkaro.com/stores/john-jacobs-coupons",
            "https://cashkaro.com/stores/nykaa",
            "https://cashkaro.com/stores/tatacliq-coupons",
            "https://cashkaro.com/shop/4g-mobile-offers?utm_source=dynamic-homepage&utm_medium=box-4&utm_campaign=mobiles-offers"
    };

    public static final String[] BANNER_RETAILERS = new String[]{
            "Jabong",
            "Make my trip",
            "John Jacobs",
            "Nykaa",
            "Tata Cliq",
            "Mobile"
    };

    //Facebook permissions
    //"email", "user_photos", "public_profile"
    public static final String[] FACEBOOK_PERMISSIONS = new String[]{
            "user_photos", "public_profile"
    };

    public static final String FACEBOOK_WELCOME_MESSAGE = "Welcome ";
    public static final String FACEBOOK_WELCOME_GUEST = "Welcome Guest";

    //Analytics Messages
    public static final String IMAGE_SLIDER_BANNER_VIEWED = "Banner Ad Viewed ";
}
