package come.basim.step_by_step.data.di

import come.basim.step_by_step.data.dataSource.PatientDataSource
import come.basim.step_by_step.data.repo.PatientRepositoryImpl
import come.basim.step_by_step.domin.repository.PatientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideRepository(patientDataSource: PatientDataSource):PatientRepository{
        return  PatientRepositoryImpl(patientDataSource)
    }
}