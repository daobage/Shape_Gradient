package com.example.uworks.shape_gradient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.uworks.shape_gradient.fragment.ChatFragment;
import com.example.uworks.shape_gradient.fragment.ContactsFragment;
import com.example.uworks.shape_gradient.fragment.FriendFragment;
import com.example.uworks.shape_gradient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UWorks on 2016/10/9.
 */
public class ViewPagerActivity extends android.support.v4.app.FragmentActivity {
    ViewPager pager;
    Fragment chat;
    Fragment friend;
    Fragment contacts;
    TextView chattab,friendtab,contactstab;
    ImageView tab;
    int screenWidth;
    int currentindex;
    FragmentPagerAdapter adapter;
    List<Fragment> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frament_tab);
        initview();
//        initTabWidth();
        initData();

    }
    private void initview(){
        pager = (ViewPager) findViewById(R.id.pager);
        chattab = (TextView) findViewById(R.id.chat);
        friendtab = (TextView) findViewById(R.id.friend);
        contactstab = (TextView) findViewById(R.id.contacts);
        tab = (ImageView) findViewById(R.id.image);
    }
    private void initData(){
        list = new ArrayList<>();
        chat = new ChatFragment();
        friend = new FriendFragment();
        contacts = new ContactsFragment();
        list.add(chat);
        list.add(friend);
        list.add(contacts);
        adapter = new MyAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tab.getLayoutParams();
                    lp.leftMargin = (int) (positionOffset*(screenWidth*1.0/3)+currentindex*screenWidth/3);
                Log.d("onpagescrolled","positionOffsetPixels="+positionOffsetPixels);
                tab.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
//    private void initTabWidth(){
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        screenWidth = metrics.widthPixels;
//        LinearLayout.LayoutParams LP = (LinearLayout.LayoutParams) tab.getLayoutParams();
//        LP.width = screenWidth/3;
//        tab.setLayoutParams(LP);
//    }
class MyAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public MyAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
};
}
