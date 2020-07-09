package com.ferechamitbeyli.complexfetchretrofitexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Data1(

    @SerializedName("children")
    val children: List<Children>
) {
    @PrimaryKey(autoGenerate = true)
    var uuid_data1: Int = 0
}