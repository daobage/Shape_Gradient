package com.example.uworks.shape_gradient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.uworks.shape_gradient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UWorks on 2016/10/9.
 */
public class FragmentActivity extends AppCompatActivity {
    View view1,view2,view3;
    ViewPager pager;
    List<View> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewpager);
        init();
    }

    private void init() {
        pager = (ViewPager) findViewById(R.id.viewpager);
        view1 = View.inflate(this,R.layout.layout_fragment1,null);
        view2 = View.inflate(this,R.layout.layout_fragment2,null);
        view3 = View.inflate(this,R.layout.layout_fragment3,null);
        list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(list.get(position),0);
                return list.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(list.get(position));
            }
        };
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
    }
}
