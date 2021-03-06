package com.huseyinbulbul.simplerepos.list.detail

import com.huseyinbulbul.simplerepos.R
import com.huseyinbulbul.simplerepos.common.DataManager
import com.huseyinbulbul.simplerepos.common.FavouriteManager

class DetailPresenter(private val view: IDetailView,
                      private val manager: DataManager,
                      private val favouriteManager: FavouriteManager) {

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

    fun favouriteSelected(){
        manager.getSelectedRepository()?.id?.toString()?.let {
            if(favouriteManager.isFavourite(it)){
                favouriteManager.removeFavourite(it)
            }else {
                favouriteManager.addFavourite(it)
            }
            manager.getSelectedRepository()?.let {repo ->
                view.showRepo(repo)
            }
        }
    }
}