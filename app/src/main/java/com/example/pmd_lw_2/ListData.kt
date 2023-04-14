package com.example.pmd_lw_2

import android.graphics.Bitmap

class ListData(private var _id: String, private var _description: String, private var _imageURL: String, private var _content: String? = null) {
    fun getID(): String {
        return _id
    }

    fun getDescription(): String {
        return _description
    }

    fun getImageURL(): String {
        return _imageURL
    }

    fun getContent(): String? {
        return _content
    }
}