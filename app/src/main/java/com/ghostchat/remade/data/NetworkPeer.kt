package com.ghostchat.remade.data

data class NetworkPeer(
    val username: String,
    val displayName: String,
    val ipAddress: String,
    val port: Int = 8988,
    val isOnline: Boolean = true,
    val avatarColorHex: String = "#00A884",
    val statusBio: String = "P2P Ghost Node",
    val lastSeenTimestamp: Long = System.currentTimeMillis()
) {
    fun copy(
        displayName: String = this.displayName,
        ipAddress: String = this.ipAddress,
        port: Int = this.port,
        isOnline: Boolean = this.isOnline,
        avatarColorHex: String = this.avatarColorHex,
        statusBio: String = this.statusBio,
        lastSeenTimestamp: Long = this.lastSeenTimestamp
    ) = NetworkPeer(username, displayName, ipAddress, port, isOnline, avatarColorHex, statusBio, lastSeenTimestamp)
}
