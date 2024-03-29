package com.sevenpeakssoftware.farhan.ui.base

import androidx.lifecycle.ViewModel
import com.sevenpeakssoftware.farhan.usecase.errors.ErrorManager
import javax.inject.Inject


/**
 * Created by FarhanAhmed
 */


abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}
