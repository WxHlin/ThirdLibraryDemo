package com.demo.thirdlibrarydemo.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.thirdlibrarydemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<String> datas;

    public MyAdapter(Context context, ArrayList<String> datas) {
        this.context=context;
        this.datas=datas;
    }

    /**
     * 相当于getView（）创建View和ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=View.inflate(context, R.layout.itemview,null);
        return new MyViewHolder(itemView);
    }

    /**
     * 数据和View绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text.setText(datas.get(position));
        if (position%2==0){
            holder.img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher));
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

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView text;

        public MyViewHolder(View itemView) {
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
        public void OnItemClick(View view,String data);
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
