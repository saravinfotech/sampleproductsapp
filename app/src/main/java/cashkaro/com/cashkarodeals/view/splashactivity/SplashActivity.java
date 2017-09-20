package cashkaro.com.cashkarodeals.view.splashactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cashkaro.com.cashkarodeals.view.Home;

/**
 * Class to displaye Splash Activity
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this, Home.class));
        finish();
    }
}