import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import shopping.pk.dindinn.Screens.MainLists.FoodLists.FoodListFragment

class MainListsPagerAdapter (fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> FoodListFragment.newInstance("Pizza")
            1 -> FoodListFragment.newInstance("Sushi")
            2 -> FoodListFragment.newInstance("Drinks")
            else -> FoodListFragment.newInstance("Pizza")
        }
    }

    // 3
    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Pizza"
            1 -> "Sushi"
            2 -> "Drinks"
            else -> "Pizza"
        }
    }
}