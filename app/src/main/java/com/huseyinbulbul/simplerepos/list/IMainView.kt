package com.huseyinbulbul.simplerepos.list

import com.huseyinbulbul.simplerepos.common.IBaseView
import com.huseyinbulbul.simplerepos.common.model.Repository

interface IMainView: IBaseView {
    fun showData(repos: List<Repository>)
}