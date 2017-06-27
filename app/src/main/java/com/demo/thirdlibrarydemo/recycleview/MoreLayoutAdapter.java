package com.demo.thirdlibrarydemo.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.thirdlibrarydemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class MoreLayoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<String> datas;
    private LayoutInflater mLayoutInflater;
    private static final int TYPE_1=1;
    private static final int TYPE_2=2;

    public MoreLayoutAdapter(Context context, ArrayList<String> datas) {
        this.context=context;
        this.datas=datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 相当于getView（）创建View和ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_1){
            return new MyViewHolder1(mLayoutInflater.inflate(R.layout.itemview, parent, false));
        }else{
            return new MyViewHolder2(mLayoutInflater.inflate(R.layout.itemview1, parent, false));
        }
    }

    /**
     * 数据和View绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder1){
            ((MyViewHolder1)holder).text.setText(datas.get(position)+"TYPE_1");
//            if (position%2==0){
//                ((MyViewHolder1)holder).img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher));
//            } else{//添加此处else,recyclerView在滑动过程中不会显示错乱，具体原因还不清楚
//                ((MyViewHolder1)holder).img.setImageDrawable(context.getResources().getDrawable(R.mipmap.download_nothing));
//            }
        } else if (holder instanceof MyViewHolder2){
            ((MyViewHolder2)holder).text.setText(datas.get(position)+"TYPE_2");
//            if (position%2==0){
//                ((MyViewHolder2)holder).img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher));
//            } else{//添加此处else,recyclerView在滑动过程中不会显示错乱，具体原因还不清楚
//                ((MyViewHolder2)holder).img.setImageDrawable(context.getResources().getDrawable(R.mipmap.download_nothing));
//            }
        }

    }

    /**
     * 得到总条数
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 判断使用哪种布局
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position%5==0){
            return TYPE_1;
        } else{
            return TYPE_2;
        }
    }

    /**
     * 添加数据
     * @param position
     * @param s
     */
    public void addData(int position, String s) {
        datas.add(position,s);
        //刷新：增加数据
        notifyItemInserted(position);
    }

    /**
     * 移除数据
     * @param position
     * @param s
     */
    public void removeData(int position, String s) {
        datas.remove(position);
        //刷新：移除数据
        notifyItemRemoved(position);
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView text;

        public MyViewHolder1(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
            text= (TextView) itemView.findViewById(R.id.text);

            //第一种写法：设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //getLayoutPosition()：获取当前点击的位置
//                    Toast.makeText(context,datas.get(getLayoutPosition()),Toast.LENGTH_SHORT);
                    if (onItemClickListener!=null){
                        onItemClickListener.OnItemClick(view,datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView text;

        public MyViewHolder2(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
            text= (TextView) itemView.findViewById(R.id.text);

            //第一种写法：设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //getLayoutPosition()：获取当前点击的位置
//                    Toast.makeText(context,datas.get(getLayoutPosition()),Toast.LENGTH_SHORT);
                    if (onItemClickListener!=null){
                        onItemClickListener.OnItemClick(view,datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    /**
     *第二种写法：回调
     */
    public interface OnItemClickListener{

        /**
         * 某条点击的回调
         * @param view
         * @param data
         */
        public void OnItemClick(View view, String data);
    }

    private OnItemClickListener onItemClickListener;

    /**
     * 某条点击的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
