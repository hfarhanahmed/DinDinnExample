package com.sevenpeakssoftware.farhan.di

import com.sevenpeakssoftware.farhan.TestDataRepository
import com.sevenpeakssoftware.farhan.data.DataRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
abstract class TestDataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: TestDataRepository): DataRepositorySource
}
