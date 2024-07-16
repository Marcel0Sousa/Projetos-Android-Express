package com.marcelo.fitnesstracker.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.fitnesstracker.R
import com.marcelo.fitnesstracker.model.MainItens

class MainAdapter(
    private val mainItens: List<MainItens>,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

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

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MainItens) {

            // bind componentes xml
            val img: ImageView = itemView.findViewById(R.id.item_img_icon)
            val name: TextView = itemView.findViewById(R.id.item_text_name)
            val container: ConstraintLayout = itemView.findViewById(R.id.item_container_imc)

            img.setImageResource(item.drawableId)
            name.setText(item.textString)
            container.setBackgroundResource(item.color)

            container.setOnClickListener {
                onItemClickListener.invoke(item.id)
            }
        }

    }
}