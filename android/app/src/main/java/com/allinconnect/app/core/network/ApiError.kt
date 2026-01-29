package com.allinconnect.app.core.network

sealed class ApiError : Exception() {
    data class HttpError(val statusCode: Int, override val message: String?) : ApiError()
    data class NetworkError(val throwable: Throwable) : ApiError()
    data class DecodingError(val throwable: Throwable) : ApiError()
    data class Unauthorized(val reason: String?) : ApiError()
    object NotFound : ApiError()
    object InvalidResponse : ApiError()
    object InvalidURL : ApiError()
}
