package utils.common

import java.security.MessageDigest
import java.util.regex.Pattern

fun String.md5(): ByteArray = MessageDigest.getInstance("MD5").digest(this.toByteArray())
fun String.literalString(p: Pattern): String {
    var input = this
    p.matcher(input).also {
        input = StringBuilder().also { builder ->
            while (it.find()) {
                builder.append(it.group().length)
                builder.append(it.group(1))
            }
        }.toString()
    }
    return input
}