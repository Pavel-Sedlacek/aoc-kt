package utils.helpers.y2020

fun floatingValues(mask: String, address: String): MutableList<String> {
    val addresses = mutableListOf<String>()
    if (mask.isNotEmpty()) {
        val maskR = mask[mask.lastIndex]
        val addressR = address[address.lastIndex]

        val recurse = floatingValues(mask.substring(0, mask.lastIndex), address.substring(0, address.lastIndex))

        when (maskR) {
            '0' -> {
                addresses.addAll(recurse.map { "$it$addressR" })
            }
            '1' -> {
                addresses.addAll(recurse.map { "$it$maskR" })
            }
            'X' -> {
                addresses.addAll(recurse.map { "${it}0" })
                addresses.addAll(recurse.map { "${it}1" })
            }
        }
    } else {
        addresses.add("")
    }
    return addresses
}