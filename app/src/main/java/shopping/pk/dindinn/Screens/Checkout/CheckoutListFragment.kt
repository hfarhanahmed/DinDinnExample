package shopping.pk.dindinn.Screens.Checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hwangjr.rxbus.RxBus
import kotlinx.android.synthetic.main.fragment_checkout.*
import shopping.pk.dindinn.Events.ItemRemovedFromCartEvent
import shopping.pk.dindinn.Models.FoodItem
import shopping.pk.dindinn.R

/**
 * A placeholder fragment containing a simple view.
 */
class CheckoutListFragment : Fragment() {

    override fun onDestroy() {
        RxBus.get().unregister(this)
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkoutListRV.layoutManager = LinearLayoutManager(context)
        val itemsList = arguments?.getSerializable(
            ARG_ITEMS_LIST) as ArrayList<FoodItem>
        checkoutListRV.adapter = CheckoutItemRecyclerViewAdapter(
            itemsList
        ) { item ->
            RxBus.get().post(ItemRemovedFromCartEvent(item))
            itemsList.remove(item)
            checkoutListRV.adapter?.notifyDataSetChanged()
            totalPrice.text = "${getTotalPrice(itemsList)} usd"
        }
        totalPrice.text = "${getTotalPrice(itemsList)} usd"
    }

    private fun getTotalPrice(itemsList: java.util.ArrayList<FoodItem>): Int {
        var totalPrice = 0
        itemsList.forEach {
            totalPrice += it.price
        }
        return totalPrice
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_ITEMS_LIST = "items_list"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(itemsList: ArrayList<FoodItem>): CheckoutListFragment {
            return CheckoutListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ITEMS_LIST, itemsList)
                }
            }
        }
    }
}