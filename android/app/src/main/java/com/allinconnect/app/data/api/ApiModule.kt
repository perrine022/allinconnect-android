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
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideOffersApi(retrofit: Retrofit): OffersApi {
        return retrofit.create(OffersApi::class.java)
    }
    
    @Provides
    @Singleton
    fun providePartnersApi(retrofit: Retrofit): PartnersApi {
        return retrofit.create(PartnersApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideBillingApi(retrofit: Retrofit): BillingApi {
        return retrofit.create(BillingApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideSubscriptionsApi(retrofit: Retrofit): SubscriptionsApi {
        return retrofit.create(SubscriptionsApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideWalletApi(retrofit: Retrofit): WalletApi {
        return retrofit.create(WalletApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideSavingsApi(retrofit: Retrofit): SavingsApi {
        return retrofit.create(SavingsApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideFavoritesApi(retrofit: Retrofit): FavoritesApi {
        return retrofit.create(FavoritesApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideRatingsApi(retrofit: Retrofit): RatingsApi {
        return retrofit.create(RatingsApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideNotificationPreferencesApi(retrofit: Retrofit): NotificationPreferencesApi {
        return retrofit.create(NotificationPreferencesApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideInvoicesApi(retrofit: Retrofit): InvoicesApi {
        return retrofit.create(InvoicesApi::class.java)
    }
    
    @Provides
    @Singleton
    fun providePaymentApi(retrofit: Retrofit): PaymentApi {
        return retrofit.create(PaymentApi::class.java)
    }
    
    @Provides
    @Singleton
    fun providePushApi(retrofit: Retrofit): PushApi {
        return retrofit.create(PushApi::class.java)
    }
}
