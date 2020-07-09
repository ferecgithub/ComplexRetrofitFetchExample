package com.ferechamitbeyli.complexfetchretrofitexample.model.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ferechamitbeyli.complexfetchretrofitexample.model.Response


@Dao
interface RedditDao {

    @Insert
    suspend fun insertAll(vararg posts: Response): List<Long>

    @Query("SELECT * FROM response")
    suspend fun getAllPosts(): Response

//    @Query("SELECT * FROM response WHERE uuid = :postId")
//    suspend fun getPost(postId: Int): Data2

    @Query("DELETE FROM response")
    suspend fun deleteAllPosts()

}