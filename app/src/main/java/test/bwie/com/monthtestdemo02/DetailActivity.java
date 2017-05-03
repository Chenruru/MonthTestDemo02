package test.bwie.com.monthtestdemo02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name1");
        String name2 = intent.getStringExtra("name2");
        if ("花王品牌专场".equals(name1)){
            new MyXutils(mLv,DetailActivity.this,mTitle).getDetail(UrL.path);
        }else {
            new MyXutils(mLv,DetailActivity.this,mTitle).getBeauty(UrL.path);
        }


    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.text_title);
        mLv = (ListView) findViewById(R.id.lv);
    }
}
