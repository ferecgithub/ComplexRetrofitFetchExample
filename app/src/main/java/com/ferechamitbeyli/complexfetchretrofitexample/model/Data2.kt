package com.ferechamitbeyli.complexfetchretrofitexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Data2(

    @SerializedName("author_fullname")
    val authorName: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("subreddit_name_prefixed")
    val subredditName: String,

    @SerializedName("thumbnail")
    val image: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid_post: Int = 0
}