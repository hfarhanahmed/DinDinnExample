package shopping.pk.dindinn.Network.dtos

import shopping.pk.dindinn.Models.ExampleModel
import shopping.pk.dindinn.Models.FoodItem

data class FoodlistResponse(val data: List<FoodItem>, val page: Int, val totalPages:Int)