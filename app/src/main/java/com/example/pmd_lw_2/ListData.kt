package com.example.pmd_lw_2

class ListData(private var _slug: String?, private var _description: String?, private var _imageID: Int, private var _content: String? = null) {
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

    fun getImageId(): Int {
        return _imageID
    }

    fun setImageId(_imageID: Int) {
        this._imageID = _imageID
    }

    fun getContent(): String? {
        return _content
    }

    fun setContent(_content: String?) {
        this._content = _content
    }
}