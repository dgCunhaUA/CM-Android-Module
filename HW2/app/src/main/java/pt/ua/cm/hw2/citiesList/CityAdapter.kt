import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ua.cm.hw2.R
import pt.ua.cm.hw2.data.City

class CityAdapter(private val context: Context, private val dataset: List<City>)
    : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.city_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item, parent, false)

        return CityViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = dataset[position]

        holder.textView.text = context.resources.getString(city.stringResourceId)
    }

    override fun getItemCount() = dataset.size

}
