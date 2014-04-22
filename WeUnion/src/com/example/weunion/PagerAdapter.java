package com.example.weunion;

import java.util.List;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
List<Fragment> fragment_list;
public PagerAdapter(FragmentManager fm,List<Fragment> f ) {
		super(fm);
	this.fragment_list = f;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int p) {
		// TODO Auto-generated method stub
		return this.fragment_list.get(p);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.fragment_list.size();
	}

}
