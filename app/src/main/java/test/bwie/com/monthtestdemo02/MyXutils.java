package test.bwie.com.monthtestdemo02;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * @ Description:
 * @ Date:2017/4/28
 * @ Author:刘刚
 */

public class MyXutils {
    private ViewPager vp;
    private RadioGroup mRg;
    private Context mContext;
   private  Handler handler;
    private GridView grid;
    private TextView text;
    private ListView lv;

    public MyXutils(ListView lv, Context context, TextView text) {
        this.lv = lv;
        mContext = context;
        this.text = text;
    }

    public MyXutils(ViewPager vp, Handler handler, Context context, RadioGroup rg) {
        this.vp = vp;
        this.handler = handler;
        mContext = context;
        mRg = rg;
    }

    public MyXutils(GridView grid, Context context) {
        this.grid = grid;
        mContext = context;
    }
   public  void  getGrid(String uri){
       RequestParams params = new RequestParams(uri);
       x.http().get(params, new Callback.CommonCallback<String>() {
           @Override
           public void onSuccess(String result) {
               Gson gson = new Gson();
               GsonBean gsonBean = gson.fromJson(result, GsonBean.class);
               List<GsonBean.ResultBean.IndexProductsBean> list = gsonBean.getResult().getIndexProducts();
               grid.setAdapter(new GridAdapter(list,mContext));
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
    public void getBeauty(String uri){
        RequestParams params = new RequestParams(uri);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                GsonBean gsonBean = gson.fromJson(result, GsonBean.class);
                List<GsonBean.ResultBean.BrandsBean> brands = gsonBean.getResult().getBrands();


                String  title1 = brands.get(1).getTitle();
                List<GsonBean.ResultBean.BrandsBean.ProductsBean> products1 = brands.get(1).getProducts();

                text.setText(title1);
                lv.setAdapter(new LvAdapter(products1,mContext));

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
    public  void getDetail(String uri){
        RequestParams params = new RequestParams(uri);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                GsonBean gsonBean = gson.fromJson(result, GsonBean.class);
                List<GsonBean.ResultBean.BrandsBean> brands = gsonBean.getResult().getBrands();
                String  title = brands.get(0).getTitle();
                    List<GsonBean.ResultBean.BrandsBean.ProductsBean> products = brands.get(0).getProducts();

                    text.setText(title);
                    lv.setAdapter(new LvAdapter(products,mContext));



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
    public void getXutils(String uri){
      RequestParams params = new RequestParams(uri);
      x.http().get(params, new Callback.CommonCallback<String>() {
          @Override
          public void onSuccess(String result) {
              Gson gson = new Gson();
              GsonBean gsonBean = gson.fromJson(result, GsonBean.class);



              List<GsonBean.ResultBean.AdvsBean> advs = gsonBean.getResult().getAdvs();
              vp.setAdapter(new MyPagerAdapter(advs,mContext,handler));
              vp.setCurrentItem(advs.size() * 5000);
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
