package com.nyan.androidtest.features.ui.data_details

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nyan.androidtest.R
import com.nyan.androidtest.core.platform.BaseFragment
import com.nyan.androidtest.features.ui.DataView
import com.nyan.androidtest.databinding.FragmentDetailBinding

/**
 * A placeholder fragment containing a simple view.
 */
class DetailFragment : BaseFragment() {
    override fun layoutId() = R.layout.fragment_detail

    companion object {
        private const val PARAM_DATA = "PARAM_DATA"
        fun showData(dataView: DataView): DetailFragment {
            val detailFragment = DetailFragment()
            val arg = Bundle()
            arg.putParcelable(PARAM_DATA, dataView)
            detailFragment.arguments = arg
            return detailFragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        binding.dataView = arguments?.getParcelable(PARAM_DATA)
        return binding.root

    }


}
