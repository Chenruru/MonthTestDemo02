package test.bwie.com.mothtestdemo03;

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

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<GsonBean.ResultBean.RowsBean>list;

    public MyAdapter(List<GsonBean.ResultBean.RowsBean> list, Context context) {
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
            convertView=View.inflate(mContext,R.layout.item,null);
            holder=new ViewHolder();
            holder.image= (ImageView) convertView.findViewById(R.id.pic);
            holder.t1= (TextView) convertView.findViewById(R.id.name);
            holder.t2= (TextView) convertView.findViewById(R.id.location);
            holder.t3= (TextView) convertView.findViewById(R.id.price);
            holder.t4= (TextView) convertView.findViewById(R.id.per);
         convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.t1.setText(list.get(position).getInfo().getLoupan_name());
        holder.t2.setText(list.get(position).getInfo().getRegion_title());
        holder.t3.setText(list.get(position).getInfo().getNew_price_value());
        holder.t4.setText(list.get(position).getInfo().getNew_price_back());
        ImageLoader.getInstance().displayImage(list.get(position).getInfo().getDefault_image(),holder.image);
        return convertView;
    }
    class ViewHolder{
        TextView t1;
        TextView t2;
        TextView t3;
        TextView t4;
        ImageView image;
    }
}
