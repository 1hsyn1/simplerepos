package com.huseyinbulbul.simplerepos.list.detail

import com.huseyinbulbul.simplerepos.common.IBaseView
import com.huseyinbulbul.simplerepos.common.model.Repository

interface IDetailView: IBaseView {
    fun showRepo(repo: Repository)
}