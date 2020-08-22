package com.huseyinbulbul.simplerepos.list.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.huseyinbulbul.simplerepos.R
import com.huseyinbulbul.simplerepos.common.BaseAct
import com.huseyinbulbul.simplerepos.common.DataManager
import com.huseyinbulbul.simplerepos.common.FavouriteManager
import com.huseyinbulbul.simplerepos.common.model.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailAct : BaseAct(), IDetailView {
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter = DetailPresenter(this, DataManager.getInstance(), FavouriteManager.getInstance())

        iv_right_icon.setOnClickListener {
            presenter.favouriteSelected()
        }

        presenter.viewReady()
    }

    override fun showRepo(repo: Repository) {
        repo.owner?.avatar_url?.let {
            Picasso.with(this)
                .load(it)
                .into(iv_owner)
        }

        repo.owner?.login?.let {
            tv_name.text = it
        }

        repo.owner?.type?.let {
            tv_type.text = it
        }

        repo.stargazers_count?.let {
            tv_star_count.text = "$it"
        }

        repo.open_issues_count?.let {
            tv_issue_count.text = "$it"
        }

        repo.id?.let {
            if(FavouriteManager.getInstance().isFavourite("$it")){
                iv_right_icon.setImageDrawable(getDrawable(R.drawable.ic_star_filled))
            }else {
                iv_right_icon.setImageDrawable(getDrawable(R.drawable.ic_star))
            }
        }
    }
}
