package com.huseyinbulbul.simplerepos.common

import com.huseyinbulbul.simplerepos.common.model.Repository
import com.huseyinbulbul.simplerepos.common.network.ApiConnecter
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DataManager {
    companion object{
        private var instance: DataManager? = null

        fun getInstance(): DataManager{
            if(instance == null){
                instance = DataManager()
            }

            return instance as DataManager
        }
    }
    private val map = HashMap<String, List<Repository>>()
    private var selectedRepository: Repository? = null

    fun getListFor(user: String, success: ((List<Repository>) -> Unit), error: ((messeage: String) -> Unit)){
        map[user]?.let {
            success(it)
        } ?: run {
            ApiConnecter
                .getApi()
                .getRepos(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Repository>> {
                    override fun onComplete() {}

                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(t: List<Repository>) {
                        map[user] = t
                        success(t)
                    }

                    override fun onError(e: Throwable) {
                        error(e.localizedMessage)
                    }

                })
        }
    }

    fun selectRepository(repository: Repository){
        selectedRepository = repository
    }

    fun deselectRepository(){
        selectedRepository = null
    }

    fun getSelectedRepository(): Repository?{
        return selectedRepository
    }
}