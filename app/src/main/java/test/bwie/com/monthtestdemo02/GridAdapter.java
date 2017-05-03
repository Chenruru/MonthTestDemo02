package test.bwie.com.monthtestdemo02;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @ Description:
 * @ Date:2017/4/28
 * @ Author:刘刚
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<GsonBean.ResultBean.IndexProductsBean>list;

    public GridAdapter(List<GsonBean.ResultBean.IndexProductsBean> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
        if (convertView==null){
            convertView=View.inflate(mContext,R.layout.item_grad,null);
            holder=new ViewHolder();
            holder.t1= (TextView) convertView.findViewById(R.id.shop_name);
            holder.t2= (TextView) convertView.findViewById(R.id.shop_price);
            holder.image= (ImageView) convertView.findViewById(R.id.shop_image);
         convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();

        }
        holder.t1.setText(list.get(position).getName());
        holder.t2.setText(list.get(position).getPrice()+"");
        ImageLoader.getInstance().displayImage(list.get(position).getPic(),holder.image);
        return convertView;

    }

    class ViewHolder{
        TextView t1;
        TextView t2;
        ImageView image;
    }
}
