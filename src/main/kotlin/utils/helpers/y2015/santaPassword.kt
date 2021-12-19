package utils.helpers.y2015

fun String.increment(): String = (this.last() + 1).let {
    if (it > 'z') this.substring(0 until this.length - 1).increment() + 'a'
    else this.substring(0 until this.length - 1) + it
}