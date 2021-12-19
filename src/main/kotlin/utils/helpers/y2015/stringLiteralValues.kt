package utils.helpers.y2015

import java.util.regex.Pattern

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