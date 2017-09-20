package cashkaro.com.cashkarodeals.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model Class to consume the local JSON to handle the list of coupons on the
 * Home Screen.
 */

public class CashKaroOfferDetail {
    @SerializedName("retailerName")
    @Expose
    private String retailerName;
    @SerializedName("retailerCouponImage")
    @Expose
    private String retailerCouponImage;
    @SerializedName("retailerCouponDescription")
    @Expose
    private String retailerCouponDescription;
    @SerializedName("retailerStoreDetail")
    @Expose
    private String retailerStoreDetail;

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getRetailerCouponImage() {
        return retailerCouponImage;
    }

    public void setRetailerCouponImage(String retailerCouponImage) {
        this.retailerCouponImage = retailerCouponImage;
    }

    public String getRetailerCouponDescription() {
        return retailerCouponDescription;
    }

    public void setRetailerCouponDescription(String retailerCouponDescription) {
        this.retailerCouponDescription = retailerCouponDescription;
    }

    public String getRetailerStoreDetail() {
        return retailerStoreDetail;
    }

    public void setRetailerStoreDetail(String retailerStoreDetail) {
        this.retailerStoreDetail = retailerStoreDetail;
    }

}
