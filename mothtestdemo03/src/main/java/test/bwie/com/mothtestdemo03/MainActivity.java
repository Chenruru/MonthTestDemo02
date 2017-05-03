package test.bwie.com.mothtestdemo03;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button button2;
    private ListView lv;
    private static File CacheRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
      //  initData();
    }
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static void setNetworkMethod(final Context context) {
        //提示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent intent = null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if (Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();
    }
    @SuppressWarnings("resource")
    public static String readJson(Context c, String fileName) {

        CacheRoot = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED ? c
                .getExternalCacheDir() : c.getCacheDir();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        String result = new String();
        File des = new File(CacheRoot, fileName);
        try {
            fis = new FileInputStream(des);
            ois = new ObjectInputStream(fis);
            while (fis.available() > 0)
                result = (String) ois.readObject();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

        return result;
    }
    public void getXutil(final Context context) {
        RequestParams params = new RequestParams("http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime=20160411091603");
        x.http().get(params, new Callback.CommonCallback<String>() {
            public void onSuccess(String result) {
                writeJson(context, result, "MyCache", true);
                Gson gson = new Gson();
                GsonBean cityBean = gson.fromJson(result, GsonBean.class);
                GsonBean.ResultBean resultBean = cityBean.getResult();
                List<GsonBean.ResultBean.RowsBean> rws = resultBean.getRows();
                MyAdapter adapter = new MyAdapter(rws, MainActivity.this);
                lv.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {


            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });
    }
    public static void writeJson(Context c, String json, String fileName,
                                 boolean append) {

        CacheRoot = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED ? c
                .getExternalCacheDir() : c.getCacheDir();
        FileOutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            File ff = new File(CacheRoot, fileName);
            boolean boo = ff.exists();
            fos = new FileOutputStream(ff, append);
            os = new ObjectOutputStream(fos);
            if (append && boo) {
                FileChannel fc = fos.getChannel();
                fc.truncate(fc.position() - 4);

            }

            os.writeObject(json);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (os != null) {

                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNetworkConnected(this)) {
            getXutil(this);
        } else {
            String myCache = readJson(this, "MyCache");
            if (myCache != null) {
                Gson gson = new Gson();
                GsonBean cityBean = gson.fromJson(myCache, GsonBean.class);
                GsonBean.ResultBean resultBean = cityBean.getResult();
                List<GsonBean.ResultBean.RowsBean> rows = resultBean.getRows();

                MyAdapter adapter = new MyAdapter(rows, MainActivity.this);
                lv.setAdapter(adapter);
            }

        }
    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        lv = (ListView) findViewById(R.id.lv);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
    public void getHighXutil(final Context context, final int id) {
        RequestParams params = new RequestParams("http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime=20160411091603");
        x.http().get(params, new Callback.CommonCallback<String>() {
            public void onSuccess(String result) {
                //writeJson(context, result, "MyCache", true);
               Gson gson=new Gson();
                GsonBean gsonBean = gson.fromJson(result, GsonBean.class);
                GsonBean.ResultBean resultBean = gsonBean.getResult();
                List<GsonBean.ResultBean.RowsBean> rows = resultBean.getRows();
                List<GsonBean.ResultBean.RowsBean> rows1 = new ArrayList<GsonBean.ResultBean.RowsBean>();
                List<GsonBean.ResultBean.RowsBean> rows2 = new ArrayList<GsonBean.ResultBean.RowsBean>();
                for(GsonBean.ResultBean.RowsBean crr:rows){
                    if(crr.getInfo().getNew_price_back().equals("万元/套")||crr.getInfo().getNew_price_back().equals("万元/套起")){
                        rows1.add(crr);
                    }else if(crr.getInfo().getNew_price_back().equals("元/m²")){
                        rows2.add(crr);
                    }
                }
                if (R.id.button==id){
                    Collections.sort(rows1,new CalendarComparator());
                    Collections.sort(rows2,new CalendarComparator());
                }else {
                    Collections.sort(rows1,new CalendarComparator1());
                    Collections.sort(rows2,new CalendarComparator1());
                }

                rows.clear();
                rows.addAll(rows2);
                rows.addAll(rows2.size(),rows1);
                for(int x=0; x< rows.size(); x++){
                    Log.d("zzz",rows.get(x).getInfo().getNew_price_value()+rows.get(x).getInfo().getNew_price_back());
                }
                MyAdapter adapter = new MyAdapter(rows, MainActivity.this);
                lv.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {


            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                getHighXutil(MainActivity.this,R.id.button);
                break;
            case R.id.button2:
                getHighXutil(MainActivity.this,R.id.button2);
                break;
        }
    }

    static class CalendarComparator implements Comparator {
        public int compare(Object object1, Object object2) {// 实现接口中的方法
            GsonBean.ResultBean.RowsBean p1 = (GsonBean.ResultBean.RowsBean) object1; // 强制转换
            GsonBean.ResultBean.RowsBean p2 = (GsonBean.ResultBean.RowsBean) object2;
            return Integer.valueOf(p2.getInfo().getNew_price_value()).compareTo(Integer.valueOf(p1.getInfo().getNew_price_value()));

        }


    }
    static class CalendarComparator1 implements Comparator {
        public int compare(Object object1, Object object2) {// 实现接口中的方法
            GsonBean.ResultBean.RowsBean p1 = (GsonBean.ResultBean.RowsBean) object1; // 强制转换
            GsonBean.ResultBean.RowsBean p2 = (GsonBean.ResultBean.RowsBean) object2;
            return Integer.valueOf(p1.getInfo().getNew_price_value()).compareTo(Integer.valueOf(p2.getInfo().getNew_price_value()));

        }


    }
}
