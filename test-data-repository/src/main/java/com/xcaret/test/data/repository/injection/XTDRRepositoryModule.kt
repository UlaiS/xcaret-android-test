package com.xcaret.test.data.repository.injection

import com.xcaret.test.data.repository.local.XTDRLocalSessionDataSource
import com.xcaret.test.data.repository.repository.XTDRSessionRepositoryImpl
import com.xcaret.test.domain.repository.XTDSessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class XTDRRepositoryModule {
    @Provides
    fun provideSessionRepository(
        sessionDataSource: XTDRLocalSessionDataSource
    ): XTDSessionRepository = XTDRSessionRepositoryImpl(
        sessionDataSource
    )
}