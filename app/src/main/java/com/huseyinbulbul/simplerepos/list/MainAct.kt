package com.huseyinbulbul.simplerepos.list

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.huseyinbulbul.simplerepos.R
import com.huseyinbulbul.simplerepos.common.BaseAct
import com.huseyinbulbul.simplerepos.common.DataManager
import com.huseyinbulbul.simplerepos.common.model.Repository
import com.huseyinbulbul.simplerepos.common.network.ApiConnecter
import kotlinx.android.synthetic.main.activity_main.*

class MainAct : BaseAct(), IMainView {
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, DataManager.getInstance())

        rv_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        bt_submit.setOnClickListener {
            presenter.userSubmitted(et_user.text.toString())
        }

        presenter.viewReady()
    }

    override fun onStart() {
        super.onStart()
        if (!et_user.text.toString().trim().isNullOrEmpty()) {
            presenter.userSubmitted(et_user.text.toString())
        }
    }

    override fun showData(repos: List<Repository>) {
        rv_list.adapter = RepositoryAdapter(repos){
            presenter.repoSelected(it)
        }
    }
}
