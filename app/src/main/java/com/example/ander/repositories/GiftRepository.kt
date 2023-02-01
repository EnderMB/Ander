package com.example.ander.repositories

import com.example.ander.gifts.Gift
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GiftRepository
@Inject
constructor(
    private val giftList: CollectionReference
) {
    fun addNewGift(gift: Gift) {
        try {
            giftList.document(gift.id).set(gift)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getGiftList(): Flow<Result<List<Gift>>> = flow{
        try{
            emit(Result.Loading<List<Gift>>())

            val giftList = giftList.get().await().map{ document ->
                document.toObject(Gift::class.java)
            }
            emit(Result.Sucess<List<Gift>>(data = giftList))
        }catch (e: Exception){
            emit(Result.Error<List<Gift>>(message = e.localizedMessage ?: "Error unknown"))
        }
    }

    fun getGiftId(giftId: String): Flow<Result<Gift>> = flow{
        try {
            emit(Result.Loading<Gift>())

            val gift = giftList
                .whereGreaterThanOrEqualTo("id", giftId)
                .get()
                .await()
                .toObjects(Gift::class.java)
                .first()
            emit(Result.Sucess<Gift>(data = gift))
        }catch (e: Exception){
            emit(Result.Error<Gift>(message = e.localizedMessage ?: "Error unknown"))
        }
    }

    fun updateGift(giftId: String, gift: Gift){
        try {
            val map = mapOf(
                "nom" to gift.nom,
                "precio" to gift.precio
            )
            giftList.document(giftId).update(map)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun delGift(giftId: String){
        try {
            giftList.document(giftId).delete()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}