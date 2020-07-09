package com.ferechamitbeyli.complexfetchretrofitexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Children(

    @SerializedName("kind")
    val kind: String,


    @SerializedName("data")
    val data2: Data2
) {
    @PrimaryKey(autoGenerate = true)
    var uuid_children: Int = 0
}