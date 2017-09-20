package cashkaro.com.cashkarodeals.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Offer List model class for the local JSON Files
 */

public class CashKaroOfferList {

    @SerializedName("CashKaroOfferDetails")
    @Expose
    private List<CashKaroOfferDetail> cashKaroOfferDetails = null;

    public List<CashKaroOfferDetail> getCashKaroOfferDetails() {
        return cashKaroOfferDetails;
    }

    public void setCashKaroOfferDetails(List<CashKaroOfferDetail> cashKaroOfferDetails) {
        this.cashKaroOfferDetails = cashKaroOfferDetails;
    }

}

