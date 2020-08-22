package com.huseyinbulbul.simplerepos.common

import android.graphics.Bitmap
import android.view.View

interface IBaseView {
    fun hideActionBar()
    fun showLoading()
    fun hideLoading()
    fun setTitle(title: String)
    fun setTitle(title: Int)
    fun showRightIcon(isShow: Boolean)
    fun showLeftIcon(isShow: Boolean)
    fun setRightIcon(bitmap: Bitmap)
    fun setRightIcon(id: Int)
    fun setLeftIcon(bitmap: Bitmap)
    fun setLeftIcon(id: Int)
    fun setOnRightIconClicked(listener: View.OnClickListener)
    fun openActivity(cls: Class<*>)
    fun close()
    fun hideKeyboard()
    fun isOnScreen(): Boolean
}