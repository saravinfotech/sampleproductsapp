package cashkaro.com.cashkarodeals.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.model.CashKaroOfferList;
import cashkaro.com.cashkarodeals.utils.AnalyticsHelper;
import cashkaro.com.cashkarodeals.utils.AnimationUtil;
import cashkaro.com.cashkarodeals.utils.Constants;
import cashkaro.com.cashkarodeals.utils.DisplayLocalNotification;
import cashkaro.com.cashkarodeals.view.BaseActivity;
import cashkaro.com.cashkarodeals.view.Home;
import cashkaro.com.cashkarodeals.view.storedetails.StoreDetailsActivity;

/**
 * Adapter to handle the categories on different categories
 */

public class CouponsAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final CashKaroOfferList offerList;
    private int previousPosition = 0;

    public CouponsAdapter(Context context, CashKaroOfferList offerList) {
        this.context = context;
        this.offerList = offerList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_deals_category_row, parent, false);
        return new BestDealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        ((BestDealsViewHolder) holder).bindView(position);
        if (position > previousPosition) { //scrolling down
            AnimationUtil.animate(holder, true);
        } else { //scrolling up
            AnimationUtil.animate(holder, false);
        }
        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        return offerList.getCashKaroOfferDetails().size();//BestDealsCategoryModel.couponImages.length;
    }

    public class BestDealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView couponsImage;
        public final TextView couponsDescription;
        public final Button activateCouponBtn;
        public final TextView viewOffersTxt;
        String couponDetails, storeDetails, retailerName;

        public BestDealsViewHolder(View itemView) {
            super(itemView);
            //noinspection RedundantCast
            couponsImage = (ImageView) itemView.findViewById(R.id.couponImage);
            //noinspection RedundantCast
            couponsDescription = (TextView) itemView.findViewById(R.id.couponsDescription);
            //noinspection RedundantCast
            activateCouponBtn = (Button) itemView.findViewById(R.id.activateCouponBtn);
            //noinspection RedundantCast
            viewOffersTxt = (TextView) itemView.findViewById(R.id.viewOffersTxt);
            activateCouponBtn.setOnClickListener(this);
        }

        //String imageURL, ImageView imageView, final ImageView errorImage
        public void bindView(int position) {
            String imageName = offerList.getCashKaroOfferDetails().get(position).getRetailerCouponImage();
            if (imageName.contains("http")) {
                ((BaseActivity) context).loadImage(imageName, couponsImage, couponsImage);
            } else {
                couponsImage.setImageResource(Constants.couponImages[position]);
            }

            couponDetails = offerList.getCashKaroOfferDetails().get(position).getRetailerCouponDescription();
            couponsDescription.setText(couponDetails);
            storeDetails = offerList.getCashKaroOfferDetails().get(position).getRetailerStoreDetail();
            retailerName = offerList.getCashKaroOfferDetails().get(position).getRetailerName();
            AnalyticsHelper.logCrashlytics("User Clicked on " + retailerName);
            AnalyticsHelper.logCrashlytics("Coupon viewed is " + couponsDescription);

        }

        @Override
        public void onClick(View view) {
            DisplayLocalNotification objDisplayLocalNotification = new DisplayLocalNotification();
            FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
            mFirebaseAnalytics.setUserProperty("Deal Tapped", retailerName);
            objDisplayLocalNotification.displayLocalNotification(context, retailerName, Home.class);
            Intent openStoreDetails = new Intent(context, StoreDetailsActivity.class);
            openStoreDetails.putExtra(Constants.STORE_URL, storeDetails);
            context.startActivity(openStoreDetails);
            /*Intent intent = new Intent(Constants.STORE_DETAILS_INTENT);
            intent.putExtra(Constants.STORE_URL,storeDetails);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);*/
        }
    }
}
