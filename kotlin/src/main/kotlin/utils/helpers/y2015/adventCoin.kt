package utils.helpers.y2015

import java.security.MessageDigest

fun String.md5(): ByteArray = MessageDigest.getInstance("MD5").digest(this.toByteArray())