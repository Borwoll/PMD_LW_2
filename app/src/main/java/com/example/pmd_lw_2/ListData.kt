package com.example.pmd_lw_2

import android.graphics.Bitmap

class ListData(private var _slug: String?, private var _description: String?, private var _imageURL: String, private var _content: String? = null) {
    fun getSlug(): String? {
        return _slug
    }

    fun setSlug(_slug: String?) {
        this._slug = _slug
    }

    fun getDescription(): String? {
        return _description
    }

    fun setDescription(_description: String?) {
        this._description = _description
    }

    fun getImageURL(): String {
        return _imageURL
    }

    fun setImageURL(imageURL: String) {
        this._imageURL = imageURL
    }

    fun getContent(): String? {
        return _content
    }

    fun setContent(_content: String?) {
        this._content = _content
    }
}