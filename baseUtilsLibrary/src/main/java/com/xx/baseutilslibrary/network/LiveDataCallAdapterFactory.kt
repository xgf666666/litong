package com.xx.baseutilslibrary.network

import android.arch.lifecycle.LiveData
import com.xx.baseutilslibrary.network.entity.ApiResponse
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class LiveDataCallAdapterFactory: CallAdapter.Factory(){


    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {

        if (Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }

        val observableType=Factory.getParameterUpperBound(0,returnType as ParameterizedType)
        val rawObservableType=Factory.getRawType(observableType)

        if (rawObservableType !=ApiResponse::class.java) {
            return null
        }

        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }

        val bodyType = Factory.getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)


    }



}