package com.sevenpeakssoftware.farhan.usecase.errors

import com.sevenpeakssoftware.farhan.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
