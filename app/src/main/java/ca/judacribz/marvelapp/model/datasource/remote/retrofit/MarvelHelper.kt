package ca.judacribz.marvelapp.model.datasource.remote.retrofit

import ca.judacribz.marvelapp.model.constants.Url.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarvelHelper {
    private val logging = HttpLoggingInterceptor()
    private val client: OkHttpClient by lazy {
        logging.level = Level.BASIC

        OkHttpClient()
            .newBuilder()
            .addInterceptor(logging)
            .build()
    }


    private val retrofitInstance: Retrofit
        get() =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

    val observeMarvelService: ObservableMarvelService
        get() = retrofitInstance.create(ObservableMarvelService::class.java)
}