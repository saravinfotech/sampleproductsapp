package cashkaro.com.cashkarodeals.view.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.adapter.TodayOfferAdapter;

/**
 * Fragment to display Top Category Fragment Screen
 */

public class TopCategoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_category, container, false);
        @SuppressWarnings("RedundantCast") RecyclerView couponListView = (RecyclerView) view.findViewById(R.id.recyclerView);
        TodayOfferAdapter todayOfferAdapter = new TodayOfferAdapter();
        couponListView.setAdapter(todayOfferAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        couponListView.setLayoutManager(layoutManager);
        return view;
    }
}
