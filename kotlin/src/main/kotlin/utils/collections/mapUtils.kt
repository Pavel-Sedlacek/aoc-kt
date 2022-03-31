package utils.collections


fun <T, U> Map<T, U>.copy(mut: Boolean = false) = if (mut) this.toMutableMap() else this.toMap()