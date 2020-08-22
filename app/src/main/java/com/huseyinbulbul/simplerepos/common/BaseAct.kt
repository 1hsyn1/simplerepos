package com.huseyinbulbul.simplerepos.common

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.huseyinbulbul.simplerepos.R
import kotlinx.android.synthetic.main.activity_base.*

open class BaseAct : AppCompatActivity(), IBaseView {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(R.layout.activity_base)
        val view = LayoutInflater.from(this).inflate(layoutResID, fl_content, false)

        iv_left_icon.setOnClickListener {
            onBackPressed()
        }

        fl_content.addView(view)
    }

    override fun hideActionBar() {
        apl_toolbar.visibility = View.GONE
    }

    override fun showLoading() {
        if (!isOnScreen())
            return

        rl_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        if (!isOnScreen())
            return

        rl_loading.visibility = View.GONE
    }

    override fun setTitle(title: String) {
        if (!isOnScreen())
            return

        tv_title.text = title
    }

    override fun setTitle(title: Int) {
        if (!isOnScreen())
            return

        setTitle(getString(title))
    }

    override fun showRightIcon(isShow: Boolean) {
        if (!isOnScreen())
            return

        iv_right_icon.visibility = if(isShow) View.VISIBLE else View.GONE
    }

    override fun showLeftIcon(isShow: Boolean) {
        if (!isOnScreen())
            return

        iv_left_icon.visibility = if(isShow) View.VISIBLE else View.GONE
    }

    override fun setRightIcon(bitmap: Bitmap) {
        if (!isOnScreen())
            return

        iv_right_icon.setImageBitmap(bitmap)
    }

    override fun setRightIcon(id: Int) {
        if (!isOnScreen())
            return

        iv_right_icon.setImageDrawable(resources.getDrawable(id))
    }

    override fun setLeftIcon(bitmap: Bitmap) {
        if (!isOnScreen())
            return

        iv_left_icon.setImageBitmap(bitmap)
    }

    override fun setLeftIcon(id: Int) {
        if (!isOnScreen())
            return

        iv_left_icon.setImageDrawable(resources.getDrawable(id))
    }

    override fun setOnRightIconClicked(listener: View.OnClickListener) {
        if (!isOnScreen())
            return

        iv_right_icon.setOnClickListener(listener)
    }

    override fun openActivity(cls: Class<*>) {
        if (!isOnScreen())
            return

        startActivity(Intent(this, cls))
    }

    override fun close() {
        if (!isOnScreen())
            return

        finish()
    }

    override fun showMessage(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok
            ) { dialog, which ->
                dialog?.let {
                    it.dismiss()
                }
            }
            .show()
    }

    override fun showMessage(message: Int) {
        AlertDialog.Builder(this)
            .setMessage(getString(message))
            .setPositiveButton(R.string.ok
            ) { dialog, which ->
                dialog?.let {
                    it.dismiss()
                }
            }
            .show()
    }

    override fun hideKeyboard() {
        if (!isOnScreen())
            return

        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun isOnScreen(): Boolean {
        return !isFinishing
    }
}
