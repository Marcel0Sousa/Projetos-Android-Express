package com.marcelo.fitnesstracker.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.fitnesstracker.R
import com.marcelo.fitnesstracker.model.Calc
import java.text.SimpleDateFormat
import java.util.Locale

class ListCalcAdapter(
    private val listCalc: List<Calc>
) :
    RecyclerView.Adapter<ListCalcAdapter.ListCalcViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCalcViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_calc_item, parent, false)
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
        fun bind(item: Calc) {
            val type: TextView = itemView.findViewById(R.id.tv_type_item)
            val result: TextView = itemView.findViewById(R.id.tv_response_item)
            val date: TextView = itemView.findViewById(R.id.tv_date_item)

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
            date.text = simpleDateFormat.format(item.createdDate)

            type.text = item.type.uppercase(Locale.getDefault())
            result.text = formatNumber(item.response)

            //result.text = item.response.toString().substring(0, 5)
            /*if (item.type == "tmb") {
                result.text = item.response.toString().format("%1$.2f", item.response)
                return
            }*/

        }

        private fun formatNumber(number: Double): String {
            return String.format("%1$.2f", number)
        }

    }
}