package com.zara.cuvelo.codechallenge.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Character is used as unique class for model, entity and domain
 * This decision because Character is the only model, if the app allows
 * another kind of model, we can migrate to a multi ways of handle data. In a multimodular application
 */


@Entity(tableName = "Characters")
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "species")
    val species: String,
    @SerializedName("url")
    @ColumnInfo(name = "url_photo")
    var photoUrl: String,
    @ColumnInfo(name = "page")
    var page: Int
)