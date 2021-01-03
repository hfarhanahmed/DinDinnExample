package shopping.pk.dindinn.Screens.Checkout

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_checkout.*
import shopping.pk.dindinn.Models.FoodItem
import shopping.pk.dindinn.R

class CheckoutActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CHECKOUT_ITEMS = "EXTRA_CHECKOUT_ITEMS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager,
                intent.getSerializableExtra(EXTRA_CHECKOUT_ITEMS) as ArrayList<FoodItem>
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.paymentFab)

        backIcon.setOnClickListener { onBackPressed() }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}