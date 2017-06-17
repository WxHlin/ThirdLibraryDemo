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
    }

    /**
     * 得到总条数
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
            text= (TextView) itemView.findViewById(R.id.text);
        }
    }
}
