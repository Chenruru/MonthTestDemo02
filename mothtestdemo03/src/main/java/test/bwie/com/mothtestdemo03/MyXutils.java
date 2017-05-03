package test.bwie.com.mothtestdemo03;

import android.content.Context;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ Description:
 * @ Date:2017/4/28
 * @ Author:刘刚
 */

public class MyXutils {

    private Context mContext;
    private ListView lv;

    public MyXutils(ListView lv, Context context) {
        this.lv = lv;
        mContext = context;
    }
    private void sortInfo() {
        }
    public  void  getXutils(String uri){
       RequestParams params = new RequestParams(uri);
       x.http().get(params, new Callback.CommonCallback<String>() {
           @Override
           public void onSuccess(String result) {
               Gson gson = new Gson();
               GsonBean gsonBean = gson.fromJson(result, GsonBean.class);
               List<GsonBean.ResultBean.RowsBean> rows = gsonBean.getResult().getRows();
               Comparator<GsonBean.ResultBean.RowsBean> itemComparator = new Comparator<GsonBean.ResultBean.RowsBean>() {
                   @Override
                   public int compare(GsonBean.ResultBean.RowsBean o1, GsonBean.ResultBean.RowsBean o2) {
                       return o2.getInfo().getNew_price_value().compareTo(o1.getInfo().getNew_price_value());
                   }


               };
               Collections.sort(rows, itemComparator);


               MyAdapter adapter = new MyAdapter(rows, mContext);
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
}
