package shopping.pk.dindinn.Screens.MainLists.FoodLists

import com.airbnb.mvrx.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject
import shopping.pk.dindinn.Models.ExampleModel
import shopping.pk.dindinn.Network.FoodlistService

data class FoodListState(
    /** We use this Async to keep track of the state of the current network request. */
    val request: Async<List<ExampleModel>> = Uninitialized
) : MavericksState

class FoodListViewModel(initialState: FoodListState,
                         private val foodListService: FoodlistService)
    : MavericksViewModel<FoodListState>(initialState){
    init {
        fetchNextPage()
    }

    var categoryId = ""

    fun fetchNextPage() = withState { state ->
        if (state.request is Loading) return@withState

        suspend {
            foodListService.exampleList()
        }.execute(Dispatchers.IO) { it ->
            copy(request = it)
        }
    }

    /**
     * If you implement MvRxViewModelFactory in your companion object, MvRx will use that to create
     * your ViewModel. You can use this to achieve constructor dependency injection with Mavericks.
     *
     * @see MavericksViewModelFactory
     */
    companion object : MavericksViewModelFactory<FoodListViewModel, FoodListState> {

        override fun create(viewModelContext: ViewModelContext, state: FoodListState): FoodListViewModel {
            val service: FoodlistService by viewModelContext.activity.inject()
            return FoodListViewModel(
                state,
                service
            )
        }
    }
}