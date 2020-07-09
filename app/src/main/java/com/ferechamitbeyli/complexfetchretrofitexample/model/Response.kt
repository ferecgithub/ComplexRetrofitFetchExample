package com.ferechamitbeyli.complexfetchretrofitexample.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Response(

    @ColumnInfo(name = "kind")
    @SerializedName("kind")
    val kind: String,

    @ColumnInfo(name = "data1")
    @SerializedName("data")
    val data1: Data1


) {
    @PrimaryKey(autoGenerate = true)
    var uuid_response: Int = 0
}