package com.pedroribeiro.data.db

import androidx.room.TypeConverter
import com.pedroribeiro.data.models.BuiltBy
import com.squareup.moshi.Moshi
import kotlin.math.sign

class DBConverters {

    private val moshi by lazy {
        Moshi.Builder().build()
    }
    private val jsonAdapter by lazy {
        moshi.adapter<List<BuiltBy>>(BuiltBy::class.java)
    }

    @TypeConverter
    fun fromBuiltByList(builtByList: List<BuiltBy>): String {
        if (builtByList.isEmpty()) {
            return ""
        }

        return jsonAdapter.toJson(builtByList)
    }

    @TypeConverter
    fun toBuiltByList(string: String): List<BuiltBy>? {
        if (string.isEmpty()) {
            return emptyList()
        }
        return jsonAdapter.fromJson(string)
    }
}