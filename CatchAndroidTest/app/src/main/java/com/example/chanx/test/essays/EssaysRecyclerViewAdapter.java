package com.example.chanx.test.essays;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chanx.test.R;
import com.example.chanx.test.data.EssayItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link EssayItem} and makes a call to the
 * specified {@link OnListItemClickListener}.
 */
public class EssaysRecyclerViewAdapter extends RecyclerView.Adapter<EssaysRecyclerViewAdapter.ViewHolder> {

    private final List<EssayItem> mValues;
    private final OnListItemClickListener mListener;

    public EssaysRecyclerViewAdapter(List<EssayItem> items, OnListItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.essay_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mSubtitleView.setText(mValues.get(position).getSubtitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mSubtitleView;
        public EssayItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(android.R.id.text1);
            mSubtitleView = (TextView) view.findViewById(android.R.id.text2);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mSubtitleView.getText() + "'";
        }
    }

    public synchronized void replaceAll(List<EssayItem> essays) {
        mValues.clear();
        mValues.addAll(essays);
    }

    /**
     * Listener callback for list item clicks in RecyclerView
     */
    public interface OnListItemClickListener {
        void onClick(EssayItem item);
    }
}
