package shopping.pk.dindinn.Screens.MainLists.FoodLists

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


class FoodItemRecyclerViewAdapter(private val values: List<FoodItem>, val addToCart: (item: FoodItem) -> Unit) : RecyclerView.Adapter<FoodItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_food_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Glide.with(holder.itemView).load(item.imageUrl).fitCenter().into(holder.imageView)
        holder.name.text = item.name
        holder.contentView.text = item.description
        holder.weightSize.text = item.weightSize
        holder.addToCart.text =   "${item.price} usd"

        holder.addToCart.setOnTouchListener { v, event ->
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                holder.addToCart.text =   "added + 1"
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                holder.addToCart.text =   "${item.price} usd"
            } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                holder.addToCart.text =   "${item.price} usd"
            }
            false
        }

        holder.addToCart.setOnClickListener {
            addToCart.invoke(item)
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.name)
        val contentView: TextView = view.findViewById(R.id.content)
        val weightSize: TextView = view.findViewById(R.id.weightSize)
        val addToCart: Button = view.findViewById(R.id.addToCart)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}