package com.ojanbelajar.aronta.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.ojanbelajar.aronta.data.source.local.entity.BuruhEntity
import com.ojanbelajar.aronta.data.source.local.entity.FarmerEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ArontaDao {
    @Query("Select * from farmers")
    fun getFarmers(): Flow<List<FarmerEntity>>

    @Query("Select * from farmers where id =:id")
    fun getFarmer(id: String): Flow<FarmerEntity>

    @Query("Select * from buruhs")
    fun getBuruhs(): Flow<List<BuruhEntity>>

    @Query("Select * from buruhs where id =:id")
    fun getBuruh(id: String): Flow<BuruhEntity>


}