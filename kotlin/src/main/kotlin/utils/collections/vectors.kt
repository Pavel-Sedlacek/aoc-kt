package utils.collections

fun Vec3i(x: Int, y: Int, z: Int) = Vec4i(x, y, z, 0)
data class Vec4i(val x: Int, val y: Int, val z: Int, val w: Int)