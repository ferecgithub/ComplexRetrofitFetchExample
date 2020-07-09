package com.ferechamitbeyli.complexfetchretrofitexample.model.DB

import androidx.room.TypeConverter
import com.ferechamitbeyli.complexfetchretrofitexample.model.Children
import com.ferechamitbeyli.complexfetchretrofitexample.model.Data1
import com.ferechamitbeyli.complexfetchretrofitexample.model.Data2
import com.google.gson.Gson

class Converters {

    //Data1 converter
    @TypeConverter
    fun fromData1(data1: Data1): String {
        return Gson().toJson(data1)
    }

    @TypeConverter
    fun toData1(value: String): Data1 {
        return Gson().fromJson(value, Data1::class.java)
    }

    //Data2 converter
    @TypeConverter
    fun fromData2(data2: Data2): String {
        return Gson().toJson(data2)
    }

    @TypeConverter
    fun toData2(value: String): Data2 {
        return Gson().fromJson(value, Data2::class.java)
    }

    //Children converter
    @TypeConverter
    fun fromChildren(children: List<Children>): String? {
        return return Gson().toJson(children)
    }

    @TypeConverter
    fun toChildren(value: String): List<Children> {
        return Gson().fromJson(value, Data1::class.java).children.toList()
    }


}