package com.ojanbelajar.aronta.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import javax.annotation.Nonnull

@Entity(tableName = "learns")
@Parcelize
data class LearnEntity(
    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "judul")
    var judul: String = "",

    @ColumnInfo(name = "konten")
    var konten: String = "",

    @ColumnInfo(name = "picture")
    var picture: String = "",

    @ColumnInfo(name = "link")
    var link: String = ""
): Parcelable
