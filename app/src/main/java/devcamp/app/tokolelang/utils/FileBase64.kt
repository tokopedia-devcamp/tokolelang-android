package devcamp.app.tokolelang.utils

import android.util.Base64
import android.util.Base64OutputStream
import java.io.*


/**
 * Created by isfaaghyth on 8/31/18.
 * github: @isfaaghyth
 */
open class FileBase64 {
    companion object {
        fun getStringFile(f: File): String {
            var inputStream: InputStream?
            var encodedFile = ""
            val lastVal: String
            try {
                inputStream = FileInputStream(f.absolutePath)
                val buffer = ByteArray(10240)
                val output = ByteArrayOutputStream()
                val output64 = Base64OutputStream(output, Base64.DEFAULT)
                while (inputStream.read(buffer) != -1) {
                    output64.write(buffer, 0, inputStream.read(buffer))
                }
                output64.close()
                encodedFile = output.toString()
            } catch (e1: FileNotFoundException) {
                e1.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            lastVal = encodedFile
            return lastVal
        }
    }
}