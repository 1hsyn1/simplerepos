package com.huseyinbulbul.simplerepos.list.detail

import com.huseyinbulbul.simplerepos.R
import com.huseyinbulbul.simplerepos.common.DataManager

class DetailPresenter(private val view: IDetailView, private val manager: DataManager) {

    fun viewReady(){
        manager.getSelectedRepository()?.let {
            it.name?.let {name ->
                view.setTitle(name)
            }
            view.setLeftIcon(R.drawable.ic_back)
            view.setRightIcon(R.drawable.ic_star)
            view.showRepo(it)
        } ?: run {
            view.close()
        }
    }
}