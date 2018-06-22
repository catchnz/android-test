package com.nyan.androidtest.features.models

import com.nyan.androidtest.core.extension.empty

data class Data(val id: Int, val title: String, val subTitle: String, val content: String) {
    companion object {
        fun empty() = Data(0, String.empty(), String.empty(), String.empty())
    }
}