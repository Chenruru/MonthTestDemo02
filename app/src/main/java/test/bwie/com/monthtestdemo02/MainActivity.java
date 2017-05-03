package test.bwie.com.monthtestdemo02;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;

import static test.bwie.com.monthtestdemo02.R.id.vp;

public class MainActivity extends AppCompatActivity {

    private int[]ids={R.id.rb01,R.id.rb02,R.id.rb03,R.id.rb04,R.id.rb05};
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                // 获取viewPager当前所在的页码索引值
                int currentItem = mVp.getCurrentItem();
                currentItem++;
                mVp.setCurrentItem(currentItem);
                mRg.check(ids[currentItem%ids.length]);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        };
    };

    private ViewPager mVp;
    private RadioGroup mRg;
    private TextView mHuawang;
    private TextView mBeauty;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    private void initData() {

       new MyXutils(mVp,handler,MainActivity.this,mRg).getXutils(UrL.path);
        new MyXutils(mGridView,MainActivity.this).getGrid(UrL.path);
        mHuawang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("name1",mHuawang.getText().toString());
                startActivity(intent);
            }
        });
        mBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("name2",mBeauty.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mVp = (ViewPager) findViewById(vp);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mHuawang = (TextView) findViewById(R.id.huawang);
        mBeauty = (TextView) findViewById(R.id.beautiful);
        mGridView = (GridView) findViewById(R.id.grad);
    }
}
