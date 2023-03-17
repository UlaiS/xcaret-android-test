package com.xcaret.test.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xcaret.test.data.local.entity.XTDRPokemonEntity

class XTDLTypeConverter {

    @TypeConverter
    fun stringToXTDRPokemonEntity(xtdrPokemon: String): List<XTDRPokemonEntity> {
        return Gson().fromJson(xtdrPokemon, object : TypeToken< List<XTDRPokemonEntity>>() {}.type)
    }

    @TypeConverter
    fun xtdrPokemonEntityToString(listOfString: List<XTDRPokemonEntity>): String {
        return Gson().toJson(listOfString)
    }

}