package cashkaro.com.cashkarodeals.view;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.utils.Constants;
import cashkaro.com.cashkarodeals.utils.NetworkReceiver;
import cashkaro.com.cashkarodeals.view.checknetwork.NoNetworkActivity;

/**
 * Common actions to be used across the application
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @SuppressWarnings("WeakerAccess")
    protected DrawerLayout drawer;
    private NetworkReceiver networkReceiver;
    private Dialog networkDialog = null;
    private CallbackManager faceBookCallbackManager;
    private TextView welcomeMessage;
    private ProfileTracker profileTracker;
    private AccessTokenTracker accessTokenTracker;
    private ImageView profileImage;
    private Context context;
    private final FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            /*AccessToken accessToken = */
            loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayFaceBookDetails(profile, welcomeMessage, profileImage);
        }

        @Override
        public void onCancel() {
            Toast.makeText(context, R.string.facebookLoginCancel, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(FacebookException error) {
            Toast.makeText(context, R.string.facebookLoginError + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Setup Navigation Drawer
        generateFacebookHashKey();
        setupNavigationBar();
    }

    /**
     * Method to setup the navigation bar
     */
    private void setupNavigationBar() {
        @SuppressWarnings("RedundantCast") Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection RedundantCast
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        @SuppressWarnings("RedundantCast") NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        attachNavigationViewHeaderLayout(navigationView, this);
    }

    /**
     * Navigation Drawer Header View
     *
     * @param navigationView navigation view to which the header needs to be attached
     * @param context        Context to display the toast
     */
    protected void attachNavigationViewHeaderLayout(NavigationView navigationView, Context context) {
        this.context = context;
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        @SuppressWarnings("RedundantCast") final LoginButton facebookLogin = (LoginButton) headerView.findViewById(R.id.facebookLoginBtn);
        //noinspection RedundantCast
        welcomeMessage = (TextView) headerView.findViewById(R.id.welcomeMessage);
        //noinspection RedundantCast
        profileImage = (ImageView) headerView.findViewById(R.id.profileImage);
        faceBookCallbackManager = CallbackManager.Factory.create();
        facebookLogin.setReadPermissions(Constants.FACEBOOK_PERMISSIONS);
        facebookLogin.registerCallback(faceBookCallbackManager, facebookCallback);
        profileTracker = getProfileTracker(welcomeMessage, profileImage);
        accessTokenTracker = getAccessTokenTracker(welcomeMessage, profileImage);
        Profile.fetchProfileForCurrentAccessToken();
        displayFaceBookDetails(Profile.getCurrentProfile(), welcomeMessage, profileImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerNetworkListener();
        profileTracker.startTracking();
        accessTokenTracker.startTracking();
    }

    /**
     * Register the Listener to for Network Change events
     */
    private void registerNetworkListener() {
        // Registers BroadcastReceiver to track network connection changes.
        IntentFilter filter = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);
        networkReceiver = new NetworkReceiver();
        this.registerReceiver(networkReceiver, filter);
    }

    /**
     * Un-Register the Network listener
     */
    private void unRegisterNetworkListener() {
        if (networkReceiver != null) {
            this.unregisterReceiver(networkReceiver);
        }
    }

    /**
     * Method which gets called on network status change
     * from the braodcast receiver. This is an extension of the logic
     * in NetworkRecever Class
     *
     * @param isNetworkAvailable returns if the network is available or not to display alternative
     *                           screen
     */
    public void onNetworkChange(boolean isNetworkAvailable) {
        if (!isNetworkAvailable) {
            stopNetworkCall();
            if (!(this instanceof NoNetworkActivity)) {
                showNetworkDialog();
            }
        } else {
            closeNetworkDialog();
        }
    }

    /**
     * Dialog to be displayed when no network is available
     */
    private void showNetworkDialog() {
        if (networkDialog == null) {
            networkDialog = new Dialog(this);
            networkDialog.setContentView(R.layout.network_dialog);
            if (networkDialog.getWindow() != null) {
                networkDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                networkDialog.setCancelable(false);
                networkDialog.setTitle("Title...");
                networkDialog.show();
            }
        }
    }

    private void closeNetworkDialog() {
        if (networkDialog != null) {
            networkDialog.dismiss();
            networkDialog = null;
        }
    }

    /**
     * Code to be included for stop network calls if any
     * when network call is unavailable
     */
    @SuppressWarnings("EmptyMethod")
    private void stopNetworkCall() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        unRegisterNetworkListener();
    }

    // Runtime Permission Request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.PERMISSION_REQUEST_CAMERA:
                permissionGranted(grantResults, Constants.PERMISSION_NAME_CAMERA);
                break;
            case Constants.PERMISSION_REQUEST_LOCATION:
                permissionGranted(grantResults, Constants.PERMISSION_NAME_LOCATION);
                break;
        }
    }

    // Runtime Permission Request
    private void permissionGranted(int[] grantResults, String permissionName) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, permissionName + Constants.PERMISSION_GRANTED_MESSAGE, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, permissionName + Constants.PERMISSION_DENIED_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }

    // Runtime Permission Request
    @SuppressWarnings("WeakerAccess")
    protected void checkGrantedRuntimePermissions(String permissionType, int permissionRequestType) {
        if (ContextCompat.checkSelfPermission(this, permissionType) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permissionType}, permissionRequestType);
        } else {
            if (permissionRequestType == Constants.PERMISSION_REQUEST_CAMERA)
                Toast.makeText(this, Constants.PERMISSION_NAME_CAMERA + Constants.PERMISSION_GRANTED_MESSAGE, Toast.LENGTH_SHORT).show();
            else if (permissionRequestType == Constants.PERMISSION_REQUEST_LOCATION)
                Toast.makeText(this, Constants.PERMISSION_NAME_LOCATION + Constants.PERMISSION_GRANTED_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }

    public void loadImage(String imageURL, ImageView imageView, final ImageView errorImage) {
        Picasso.with(this)
                .load(imageURL)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        errorImage.setImageResource(R.drawable.image_not_available);
                    }
                });
    }

    void loadFragmentContents(int fragmentContainer, Fragment fragmentObject) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragmentContainer, fragmentObject);
        fragmentTransaction.commit();
    }

    private ProfileTracker getProfileTracker(final TextView welcomeMessage, final ImageView profileImage) {
        return new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                displayFaceBookDetails(currentProfile, welcomeMessage, profileImage);
            }
        };
    }

    private void displayFaceBookDetails(Profile profile, TextView welcomeMessage, final ImageView profileImage) {
        if (profile != null) {
            String welcomMessage = Constants.FACEBOOK_WELCOME_MESSAGE + profile.getName();
            welcomeMessage.setText(welcomMessage);
            loadImage(String.valueOf(profile.getProfilePictureUri(200, 200)), profileImage, profileImage);
        }
    }

    private AccessTokenTracker getAccessTokenTracker(final TextView welcomeMessage, final ImageView profileImage) {
        return new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // On AccessToken changes fetch the new profile which fires the event on
                // the ProfileTracker if the profile is different
                Profile.fetchProfileForCurrentAccessToken();
                if (currentAccessToken == null)
                    facebookLogout(welcomeMessage, profileImage);
            }
        };
    }

    private void facebookLogout(TextView welcomeMessage, ImageView profileImage) {
        welcomeMessage.setText(Constants.FACEBOOK_WELCOME_GUEST);
        profileImage.setImageResource(R.drawable.profile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        faceBookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
        accessTokenTracker.stopTracking();
    }

    @SuppressWarnings("WeakerAccess")
    protected void handleNavigationMenuActions(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            //Camera runtime permission handling
            checkGrantedRuntimePermissions(Manifest.permission.CAMERA, Constants.PERMISSION_REQUEST_CAMERA);
        } else if (id == R.id.nav_location) {
            //Location runtime permission handling
            checkGrantedRuntimePermissions(Manifest.permission.ACCESS_FINE_LOCATION, Constants.PERMISSION_REQUEST_LOCATION);
        }
        drawer.closeDrawer(GravityCompat.START);
    }


    /**
     * Method to handle the Navigation Drawer open close actions
     */
    @SuppressWarnings("WeakerAccess")
    protected void handleNavigationDrawer() {
        //Handle Navigation Drawer open close action
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        handleNavigationMenuActions(item);
        return true;
    }

    private void generateFacebookHashKey(){
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "cashkaro.com.cashkarodeals",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
