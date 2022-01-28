package com.ojanbelajar.aronta.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import javax.annotation.Nonnull


@Entity(tableName = "buruhs")
@Parcelize
data class BuruhEntity(
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

    @ColumnInfo(name = "harga")
    var harga: String = "",

    @ColumnInfo(name = "keahlian")
    var keahlian: String = "",

    @ColumnInfo(name = "workhour")
    var workhour: String = "",

    @ColumnInfo(name = "tipe")
    var tipe: List<String> = emptyList(),

    @ColumnInfo(name = "rating")
    var rating: String = "",

    @ColumnInfo(name = "image")
    var image: String = ""
): Parcelable
