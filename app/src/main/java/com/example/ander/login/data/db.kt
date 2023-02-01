package com.example.ander.login.data

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object db{

    @Singleton
    @Provides
    fun provideFirestoreInstance() = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideGiftList(
        firestore: FirebaseFirestore
    ) = firestore.collection("user")

}