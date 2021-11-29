package com.sevenpeakssoftware.farhan.usecase.errors

import com.sevenpeakssoftware.farhan.data.error.Error
import com.sevenpeakssoftware.farhan.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by FarhanAhmed
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
