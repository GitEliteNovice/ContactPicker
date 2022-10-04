package com.aryan.fastcontacts_picker.picker

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.aryan.fastcontacts_picker.ContactInfo
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class FastContactPickerImpl(private val context: Context) : FastContactPicker(), CoroutineScope {

    private val defaultExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, throwable.printStackTrace().toString())
    }

    private val parentJob = SupervisorJob()

    private val contact = ContactLogicImpl(context)

    companion object {
        private var fastContactPicker: FastContactPickerImpl? = null
        const val TAG = "HandleExceptionActivity"
        fun getInstance(context: Context): FastContactPickerImpl {
            if (fastContactPicker == null) {
                fastContactPicker =
                    FastContactPickerImpl(context)
            }
            return fastContactPicker as FastContactPickerImpl
        }
    }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + parentJob + defaultExceptionHandler

    override suspend fun getContacts(): ArrayList<ContactInfo>? {
        val data = CoroutineScope(coroutineContext).async {

            val postResult =
                runCatching {
                    contact.contacts()
                }
            postResult.onSuccess { // do some work
                return@onSuccess
            }.onFailure { exception: Throwable ->
                Log.d(TAG, "inside launch" + exception.printStackTrace().toString())
                return@onFailure
            }


        }
        var result = data.await()
        return result.getOrNull()
    }


}