import br.com.bakery.simplewallpaperlib.data.remote.LogInterceptor
import br.com.cvaccari.moneytransfer.BuildConfig
import br.com.cvaccari.moneytransfer.data.remote.MockInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private var retrofit: Retrofit? = null

        fun getClient(): API {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(OkHttpClient.Builder()
                            .addInterceptor(MockInterceptor())
                            .build())
//                        LogInterceptor().getClient())
//                        OkHttpClient.Builder()
//                            .addInterceptor(MockInterceptor())
//                            .build()
//                    )
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!.create(API::class.java)
        }
    }
}