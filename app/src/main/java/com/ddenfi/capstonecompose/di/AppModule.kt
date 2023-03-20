package com.ddenfi.capstonecompose.di

import com.ddenfi.capstonecompose.domain.use_case.GameInteractor
import com.ddenfi.capstonecompose.domain.use_case.GameUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun providesGameUseCase(gameInteractor: GameInteractor):GameUseCase
}