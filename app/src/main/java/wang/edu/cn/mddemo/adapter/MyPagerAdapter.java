package wang.edu.cn.mddemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;


/**
 * Created by wangdechang on 2016/3/9.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list = null;
    private String[] arrTabTitles = null;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> list, String[] arrTabTitles) {
        super(fm);
        this.list = list;
        this.arrTabTitles = arrTabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arrTabTitles[position];
    }

}
