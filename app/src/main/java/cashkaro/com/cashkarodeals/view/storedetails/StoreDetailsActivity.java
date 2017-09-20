package cashkaro.com.cashkarodeals.view.storedetails;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.utils.Constants;
import cashkaro.com.cashkarodeals.view.BaseActivity;
import cashkaro.com.cashkarodeals.view.Home;

public class StoreDetailsActivity extends BaseActivity {

    private ProgressBar progressbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        String url = getIntent().getStringExtra(Constants.STORE_URL);
        progressbar= (ProgressBar)findViewById(R.id.progress_bar);
        progressbar.setVisibility(View.VISIBLE);
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        builder.setToolbarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        builder.setShowTitle(true);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chrometab_home);
        Intent intent = new Intent(StoreDetailsActivity.this,Home.class);
        int requestCode = 100;

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setActionButton(bitmap, "Home", pendingIntent, true);

        customTabsIntent.intent.setData(Uri.parse(url));
        startActivityForResult(customTabsIntent.intent, Constants.CHROME_CUSTOM_TAB_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.CHROME_CUSTOM_TAB_REQUEST_CODE) {
            finish();
        }
    }

    public void onBackPressed() {
            super.onBackPressed();
    }
}
