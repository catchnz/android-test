package com.nyan.androidtest.features.models

class DataEntity(val id: Int, val title: String, val subtitle: String, val content: String) {
    fun toData() = Data(id, title, subtitle, content)
}