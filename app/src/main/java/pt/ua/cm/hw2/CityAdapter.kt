package pt.ua.cm.hw2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pt.ua.cm.hw2.data.City
import pt.ua.cm.hw2.databinding.CityItemBinding
import pt.ua.cm.hw2.databinding.FragmentCityListBinding

class CityAdapter(private val onItemClicked: (City) -> Unit) :
    ListAdapter<City, CityAdapter.CityViewHolder>(DiffCallback) {

    private lateinit var context: Context

    class CityViewHolder(private var binding: CityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City, context: Context) {
            binding.cityText.text = context.getString(city.nameResourceId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        context = parent.context
        return CityViewHolder(
            CityItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current, context)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
                return (oldItem.nameResourceId == newItem.nameResourceId )
            }

            override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem == newItem
            }
        }
    }
}