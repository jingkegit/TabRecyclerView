package com.jke.tabrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Describes
 * Created by 荆柯 on 2017/6/4.
 */

public class ColumnRecyclerAdapter extends RecyclerView.Adapter<ColumnRecyclerAdapter.ItemViewHolder>
        implements OnItemMoveListener{

    private Context mContext;
    private boolean isDelete;
    private List<String> mdatas;
    private onItemClickListener listener;

    public ColumnRecyclerAdapter(List<String> mdatas,Context mContent,boolean isDelete) {
        this.mdatas = mdatas;
        this.mContext = mContent;
        this.isDelete = isDelete;
    }

    @Override
    public ColumnRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_column_layout,parent,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ColumnRecyclerAdapter.ItemViewHolder holder, final int position) {
        holder.name_tv.setText(mdatas.get(position));
        holder.delete_img.setVisibility(isDelete? View.VISIBLE:View.GONE);
        if(mdatas.get(position).equals("要闻")){
            holder.delete_img.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mdatas.get(position).equals("要闻")){//要闻不能删
                    return;
                }
                if(listener!=null){
                    if (isDelete){
                        listener.remove(position);
                    }else {
                        listener.add(position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mdatas, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

        public View itemView;
        public TextView name_tv;
        public ImageView delete_img;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            name_tv = (TextView) itemView.findViewById(R.id.column_name_tv);
            delete_img = (ImageView) itemView.findViewById(R.id.delete_icon);

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(mContext.getResources().getColor(R.color.transparent_ffffff15));
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(mContext.getResources().getColor(R.color.transparent_ffffff));
        }
    }

    public void addData(String title,int position){
        mdatas.add(position,title);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,mdatas.size()-position);
    }

    public void removeData(int position){
        mdatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mdatas.size()-position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener  = listener;
    }

    public interface onItemClickListener{
        void remove(int pos);
        void add(int pos);
    }

}
