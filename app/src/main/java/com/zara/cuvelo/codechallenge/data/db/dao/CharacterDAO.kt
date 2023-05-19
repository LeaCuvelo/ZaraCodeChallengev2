package com.zara.cuvelo.codechallenge.data.db.dao


import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zara.cuvelo.codechallenge.data.db.entity.Character

@Dao
interface CharacterDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    @Query("SELECT * From characters ORDER BY page")
    fun getCharacters(): PagingSource<Int, Character>

    @Query("DELETE FROM characters")
    suspend fun clearAllCharacters()


}