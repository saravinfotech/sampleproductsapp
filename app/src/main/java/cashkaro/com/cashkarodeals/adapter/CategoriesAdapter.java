package cashkaro.com.cashkarodeals.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cashkaro.com.cashkarodeals.utils.Constants;
import cashkaro.com.cashkarodeals.view.categories.BestCashDealsFragment;
import cashkaro.com.cashkarodeals.view.categories.TopCategoryFragment;
import cashkaro.com.cashkarodeals.view.categories.ReferralProgramFragment;

/**
 * Fragment to display different categories on Home page as tab layout.
 */

public class CategoriesAdapter extends FragmentStatePagerAdapter {

    private final String[] categories = {Constants.BEST_DEALS_CATEGORY, Constants.TODAY_OFFER_CATEGORY, Constants.REFERRAL_PROGRAM_CATEGORY};

    public CategoriesAdapter(FragmentManager fm, int categoryCount) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BestCashDealsFragment();
            case 1:
                return new TopCategoryFragment();
            case 2:
                return new ReferralProgramFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories[position];
    }
}
