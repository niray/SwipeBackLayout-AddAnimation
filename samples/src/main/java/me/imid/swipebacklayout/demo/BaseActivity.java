package me.imid.swipebacklayout.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import de.greenrobot.event.EventBus;

/**
 * Created by Issac on 8/11/13.
 */
public class BaseActivity extends Activity {
    private View ll_parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ll_parent = findViewById(R.id.ll_parent);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this, DemoActivity.class));
            }
        });
        startActivity(new Intent(BaseActivity.this, DemoActivity.class));
        EventBus.getDefault().register(this);
    }

    public void onEvent(Object event) {
        //80
        float e = (float) event;
        float per = e + 0.5f;
        if (per > 1) return;
        ViewHelper.setAlpha(ll_parent, per);
        ViewHelper.setScaleX(ll_parent, per);
        ViewHelper.setScaleY(ll_parent, per);
        Log.e("onEvent", "onEvent: " + per);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
