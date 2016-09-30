package com.android.core.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    private SetListener setListener;
    private GetPos getPos;

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public CommonAdapter(Context context, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mItemLayoutId = itemLayoutId;
        this.mDatas = new ArrayList<>();
    }

    /**
     * 刷新数据
     * @param datas
     */
    public void refreshDatas(List<T> datas){
        mDatas.clear();
        if(datas != null && !datas.isEmpty()){
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearDatas(){
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param datas
     */
    public void addMoreDatas(List<T> datas){
        if(datas != null && !datas.isEmpty()){
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }


    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        if (getPos != null) {
            getPos.getPos(position);
        }
        convert(viewHolder, getItem(position),position);
        if (setListener != null) {
            if (position == mDatas.size() - 1) {
                setListener.onLastPosition(viewHolder);
            } else {
                setListener.onOtherPosition(viewHolder);
            }
        }
        return viewHolder.getConvertView();
    }

    public void setListener(SetListener setListener) {
        this.setListener = setListener;
    }

    public void convert(ViewHolder helper, T item,int position){
        convert(helper,item);
    }
    public abstract void convert(ViewHolder helper, T item);

    public void addAll(Collection<? extends T> list) {
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void setGetPosListener(GetPos getPos) {
        this.getPos = getPos;
    }


    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

    public List<T> getList(){
        return mDatas;
    }
    public interface SetListener {
        void onLastPosition(ViewHolder viewHolder);

        void onOtherPosition(ViewHolder viewHolder);
    }

    public interface GetPos {
        void getPos(int pos);
    }

}