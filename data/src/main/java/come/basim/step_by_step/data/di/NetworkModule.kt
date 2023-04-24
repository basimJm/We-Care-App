package come.basim.step_by_step.data.di

import come.basim.step_by_step.data.dataSource.PatientDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val baseUrl = "https://patients-app-api.herokuapp.com/"

    @Provides
    @Singleton
    fun porvideretrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://patients-app-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideDataSource(retrofit: Retrofit): PatientDataSource {
        return retrofit.create(PatientDataSource::class.java)
    }
}