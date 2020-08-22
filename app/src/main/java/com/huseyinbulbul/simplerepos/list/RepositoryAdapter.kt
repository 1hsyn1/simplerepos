package com.huseyinbulbul.simplerepos.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huseyinbulbul.simplerepos.R
import com.huseyinbulbul.simplerepos.common.inflate
import com.huseyinbulbul.simplerepos.common.model.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(private val list: List<Repository>,
                        private val repoSelectedListener: ((Repository) -> Unit)? = null)
    : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(parent.inflate(R.layout.item_repository))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        list[position]?.let {
            holder.itemView.apply {
                tv_name.text = it.name

                tv_name.setOnClickListener {_ ->
                    repoSelectedListener?.let { listener ->
                        listener(it)
                    }
                }
            }
        }
    }

    class RepositoryViewHolder(v: View): RecyclerView.ViewHolder(v)
}