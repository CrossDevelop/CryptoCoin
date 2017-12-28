@file:JvmName("UtilsKt")
@file:JvmMultifileClass

package crossdevelop.com.cryptocoin

import java.io.ByteArrayOutputStream


/**
 * Not my method
 */
fun convertResourceToString(clzz: Class<*>, fileName: String): String {
    try {
        val inputStream = clzz.classLoader.getResourceAsStream(fileName)
        val result = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var length: Int? = 0
        while ({ length = inputStream.read(buffer); length }() != -1) {
            result.write(buffer, 0, length!!)
        }
        val str = result.toString("UTF-8")
        println(str)
        return str
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}
