package cashkaro.com.cashkarodeals.view.checknetwork;

import android.os.Bundle;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.view.BaseActivity;

/**
 * Activity to display when there is no Network
 */

public class NoNetworkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_network);

    }

    public void onNetworkChange(boolean isNetworkAvailable) {
        if (isNetworkAvailable) {
            finish();
        }
    }

}