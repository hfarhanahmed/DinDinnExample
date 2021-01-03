package shopping.pk.dindinn.Screens.Checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hwangjr.rxbus.RxBus
import kotlinx.android.synthetic.main.fragment_checkout.*
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
        val root = inflater.inflate(R.layout.fragment_checkout, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkoutListRV.layoutManager = LinearLayoutManager(context)
        checkoutListRV.adapter = CheckoutItemRecyclerViewAdapter(emptyList(), {item ->

        })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): CheckoutListFragment {
            return CheckoutListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}