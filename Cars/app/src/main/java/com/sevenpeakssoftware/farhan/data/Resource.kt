package com.sevenpeakssoftware.farhan.data

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
        val data: T? = null,
        val errorCode: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class LocalSuccess<T>(data: T) : Resource<T>(data)
    class LocalInsertSuccess<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class LocalDataError<T>(errorCode: Int) : Resource<T>(null, errorCode)
    class DataError<T>(errorCode: Int) : Resource<T>(null, errorCode)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is LocalSuccess<*> -> "Success[data=$data]"
            is LocalInsertSuccess<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorCode]"
            is LocalDataError -> "Error[exception=$errorCode]"
            is Loading<T> -> "Loading"
        }
    }
}
