
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import shopping.pk.dindinn.R


class SliderAdapter(context: Context) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private val context: Context
    private var mSliderItems: MutableList<Int> = ArrayList()
    fun renewItems(sliderItems: MutableList<Int>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: Int) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(
        viewHolder: SliderAdapterVH,
        position: Int
    ) {
        val sliderItem: Int = mSliderItems[position]
        Glide.with(viewHolder.itemView)
            .load(sliderItem)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    inner class SliderAdapterVH(itemView: View) :
        ViewHolder(itemView) {
        var item: View
        var imageViewBackground: ImageView
        var imageGifContainer: ImageView
        var textViewDescription: TextView

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container)
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider)
            this.item = itemView
        }
    }

    init {
        this.context = context
    }
}