package com.developer.desafioinfo.utils
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(update: Boolean, databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =

    liveData(Dispatchers.IO) {
        if(update){
            emit(Resource.update())

        }else{
            emit(Resource.loading())

        }
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }
