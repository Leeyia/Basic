package com.dintech.api.widget;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseExpandableAdapter<G extends GroupItem<C>, C> extends BaseExpandableListAdapter {

    private Context context;
    private int groupLayoutRes;
    private int childLayoutRes;
    private List<G> groupItems = new ArrayList<>();

    public abstract void transformGroup(ViewHolder holder, G item, boolean isExpanded, int groupPosition);

    public abstract void transformChild(ViewHolder holder, C item, boolean isLastChild, int groupPosition, int childPosition);

    public BaseExpandableAdapter(Context context, @LayoutRes int groupLayoutRes, @LayoutRes int childLayoutRes) {
        this.context = context;
        this.groupLayoutRes = groupLayoutRes;
        this.childLayoutRes = childLayoutRes;
    }

    public void syncGroupItems(List<G> groupItems) {
        syncGroupItems(groupItems, false);
    }

    public void syncGroupItems(List<G> groupItems, boolean isMore) {
        if (!isMore) this.groupItems.clear();
        this.groupItems.addAll(groupItems);
        notifyDataSetChanged();
    }

    public List<G> getGroupItems() {
        return this.groupItems;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getGroupCount() {
        return groupItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupItems.get(groupPosition).childItems().size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return groupItems.get(groupPosition);
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return groupItems.get(groupPosition).childItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(groupLayoutRes, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        transformGroup(viewHolder, getGroup(groupPosition), isExpanded, groupPosition);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(childLayoutRes, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        transformChild(viewHolder, getChild(groupPosition, childPosition), isLastChild, groupPosition, childPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public static class ViewHolder {
        private View view;

        private ViewHolder(View view) {
            this.view = view;
        }

        public View getView() {
            return view;
        }

        public <V extends View> V findViewById(@IdRes int idRes) {
            return view.findViewById(idRes);
        }

        public void setText(@IdRes int idRes, String text) {
            TextView textView = findViewById(idRes);
            textView.setText(text);
        }
    }
}
