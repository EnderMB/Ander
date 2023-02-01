package com.example.ander.ui.login.data

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Singleton
import dagger.Module

@Module
object db{

    @Singleton
    fun provideFirestoreInstance() = FirebaseFirestore.getInstance()

    @Singleton
    fun provideGiftList(
        firestore: FirebaseFirestore
    ) = firestore.collection("users")

}