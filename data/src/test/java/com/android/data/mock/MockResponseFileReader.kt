package com.android.data.mock

import java.io.InputStreamReader

/**
 * Class used to read json files in test/resources folder
 */
class MockResponseFileReader(path: String) {

    val content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path)!!)
        content = reader.readText()
        reader.close()
    }
}
