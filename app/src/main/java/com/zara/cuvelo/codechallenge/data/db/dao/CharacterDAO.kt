package com.zara.cuvelo.codechallenge.data.db.dao


import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zara.cuvelo.codechallenge.data.db.entity.CharacterEntity

@Dao
interface CharacterDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * From characters ORDER BY page")
    fun getCharacters(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM characters")
    suspend fun clearAllCharacters()


}