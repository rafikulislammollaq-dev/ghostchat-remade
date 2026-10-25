package com.ghostchat.remade.data

// Single-profile enforcement: one stable id per person, never duplicate peers.
object IdentityManager {
    private val peers = mutableMapOf<String, NetworkPeer>()

    fun upsertPeer(peer: NetworkPeer): NetworkPeer {
        val existing = peers[peer.username]
        return if (existing == null) {
            peers[peer.username] = peer
            peer
        } else {
            // update mutable fields, keep identity stable
            val updated = existing.copy(
                displayName = peer.displayName,
                ipAddress = peer.ipAddress,
                port = peer.port,
                isOnline = peer.isOnline,
                avatarColorHex = peer.avatarColorHex,
                statusBio = peer.statusBio,
                lastSeenTimestamp = peer.lastSeenTimestamp
            )
            peers[peer.username] = updated
            updated
        }
    }

    fun getPeer(username: String): NetworkPeer? = peers[username]

    fun allPeers(): List<NetworkPeer> = peers.values.toList()

    fun dedupeConversationParticipants(ids: List<String>): List<String> = ids.distinct()
}
