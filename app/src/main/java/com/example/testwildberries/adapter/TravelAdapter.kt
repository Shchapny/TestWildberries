package com.example.testwildberries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testwildberries.R
import com.example.testwildberries.databinding.CardItemTravelBinding
import com.example.testwildberries.model.Travel
import com.example.testwildberries.util.dateFormatList
import com.example.testwildberries.util.differenceBetweenDates

interface Listener {
    fun onClick(travel: Travel)
    fun onLike(travel: Travel)
}

class TravelAdapter(private val tours: List<Travel>, private val listener: Listener) :
    ListAdapter<Travel, ToursViewHolder>(TravelDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToursViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardItemTravelBinding.inflate(layoutInflater, parent, false)
        return ToursViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ToursViewHolder, position: Int) {
        holder.bind(tours[position])
    }

    override fun getItemCount() = tours.size
}

class ToursViewHolder(private val binding: CardItemTravelBinding, private val listener: Listener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(travel: Travel) {
        binding.apply {
            textPrice.text = root.context.getString(R.string.price, travel.price.toString())
            textRoute.text = root.context.getString(
                R.string.route,
                travel.startCity,
                travel.endCity
            )
            textStartAndFinishDays.text = root.context.getString(
                R.string.days,
                travel.startDate.dateFormatList(),
                travel.endDate.dateFormatList()
            )
            textChronodays.text = root.context.getString(
                R.string.chronodays,
                differenceBetweenDates(travel.startDate, travel.endDate)
            )
            buttonLike.isChecked = travel.liked
            buttonLike.setOnClickListener {
                listener.onLike(travel)
                travel.liked != travel.liked
            }
        }
        itemView.setOnClickListener {
            listener.onClick(travel)
        }
    }
}

object TravelDiffUtil : DiffUtil.ItemCallback<Travel>() {

    override fun areItemsTheSame(oldItem: Travel, newItem: Travel): Boolean {
        return oldItem.searchToken == newItem.searchToken
    }

    override fun areContentsTheSame(oldItem: Travel, newItem: Travel): Boolean {
        return oldItem == newItem
    }
}