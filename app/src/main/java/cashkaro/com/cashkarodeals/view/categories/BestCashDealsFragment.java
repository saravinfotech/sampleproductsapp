package cashkaro.com.cashkarodeals.view.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.adapter.CouponsAdapter;
import cashkaro.com.cashkarodeals.model.CashKaroOfferList;
import cashkaro.com.cashkarodeals.utils.Constants;

/**
 * Fragment for displaying eh Best Cash Deals Tab
 */

public class BestCashDealsFragment extends Fragment {

    private CashKaroOfferList offerList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jsonString = loadJson();
        Gson gson = new Gson();
        offerList = gson.fromJson(jsonString, CashKaroOfferList.class);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.best_cash_deals_categories, container, false);
        @SuppressWarnings("RedundantCast") RecyclerView couponListView = (RecyclerView) view.findViewById(R.id.recyclerView);

        CouponsAdapter couponseAdapter = new CouponsAdapter(getActivity(), offerList);
        couponListView.setAdapter(couponseAdapter);
       /* RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());*/
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        //RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        couponListView.setLayoutManager(layoutManager);
        return view;
    }

    private String loadJson() {
        String json;
        try {
            InputStream is = getActivity().getAssets().open(Constants.OFFER_DETAILS_JSON);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
