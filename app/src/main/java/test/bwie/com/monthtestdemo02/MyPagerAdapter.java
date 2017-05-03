package test.bwie.com.monthtestdemo02;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @ Description:
 * @ Date:2017/4/28
 * @ Author:刘刚
 */

public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private Handler mHandler;
    private List<GsonBean.ResultBean.AdvsBean>list;

    public MyPagerAdapter(List<GsonBean.ResultBean.AdvsBean> list, Context context, Handler handler) {
        this.list = list;
        mContext = context;
        mHandler = handler;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 手指按下的时候,停止自动轮播的任务
                        // 移除所有的消息及回调 null 移除所有
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
        ImageLoader.getInstance().displayImage(list.get(position%list.size()).getPic(),imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
