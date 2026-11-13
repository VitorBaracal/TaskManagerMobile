package com.example.taskmanagermobile.auth

import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object AuthManager {
    private const val ITERATIONS = 100_000

    fun createSalt(): ByteArray = ByteArray(16).also {
        SecureRandom().nextBytes(it)
    }

    fun createHash(password: String, salt: ByteArray): ByteArray {
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATIONS, 256)

        return try {
            SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
                .generateSecret(spec).encoded
        } finally {
            spec.clearPassword()
        }
    }

    fun validatePassword(password: String, salt: ByteArray, savedHash: ByteArray): Boolean {
        val hash = createHash(password, salt)

        return try {
            MessageDigest.isEqual(savedHash, hash)
        } finally {
            hash.fill(0)
        }
    }
}
