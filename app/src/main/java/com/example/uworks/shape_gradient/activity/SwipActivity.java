package com.example.uworks.shape_gradient.activity;

import android.os.Bundle;

import com.example.uworks.shape_gradient.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by UWorks on 2016/9/29.
 */
public class SwipActivity extends SwipeBackActivity {
    SwipeBackLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_swiplayout);
        layout = getSwipeBackLayout();
        layout.setEdgeSize(250);
        layout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
    }
}
