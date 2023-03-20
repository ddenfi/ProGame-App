package com.ddenfi.capstonecompose.di

import com.ddenfi.capstonecompose.data.repository.GameRepository
import com.ddenfi.capstonecompose.domain.repository.IGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(gameRepository: GameRepository): IGameRepository
}