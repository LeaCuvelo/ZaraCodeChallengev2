package com.zara.cuvelo.codechallenge.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "url_photo")
    val photoUrl: String,
    @ColumnInfo(name = "page")
    val page: Int
)