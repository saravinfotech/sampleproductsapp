package cashkaro.com.cashkarodeals.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.model.TodayOfferCategoryModel;
import cashkaro.com.cashkarodeals.utils.AnimationUtil;

/**
 * Adapter to display items on the Todays Category Adapter
 */

public class TodayOfferAdapter extends RecyclerView.Adapter {

    private int previousPosition = 0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_offer_category_row
                , parent, false);
        return new TodayOfferAdapter.TodayOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        ((TodayOfferAdapter.TodayOfferViewHolder) holder).bindView(position);
        if (position > previousPosition) { //scrolling down
            AnimationUtil.animate(holder, true);
        } else { //scrolling up
            AnimationUtil.animate(holder, false);
        }
        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        return TodayOfferCategoryModel.couponImages.length;
    }

    public class TodayOfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView categoryCouponImage;
        public final TextView categoryHeader;

        public TodayOfferViewHolder(View itemView) {
            super(itemView);
            //noinspection RedundantCast
            categoryCouponImage = (ImageView) itemView.findViewById(R.id.categoryCouponImage);
            //noinspection RedundantCast
            categoryHeader = (TextView) itemView.findViewById(R.id.categoryHeader);
        }

        public void bindView(int position) {
            categoryCouponImage.setImageResource(TodayOfferCategoryModel.couponImages[position]);
            categoryHeader.setText(TodayOfferCategoryModel.couponDescription[position]);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
