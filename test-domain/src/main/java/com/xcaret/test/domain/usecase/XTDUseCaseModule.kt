package com.xcaret.test.domain.usecase

import com.xcaret.test.domain.repository.XTDLoginRepository
import com.xcaret.test.domain.repository.XTDPokemonListRepository
import com.xcaret.test.domain.repository.XTDSessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class XTDUseCaseModule {

    @Provides
    fun provideXTDUseCaseConfiguration(): XTDUseCase.XTDConfiguration = XTDUseCase.XTDConfiguration(Dispatchers.IO)

    @Provides
    fun provideLoginUseCase(
        configuration: XTDUseCase.XTDConfiguration,
        loginRepository: XTDLoginRepository
    ): XTDLoginUseCase = XTDLoginUseCase(
        configuration,
        loginRepository
    )

    @Provides
    fun providePokemonUseCase(
        configuration: XTDUseCase.XTDConfiguration,
        xtdPokemonListRepository: XTDPokemonListRepository
    ): XTDPokemonListUseCase = XTDPokemonListUseCase(
        configuration,
        xtdPokemonListRepository
    )

    @Provides
    fun provideSessionUseCase(
        configuration: XTDUseCase.XTDConfiguration,
        xtdSessionRepository: XTDSessionRepository
    ): XTDSessionUseCase = XTDSessionUseCase(
        configuration,
        xtdSessionRepository
    )
}