package shopping.pk.dindinn.MockData

import shopping.pk.dindinn.Models.FoodItem

class MockData {

    companion object {

        val pizzas = listOf(
            FoodItem("pizza1", "Italian Pizza", "This is very delicious and cheesy pizza.", "https://www.qsrmagazine.com/sites/default/files/styles/story_page/public/phut_0.jpg", "Pizza", 574, "200 gram, 13 cm"),
            FoodItem("pizza2", "Cheese Pizza", "This pizza is for cheesy lovers.", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/delish-homemade-pizza-horizontal-1542312378.png", "Pizza", 799, "200 gram, 13 cm"),
            FoodItem("pizza3", "Veg Pizza", "The healthy delight for people who love themselves", "https://www.ruchiskitchen.com/wp-content/uploads/2016/02/veggie-pizza-2-1.jpg", "Pizza", 499, "200 gram, 13 cm"),
            FoodItem("pizza4", "Special Pizza", "The very special pizza to complement you taste buds.", "https://cafedelites.com/wp-content/uploads/2016/06/Easy-Portobello-Pizzas-8.jpg", "Pizza", 999, "250 gram, 13 cm")
        )

        val sushis = listOf(
            FoodItem("sushi1", "Sushi", "Salmon, rice, magical sauces", "https://www.nippon.com/en/ncommon/contents/japan-data/169591/169591.jpg", "Sushi", 574, "200 gram, 11 pieces"),
            FoodItem("sushi2", "Japanese Sushi", "Tuna, rice, magical sauces", "https://cdngeneral.rentcafe.com//dmslivecafe/UploadedImages/a31c359b-121e-4dc8-9149-d62791c3c8d2.jpg", "Sushi", 799, "200 gram, 9 pieces"),
            FoodItem("sushi3", "Grilled Sushi", "Red Sniper, Salmon, rice, magical sauces", "https://media.istockphoto.com/photos/all-you-can-eat-sushi-picture-id1053854126?k=6&m=1053854126&s=612x612&w=0&h=4H9Iz5i30CKoxU8kRKbPayV3FtQYtL19_iwPgCYLfzs=", "Sushi", 499, "250 gram, 13 pieces"),
            FoodItem("sushi4", "Special Sushi", "Red Sniper, Salmon, rice, magical sauces", "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/00/a0000370/img/basic/a0000370_main.jpg", "Sushi", 999, "250 gram, 13 pieces")
        )
        val drinks = listOf(
            FoodItem("drink1", "Uglu", "The refreshing lemon and lime mix", "https://static.independent.co.uk/s3fs-public/thumbnails/image/2016/09/30/12/ugly.jpg?width=982&height=726", "Drinks", 3, "250 ml"),
            FoodItem("drink2", "Coke", "The best selling soft drink in whole world", "https://www.coca-cola.co.uk/content/dam/one/gb/en/article-lead/coca-cola-zero-sugar-diet-coke-article-lead-desktop-1020x420.jpg", "Drinks", 5, "250 ml"),
            FoodItem("drink3", "Smoothy", "A fruity and healthy drink", "https://www.thoughtco.com/thmb/JTvtua956kgKeIv4F-VtVfpPf-c=/1271x1271/smart/filters:no_upscale()/juice-2594602_1920-61c1b762c9e4492d82d1f7374381af11.jpg", "Drinks", 4, "350 ml"),
            FoodItem("drink4", "Cocktail", "The cocktail of best fruits", "https://www.readitforward.com/wp-content/uploads/2017/11/10-Singapore-Sling_cropped.jpg", "Drinks", 7, "350 ml")
        )

        fun getMockFoodList(categoryId: String):List<FoodItem>{
            when(categoryId){
                "Pizza" ->
                    return pizzas
                "Sushi" ->
                    return sushis
                "Drinks" ->
                    return drinks
                else ->
                    return emptyList<FoodItem>()
            }
        }
    }
}