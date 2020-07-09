package com.ferechamitbeyli.complexfetchretrofitexample.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferechamitbeyli.complexfetchretrofitexample.model.DB.RedditDatabase
import com.ferechamitbeyli.complexfetchretrofitexample.model.Remote.RedditApiService
import com.ferechamitbeyli.complexfetchretrofitexample.model.Response
import com.ferechamitbeyli.complexfetchretrofitexample.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application): BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private val redditApiService = RedditApiService()
    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = 10 * 1000 * 1000 * 1000L

    val dataList = MutableLiveData<Response>()
    val loadingError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val updateTime = prefHelper.getUpdateTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            fetchFromDatabase()
        } else {
            fetchFromRemote()
        }

    }

    fun refreshBypassCache() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            redditApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Response>() {
                    override fun onSuccess(response: Response) {
                        storePostsLocally(response)
                        Toast.makeText(getApplication(), "Reddit posts retrieved from remote end-point", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        loadingError.value = true
                        loading.value = false
                    }

                })
        )

    }

    private fun fetchFromDatabase() {
        loading.value = true
        launch {
            val dogs = RedditDatabase(getApplication()).redditDao().getAllPosts()
            postsRetrieved(dogs)
            Toast.makeText(getApplication(), "Reddit posts retrieved from database", Toast.LENGTH_LONG).show()
        }
    }

    private fun postsRetrieved(response: Response) {
        dataList.value = response
        loadingError.value = false
        loading.value = false
    }

    private fun storePostsLocally(response: Response) {
        launch {
            val dao = RedditDatabase(getApplication()).redditDao()
            dao.deleteAllPosts()
            dao.insertAll(response)
//            val postList = response.data1.children.map {
//                it.data2
//            }.toList()
//            val result = dao.insertAll(*postList.toTypedArray())
//
//            for(i in postList.indices) {
//                postList[i].uuid = result[i].toInt()
//            }
            postsRetrieved(response)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}