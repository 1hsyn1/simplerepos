package com.huseyinbulbul.simplerepos.list

import com.huseyinbulbul.simplerepos.R
import com.huseyinbulbul.simplerepos.common.DataManager
import com.huseyinbulbul.simplerepos.common.model.Repository
import com.huseyinbulbul.simplerepos.list.detail.DetailAct

class MainPresenter (private val view: IMainView, private val manager: DataManager){

    fun viewReady(){
        view.showLeftIcon(false)
        view.showRightIcon(false)
        view.setTitle(R.string.app_name)
    }

    fun userSubmitted(text: String){
        if(text.trim().isNullOrEmpty()){
            view.showMessage(R.string.invalid_username)
            return
        }

        view.showLoading()
        view.hideKeyboard()
        manager.getListFor(text,{
            view.hideLoading()
            view.showData(it)
        },{
            view.hideLoading()
            view.showMessage(it)
        })
    }

    fun repoSelected(repo: Repository){
        manager.selectRepository(repo)
        view.openActivity(DetailAct::class.java)
    }
}