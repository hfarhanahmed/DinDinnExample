package com.sevenpeakssoftware.farhan

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.sevenpeakssoftware.farhan.data.dto.articles.Articles
import com.sevenpeakssoftware.farhan.data.dto.articles.ArticlesItem
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.sevenpeakssoftware.farhan.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.sevenpeakssoftware.farhan.data.remote.moshiFactories.MyStandardJsonAdapters
import java.io.InputStream
import java.lang.reflect.Type

/**
 * Created by FarhanAhmed
 */

object TestUtil {
    var dataStatus: DataStatus = DataStatus.Success
    var recipes: Articles = Articles(arrayListOf())
    fun initData(): Articles {
        val moshi = Moshi.Builder()
                .add(MyKotlinJsonAdapterFactory())
                .add(MyStandardJsonAdapters.FACTORY)
                .build()
        val type: Type = Types.newParameterizedType(List::class.java, ArticlesItem::class.java)
        val adapter: JsonAdapter<List<ArticlesItem>> = moshi.adapter(type)
        val jsonString = getJson("RecipesApiResponse.json")
        adapter.fromJson(jsonString)?.let {
            recipes = Articles(ArrayList(it))
            return recipes
        }
        return Articles(arrayListOf())
    }

    private fun getJson(path: String): String {
        val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val inputStream: InputStream = ctx.classLoader.getResourceAsStream(path)
        return inputStream.bufferedReader().use { it.readText() }
    }
}
