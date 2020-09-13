package com.pedroribeiro.data.db

import androidx.room.TypeConverter
import com.pedroribeiro.data.models.BuiltByEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class DBConverters {

    private val moshi by lazy {
        Moshi.Builder().build()
    }

    private val type by lazy {
        Types.newParameterizedType(List::class.java, BuiltByEntity::class.java)
    }

    private val jsonAdapter by lazy {
        moshi.adapter<List<BuiltByEntity>>(type)
    }

    @TypeConverter
    fun builtByToString(builtByList: List<BuiltByEntity>): String {
        if (builtByList.isEmpty()) {
            return ""
        }

        return jsonAdapter.toJson(builtByList)
    }

    @TypeConverter
    fun stringToBuiltBy(string: String): List<BuiltByEntity> {
        if (string.isEmpty()) {
            return emptyList()
        }
        return jsonAdapter.fromJson(string).orEmpty()
    }
}