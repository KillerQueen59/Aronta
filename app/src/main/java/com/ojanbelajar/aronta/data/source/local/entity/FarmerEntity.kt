package com.ojanbelajar.aronta.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import javax.annotation.Nonnull

@Entity(tableName = "farmers")
@Parcelize
data class FarmerEntity (
    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "nama")
    var nama: String = "",

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "telepon")
    var telepon: String = "",

    @ColumnInfo(name = "alamat")
    var alamat: String = "",

    @ColumnInfo(name = "image")
    var image: String = ""
):Parcelable
