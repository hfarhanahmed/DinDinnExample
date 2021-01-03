package shopping.pk.dindinn.Screens.Checkout

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shopping.pk.dindinn.Models.FoodItem
import shopping.pk.dindinn.R


class CheckoutItemRecyclerViewAdapter(private val values: List<FoodItem>, val removeFromCart: (item: FoodItem) -> Unit) : RecyclerView.Adapter<CheckoutItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_checkout_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Glide.with(holder.itemView).load(item.imageUrl).into(holder.imageView)
        holder.name.text = item.name
        holder.price.text = "${item.price} usd"

        holder.removeItem.setOnClickListener {
            removeFromCart.invoke(item)
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.name)
        val price: TextView = view.findViewById(R.id.price)
        val removeItem: TextView = view.findViewById(R.id.removeItem)

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }
}