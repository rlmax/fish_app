package com.itbooh.fishapp.ui.home

import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.itbooh.fishapp.R
import com.itbooh.fishapp.data.model.Fish
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class HomeListAdapter(private var items: MutableList<Fish> = arrayListOf<Fish>()) : RecyclerView.Adapter<HomeListAdapter.ViewHolder>()  {

    private var onItemClickListener: ItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvTitle)
        val image = itemView.findViewById<ImageView>(R.id.ivFish)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listrow_home_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var fish = items[position]
        Picasso.get()
            .load(fish.story_image_thumb)
            .placeholder(R.drawable.fish_pa)
            .into(holder.image, object : Callback {
                override fun onSuccess() {
                    Log.d("f", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("f", "error")
                }
            })
        holder.name?.text = fish.story_title
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position,fish)
        }
    }
    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int,items: Fish)
    }

    fun replaceItems(itemsReplace: MutableList<Fish>) {
        items = itemsReplace
        notifyDataSetChanged()
    }


}