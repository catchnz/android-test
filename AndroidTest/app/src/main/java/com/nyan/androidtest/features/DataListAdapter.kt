package com.nyan.androidtest.features

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.nyan.androidtest.R
import com.nyan.androidtest.core.extension.inflate
import com.nyan.androidtest.features.models.DataView
import kotlinx.android.synthetic.main.row_data.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class DataListAdapter
@Inject constructor() : RecyclerView.Adapter<DataListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.row_data))
    internal var collection: List<DataView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }
    internal var clickListener: (DataView) -> Unit = { _ -> }

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataView: DataView, clickListener: (DataView) -> Unit) {
            itemView.tvTitle.text = dataView.title
            itemView.tvSubTitle.text = dataView.subTitle

        }
    }
}