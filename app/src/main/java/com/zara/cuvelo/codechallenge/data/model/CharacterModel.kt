package com.zara.cuvelo.codechallenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    @SerializedName("url") val photo: String) : Parcelable


@Parcelize
data class CharacterInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String) : Parcelable

@Parcelize
data class CharacterResult(
    val info: CharacterInfo,
    val results: List<CharacterModel>) : Parcelable