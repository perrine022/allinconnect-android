package com.allinconnect.app.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
    
    @Provides
    @Singleton
    fun provideOffersApi(retrofit: Retrofit): OffersApi = retrofit.create(OffersApi::class.java)
    
    @Provides
    @Singleton
    fun providePartnersApi(retrofit: Retrofit): PartnersApi = retrofit.create(PartnersApi::class.java)
    
    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): ProfileApi = retrofit.create(ProfileApi::class.java)
    
    @Provides
    @Singleton
    fun provideBillingApi(retrofit: Retrofit): BillingApi = retrofit.create(BillingApi::class.java)
    
    @Provides
    @Singleton
    fun provideSubscriptionsApi(retrofit: Retrofit): SubscriptionsApi = retrofit.create(SubscriptionsApi::class.java)
    
    @Provides
    @Singleton
    fun provideWalletApi(retrofit: Retrofit): WalletApi = retrofit.create(WalletApi::class.java)
    
    @Provides
    @Singleton
    fun provideSavingsApi(retrofit: Retrofit): SavingsApi = retrofit.create(SavingsApi::class.java)
    
    @Provides
    @Singleton
    fun provideFavoritesApi(retrofit: Retrofit): FavoritesApi = retrofit.create(FavoritesApi::class.java)
    
    @Provides
    @Singleton
    fun provideRatingsApi(retrofit: Retrofit): RatingsApi = retrofit.create(RatingsApi::class.java)
    
    @Provides
    @Singleton
    fun provideNotificationPreferencesApi(retrofit: Retrofit): NotificationPreferencesApi = retrofit.create(NotificationPreferencesApi::class.java)
    
    @Provides
    @Singleton
    fun provideInvoicesApi(retrofit: Retrofit): InvoicesApi = retrofit.create(InvoicesApi::class.java)
    
    @Provides
    @Singleton
    fun providePaymentApi(retrofit: Retrofit): PaymentApi = retrofit.create(PaymentApi::class.java)
    
    @Provides
    @Singleton
    fun providePushApi(retrofit: Retrofit): PushApi = retrofit.create(PushApi::class.java)
}

