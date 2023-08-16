package com.example.githubapp.data.mapper

import androidx.annotation.StringRes
import com.example.githubapp.R
import java.net.UnknownHostException

class ErrorMapper {

    fun mapResponseCode(code: Int): CustomException {
        return when (code) {
            404 -> CustomException.NotFoundException()
            422 -> CustomException.TooMuchRequestsException()
            503 -> CustomException.UnavailableException()
            else -> CustomException.UnknownException()
        }
    }

    fun mapResponseException(exception: Exception): CustomException {
        return when (exception) {
            is UnknownHostException -> CustomException.InternetErrorException()
            else -> CustomException.UnknownException()
        }
    }
}

sealed class CustomException(@StringRes val messageRes: Int) : Exception() {
    class UnavailableException : CustomException(R.string.unavailable_content_text)
    class NotFoundException : CustomException(R.string.not_found_content_text)
    class TooMuchRequestsException : CustomException(R.string.too_much_requests_content_text)
    class InternetErrorException : CustomException(R.string.no_internet_connection_text)
    class UnknownException : CustomException(R.string.unknown_error_text)
}