package com.dev.clevertonsantos.mybeats.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.data.model.Headphone
import com.dev.clevertonsantos.mybeats.extensions.load
import kotlinx.android.synthetic.main.item.view.*
import java.text.DecimalFormat

class HomeAdapter(
        private val onItemClickerListener: ((headphone: Headphone) -> Unit)
    ) : RecyclerView.Adapter<HomeAdapter.HeadphonesViewHolder>() {

    private val headphones = mutableListOf<Headphone>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadphonesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
            parent, false)
        return HeadphonesViewHolder(itemView, onItemClickerListener)
    }

    override fun onBindViewHolder(holder: HeadphonesViewHolder, position: Int) {
        holder.bindView(headphones[position])
    }

    fun addItens(itens: List<Headphone>) {
        headphones.clear()
        headphones.addAll(itens)
    }

    override fun getItemCount() = headphones.count()

    class HeadphonesViewHolder(
        itemView: View,
        private val onItemClickerListener: ((headphone: Headphone) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindView(headphone: Headphone) {
            itemView.itemDescription.text = headphone.name
            itemView.itemNote.text = headphone.rating.toString()
            itemView.itemReviews.text = "${headphone.total_reviews} Reviews"
            val dec = DecimalFormat("#,###.00")
            itemView.itemValue.text = "R$ ${dec.format(headphone.value)}"
            itemView.itemImageView.load(headphone.image)

            itemView.setOnClickListener {
                onItemClickerListener.invoke(headphone)
            }
        }
    }
}