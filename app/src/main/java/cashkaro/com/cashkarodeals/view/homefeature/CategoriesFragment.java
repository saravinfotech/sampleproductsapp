package cashkaro.com.cashkarodeals.view.homefeature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cashkaro.com.cashkarodeals.R;
import cashkaro.com.cashkarodeals.adapter.CategoriesAdapter;

/**
 * Fragment to initialize the Tab Bar with multiple Categories
 */

public class CategoriesFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private TabLayout categoryHeaderLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main,
                container, false);
        //noinspection RedundantCast
        categoryHeaderLayout = (TabLayout) view.findViewById(R.id.categoryHeaderLayout);
        //noinspection RedundantCast
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        setUpCategories();
        return view;
    }

    //Setup different Fragment Headers
    private void setUpCategories() {
        categoryHeaderLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setAdapter(new CategoriesAdapter(getActivity().getSupportFragmentManager(), categoryHeaderLayout.getTabCount()));
        categoryHeaderLayout.setupWithViewPager(viewPager);
        categoryHeaderLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
