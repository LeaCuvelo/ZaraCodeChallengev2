package com.zara.cuvelo.codechallenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zara.cuvelo.codechallenge.data.db.dao.CharacterDAO
import com.zara.cuvelo.codechallenge.data.db.dao.RemoteKeysDao
import com.zara.cuvelo.codechallenge.data.db.entity.CharacterEntity
import com.zara.cuvelo.codechallenge.data.db.entity.RemoteKeys

@Database(
    entities = [CharacterEntity::class, RemoteKeys::class],
    version = 1)


abstract class ZaraDatabase: RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDAO
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}