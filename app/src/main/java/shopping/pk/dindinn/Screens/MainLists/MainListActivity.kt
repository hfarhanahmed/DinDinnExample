package shopping.pk.dindinn.Screens.MainLists

import MainListsPagerAdapter
import SliderAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import shopping.pk.dindinn.Events.ItemAddedToCartEvent
import shopping.pk.dindinn.Events.ItemRemovedFromCartEvent
import shopping.pk.dindinn.Models.FoodItem
import shopping.pk.dindinn.R
import shopping.pk.dindinn.Screens.Checkout.CheckoutActivity


class MainListActivity : AppCompatActivity() {

    private val cartItems = arrayListOf<FoodItem>()

    override fun onDestroy() {
        RxBus.get().unregister(this)
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().register(this)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.checkoutFab).setOnClickListener { view ->
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra(CheckoutActivity.EXTRA_CHECKOUT_ITEMS,cartItems)
            startActivity(intent)
        }
        nestedScroll.isFillViewport = true
        val sliderAdapter = SliderAdapter(this)
        sliderAdapter.addItem(R.drawable.slide1)
        sliderAdapter.addItem(R.drawable.slide2)
        sliderAdapter.addItem(R.drawable.slide3)
        imageSlider.setSliderAdapter(sliderAdapter)

        foodListTabs.setupWithViewPager(foodListsViewpager)
        foodListsViewpager.adapter = MainListsPagerAdapter(supportFragmentManager)

        app_bar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if ((toolbar_layout.height + verticalOffset) < (2 * ViewCompat.getMinimumHeight(toolbar_layout))){
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }else{
                window.decorView.systemUiVisibility = 0
            }
        })

    }

    @Subscribe
    fun addItemToCart(item: ItemAddedToCartEvent) {
        cartItems.add(item.item)
        badgeCount.text= cartItems.size.toString()
        if (cartItems.size>0){
            badgeCount.visibility = View.VISIBLE
        }else{
            badgeCount.visibility = View.GONE
        }
    }

    @Subscribe
    fun removeItemFromCart(item: ItemRemovedFromCartEvent) {
        cartItems.remove(item.item)
        badgeCount.text= cartItems.size.toString()
        if (cartItems.size>0){
            badgeCount.visibility = View.VISIBLE
        }else{
            badgeCount.visibility = View.GONE
        }
    }
}