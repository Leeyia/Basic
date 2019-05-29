package com.dintech.api.samples;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.dintech.api.scorpius.ScorpiusView;
import com.dintech.api.widget.BaseExpandableAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PullToRefreshActivity extends AppCompatActivity {

    public static final int REFRESH_DELAY = 10000;

    private ScorpiusView mPullToRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);

//        Indicator indicator = new LineScalePulseOutIndicator(this);
//        ImageView viewById = findViewById(R.id.iv_animal);
//        viewById.setBackground(indicator);
//        indicator.start();

//        LoadView viewById = findViewById(R.id.load_view);
//        viewById.start();
//        viewById.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                viewById.stop();
//            }
//        }, 5000);
//        LoadView loadView = new LoadView(this);
//        loadView.start();
//
//        addContentView(loadView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Map<String, Integer> map;
        List<Map<String, Integer>> sampleList = new ArrayList<>();


        int[] colors = {
                R.color.saffron,
                R.color.eggplant,
                R.color.sienna};

        int[] tripNames = {
                R.string.trip_to_india,
                R.string.trip_to_italy,
                R.string.trip_to_indonesia};

        for (int i = 0; i < tripNames.length; i++) {
            map = new HashMap<>();
            map.put(SampleAdapter.KEY_NAME, tripNames[i]);
            map.put(SampleAdapter.KEY_COLOR, colors[i]);
            sampleList.add(map);
        }

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.list_view);
//        listView.setAdapter(new SampleAdapter(this, R.layout.list_item2, sampleList));

        mPullToRefreshView = (ScorpiusView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new ScorpiusView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });
        List<Metting> mettings = new ArrayList<>();
        mettings.add(new Metting());
//        Metting metting = new Metting();
//        mettings.add(metting);


        BaseExpandableAdapter<Metting, String> adapter = new BaseExpandableAdapter<Metting, String>(this, R.layout.list_item2, R.layout.list_item2) {
            @Override
            public void transformGroup(ViewHolder holder, Metting item, int groupPosition) {
                ((TextView) holder.findViewById(R.id.text_view_name)).setText(item.getName());
            }

            @Override
            public void transformChild(ViewHolder holder, String item, int groupPosition, int childPosition) {

            }
        };
        adapter.syncGroupItems(mettings);
        listView.setAdapter(adapter);
    }

    class SampleAdapter extends ArrayAdapter<Map<String, Integer>> {

        public static final String KEY_NAME = "name";
        public static final String KEY_COLOR = "color";

        private final LayoutInflater mInflater;
        private final List<Map<String, Integer>> mData;

        public SampleAdapter(Context context, int layoutResourceId, List<Map<String, Integer>> data) {
            super(context, layoutResourceId, data);
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_item2, parent, false);
                viewHolder.textViewName = (TextView) convertView.findViewById(R.id.text_view_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textViewName.setText(mData.get(position).get(KEY_NAME));
            convertView.setBackgroundResource(mData.get(position).get(KEY_COLOR));
            return convertView;
        }

        class ViewHolder {
            TextView textViewName;
        }
    }
}
