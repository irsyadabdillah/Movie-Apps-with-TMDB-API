package com.irzstudio.movieapps.di

import android.content.Context
import androidx.room.Room
import com.irzstudio.movieapps.data.Local.AppDatabase
import com.irzstudio.movieapps.data.Network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object appModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor;
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providerApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    fun providerRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "favoritedata.db")
            .allowMainThreadQueries().build()


    @Provides
    fun providerFavoriteDao(appDatabase: AppDatabase) = appDatabase.favoriteDao()

//    @Singleton
//    @Provides
//    fun providerRepository(apiService: ApiService, appDatabase: AppDatabase, ) = Repository(apiService, appDatabase)

}