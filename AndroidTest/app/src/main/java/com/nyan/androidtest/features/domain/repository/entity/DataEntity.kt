package com.nyan.androidtest.features.domain.repository.entity

import com.nyan.androidtest.features.viewmodels.Data

class DataEntity(val id: Int, val title: String, val subtitle: String, val content: String) {
    fun toData() = Data(id, title, subtitle, content)
}