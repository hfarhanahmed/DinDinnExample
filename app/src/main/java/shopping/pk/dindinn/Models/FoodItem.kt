package shopping.pk.dindinn.Models

import java.io.Serializable

data class FoodItem (val id: String, val name: String,
                     val description: String, val imageUrl: String,
                     val categoryId:String, val price:Int, val weightSize:String
): Serializable

data class ExampleModel (val userId: Int, val id: Int, val title: String, val completed: Boolean)