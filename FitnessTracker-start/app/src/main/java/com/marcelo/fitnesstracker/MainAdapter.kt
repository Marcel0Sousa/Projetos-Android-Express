package com.marcelo.fitnesstracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    private val mainItens: List<MainItem>
): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    // bind layout xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val itemCurrent = mainItens[position]
        holder.bind(itemCurrent)
    }

    override fun getItemCount(): Int {
        return mainItens.size
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: MainItem) {

            val img: ImageView = itemView.findViewById(R.id.item_img_icon)
            val name: TextView = itemView.findViewById(R.id.item_text_name)
            val container: LinearLayout = itemView as LinearLayout

            img.setImageResource(item.drawableId)
            name.setText(item.textString)
            container.setBackgroundColor(item.color)
        }

    }
}