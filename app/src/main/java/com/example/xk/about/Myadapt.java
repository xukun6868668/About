package com.example.xk.about;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 15-7-20.
 */
public class Myadapt extends RecyclerView.Adapter<Myadapt.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<String>list;
    private OnclickListener listener;
    private RecyclerView recyclerView;

    public Myadapt(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView=recyclerView;
    }

    public void setOnclickLisener(OnclickListener lisener){

        this.listener=lisener;
    }

    public void remove(int position){
        list.remove(position);
        notifyItemRemoved(position);//用这个方法才能使动画有效果
       // notifyDataSetChanged();
    }

    /**
     * 创建viewholder
     * @param parent
     * @param viewType 如果有多个布局，可以通过viewType去弄
     * @return
     */

    //把数据绑定到viewholder上
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item,null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.item.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            int position=recyclerView.getChildAdapterPosition(v);
            listener.onChildClick(v,position,list.get(position));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private  TextView item;

        public ViewHolder(View itemView) {
            super(itemView);
            item = ((TextView) itemView.findViewById(R.id.item));
        }
    }

    public interface OnclickListener{
        void onChildClick(View view,int position,String data);
    }
}
