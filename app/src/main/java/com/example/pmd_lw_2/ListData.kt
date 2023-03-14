package com.example.pmd_lw_2

class ListData(private var _description: String?, private var _imageID: Int) {
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
}