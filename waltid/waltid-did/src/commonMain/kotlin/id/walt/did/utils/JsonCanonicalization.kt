package id.walt.did.utils

expect object JsonCanonicalization {
    fun getCanonicalBytes(json: String): ByteArray
    fun getCanonicalString(json: String): String
}
