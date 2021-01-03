package shopping.pk.dindinn

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import shopping.pk.dindinn.Network.FoodlistService
import com.airbnb.mvrx.Mavericks

class DinDinnApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)

        startKoin {
            androidContext(this@DinDinnApplication)
            modules(foodListServiceModule)
        }
    }
}

private val foodListServiceModule = module {
    factory {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    factory {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(FoodlistService::class.java)
    }
}