package com.rootdown.dev.adidevibm.util

import android.content.Context
import android.os.Environment
import android.provider.ContactsContract
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.ExecutorService
import javax.inject.Inject
import kotlin.properties.Delegates


class SecurityHelper(appContext: Context) {

    //livedata reactive stream
    val streamGen: LiveDataReactiveStreams? = null
    private var xByteFlag by Delegates.notNull<Int>()
    private lateinit var outputDirectory: File
    private lateinit var xExecutor: ExecutorService

    val path = Environment.DIRECTORY_DOCUMENTS

    init {
        xStream()
        outputDirectory = outputDirectory.absoluteFile
    }
    // Although you can define your own key generation parameter specification, it's
    // recommended that you use the value specified here.
    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    private val fileToRead = "xaes.txt"
    private val encryptedFile = EncryptedFile.Builder(
        File(outputDirectory, fileToRead),
        appContext,
        mainKeyAlias,
        EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
    ).build()

    private val inputStream = encryptedFile.openFileInput()
    private val byteArrayOutputStream = ByteArrayOutputStream()

    private fun xStream(){
        var nextByte: Int = inputStream.read()
        while (nextByte != -1)
        {
            byteArrayOutputStream.write(nextByte)
            nextByte = inputStream.read()
        }
    }
    val plaintext: ByteArray = byteArrayOutputStream.toByteArray()
}
