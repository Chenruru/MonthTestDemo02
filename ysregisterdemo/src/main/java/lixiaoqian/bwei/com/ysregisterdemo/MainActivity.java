package lixiaoqian.bwei.com.ysregisterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<Bean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mList = new ArrayList<>();
        for(int i=1;i<11;i++) {
            mList.add(new Bean("你的微笑" + i, "好似毒药" + i));
        }

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter());

        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_CANCEL:
                        Toast.makeText(MainActivity.this, "ACTION_CANCEL", Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(MainActivity.this, "ACTION_DOWN", Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Toast.makeText(MainActivity.this, "ACTION_MOVE", Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(MainActivity.this, "ACTION_UP", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(MainActivity.this,R.layout.lv_item,null);
                holder=new ViewHolder();
                holder.tv1= (TextView) convertView.findViewById(R.id.textView);
                holder.tv2= (TextView) convertView.findViewById(R.id.textView2);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.tv1.setText(mList.get(position).getTv1().toString());
            holder.tv2.setText(mList.get(position).getTv2().toString());
    /*        holder.tv1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_CANCEL:
                            Log.d("ontouch---tv1","ACTION_CANCEL");
                            break;
                        case MotionEvent.ACTION_DOWN:
                            Log.d("ontouch---tv1","ACTION_DOWN");
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.d("ontouch---tv1","ACTION_MOVE");
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.d("ontouch---tv1","ACTION_UP");
                            break;
                    }
                    return false;
                }
            });

            holder.tv2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_CANCEL:
                            Toast.makeText(MainActivity.this, "ACTION_CANCEL", Toast.LENGTH_SHORT).show();
                            break;
                        case MotionEvent.ACTION_DOWN:
                            Toast.makeText(MainActivity.this, "ACTION_DOWN", Toast.LENGTH_SHORT).show();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Toast.makeText(MainActivity.this, "ACTION_MOVE", Toast.LENGTH_SHORT).show();
                            break;
                        case MotionEvent.ACTION_UP:
                            Toast.makeText(MainActivity.this, "ACTION_UP", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    return false;
                }
            });
*/
            return convertView;
        }
    }

    class ViewHolder{
        TextView tv1;
        TextView tv2;
    }

}
