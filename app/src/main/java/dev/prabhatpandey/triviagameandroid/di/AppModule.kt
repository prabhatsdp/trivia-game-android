package dev.prabhatpandey.triviagameandroid.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.prabhatpandey.triviagameandroid.data.TriviaApi
import dev.prabhatpandey.triviagameandroid.data.repositories.TriviaRepository
import dev.prabhatpandey.triviagameandroid.data.repositories.TriviaRepositoryImpl
import dev.prabhatpandey.triviagameandroid.utils.BASE_URL
import dev.prabhatpandey.triviagameandroid.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 7:31 AM
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }

    @Provides
    @Singleton
    fun provideDefaultTriviaRepository(
        triviaApi: TriviaApi
    ) = TriviaRepositoryImpl(triviaApi) as TriviaRepository


    @Provides
    @Singleton
    fun provideTriviaApi(): TriviaApi {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(TriviaApi::class.java)

    }
}