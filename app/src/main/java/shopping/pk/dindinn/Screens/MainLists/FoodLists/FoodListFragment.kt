package shopping.pk.dindinn.Screens.MainLists.FoodLists

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.mvrx.*
import com.hwangjr.rxbus.RxBus
import kotlinx.android.synthetic.main.fragment_food_item_list_list.*
import shopping.pk.dindinn.Events.ItemAddedToCartEvent
import shopping.pk.dindinn.MockData.MockData
import shopping.pk.dindinn.R

/**
 * A fragment representing a list of Items.
 */
class FoodListFragment : Fragment(),MavericksView {

    private val viewModel : FoodListViewModel by fragmentViewModel()
    private var categoryId = ""

    override fun onDestroy() {
        RxBus.get().unregister(this)
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().register(this)
        arguments?.let {
            categoryId = it.getString(ARG_CATEGORY_ID).toString()
            viewModel.categoryId = categoryId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_item_list_list, container, false)
        return view
    }

    override fun invalidate() {
        withState(viewModel){
            when(it.request){
                is Loading -> {
                    // Set the adapter
                    foodListRV.layoutManager = LinearLayoutManager(this.context)
                    foodListRV.adapter = FoodItemRecyclerViewAdapter(MockData.getMockFoodList(categoryId), { item ->
                        RxBus.get().post(
                            ItemAddedToCartEvent(
                                item
                            )
                        )
                    })
                }
                is Success -> {
                    foodListRV.adapter?.notifyDataSetChanged()
                }
                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        "Failed to load all food list",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                }
            }
        }
    }

    companion object {
        // TODO: Customize parameter argument names
        const val ARG_CATEGORY_ID = "category-id"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(categoryId: String) =
            FoodListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY_ID, categoryId)
                }
            }
    }
}