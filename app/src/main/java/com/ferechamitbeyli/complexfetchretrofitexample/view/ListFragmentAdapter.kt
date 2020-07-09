package com.ferechamitbeyli.complexfetchretrofitexample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ferechamitbeyli.complexfetchretrofitexample.R
import com.ferechamitbeyli.complexfetchretrofitexample.util.getProgressDrawable
import com.ferechamitbeyli.complexfetchretrofitexample.util.loadImage
import com.ferechamitbeyli.complexfetchretrofitexample.model.Data2
import kotlinx.android.synthetic.main.item_data.view.*

class ListFragmentAdapter(val response: ArrayList<Data2>): RecyclerView.Adapter<ListFragmentAdapter.ListViewHolder>() {

    fun updateList(newResponse: List<Data2>) {
        response.clear()
        response.addAll(newResponse)
        notifyDataSetChanged()
    }

    class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var image: ImageView = view.thumbnail_iv
        var authorName: TextView = view.authorName_tv
        var title: TextView = view.title_tv
        var subredditName: TextView = view.subredditName_tv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false))
    }

    override fun getItemCount(): Int = response.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.authorName.text = response[position].authorName
        holder.title.text = response[position].title
        holder.subredditName.text = response[position].subredditName
        holder.image.loadImage(response[position].image, getProgressDrawable(holder.image.context))
    }
}