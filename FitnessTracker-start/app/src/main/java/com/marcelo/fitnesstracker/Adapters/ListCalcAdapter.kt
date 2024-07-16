package com.marcelo.fitnesstracker.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.fitnesstracker.R
import com.marcelo.fitnesstracker.model.Calc
import java.util.Locale

class ListCalcAdapter(
    private val listCalc: List<Calc>
) :
    RecyclerView.Adapter<ListCalcAdapter.ListCalcViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCalcViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_calc_item, parent, false)
        return ListCalcViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListCalcViewHolder, position: Int) {
        val listCorrent = listCalc[position]
        holder.bind(listCorrent)
    }

    override fun getItemCount(): Int {
        return listCalc.size
    }

    inner class ListCalcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(listCorrent: Calc) {
            val result: TextView = itemView.findViewById(R.id.response_item)
            val type: TextView = itemView.findViewById(R.id.tv_type)

            type.text = listCorrent.type.uppercase(Locale.getDefault())
            result.text = listCorrent.response.toString()
        }

    }
}