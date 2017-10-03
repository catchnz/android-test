package com.payne.isaac.jsonlist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.payne.isaac.jsonlist.R;
import com.payne.isaac.jsonlist.model.DataModel;

import java.util.ArrayList;

/**
 * Created by isaac on 2/10/17.
 */

public class DataListAdapter extends ArrayAdapter<DataModel> {

    private Context mContext;
    private ArrayList<DataModel> mDataModels;

    public DataListAdapter(@NonNull Context context, @NonNull ArrayList<DataModel> dataModels) {
        super(context, R.layout.adapter_data_list, dataModels);
        mContext = context;
        mDataModels = dataModels;
    }

    @NonNull
    @Override
    public View getView(final int position, View view, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_data_list, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_subtitle = (TextView) view.findViewById(R.id.tv_subtitle);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_title.setText(mDataModels.get(position).getTitle());
        viewHolder.tv_subtitle.setText(mDataModels.get(position).getSubtitle());

        return view;
    }

    private class ViewHolder {
        TextView tv_title;
        TextView tv_subtitle;
    }
}
