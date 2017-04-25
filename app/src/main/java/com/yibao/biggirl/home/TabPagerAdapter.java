package com.yibao.biggirl.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：Stran on 2017/3/23 03:31
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class TabPagerAdapter
        extends FragmentPagerAdapter
{
    List<Fragment> mFragmentList;
    List<String>   mTitleList;
    private final FragmentManager     mFragmentManager;
    private       FragmentTransaction mTransaction;

    public TabPagerAdapter(FragmentManager fm,
                           List<Fragment> fragmentList,
                           List<String> titleList)
    {
        super(fm);
        mFragmentManager = fm;
        mFragmentList = fragmentList;
        mTitleList = titleList;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null
               ? 0
               : mFragmentList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
