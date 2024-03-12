import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator
import org.bouncycastle.crypto.params.KeyParameter
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

val HASH_ITERATIONS = 100000
val UNLOCK_KEY_SIZE = 256
val ENC_KEY_SIZE = 256

//TODO: Change mode from ECB to CBC
object SecurityService {
    private val random = SecureRandom()
    private val aesKeyGen = KeyGenerator.getInstance("AES")
        .also { it.init(ENC_KEY_SIZE, random) }


    fun getUnlockKeyFromMaster(masterKey: String, salt: ByteArray): ByteArray{
        val hashGen = PKCS5S2ParametersGenerator()
        hashGen.init(masterKey.toByteArray(), salt, HASH_ITERATIONS)
        return (hashGen.generateDerivedParameters(UNLOCK_KEY_SIZE) as KeyParameter).key
    }

    fun decodeEncKey(encodedEncKey: ByteArray, unlockKey: ByteArray): ByteArray {
        val decoder = Cipher.getInstance("Twofish")
        decoder.init(Cipher.DECRYPT_MODE, SecretKeySpec(unlockKey, "Twofish"))
        return decoder.doFinal(encodedEncKey)
    }

    fun decodePassword(encKey: ByteArray, encodedPassword: ByteArray): String{
        val decoder = Cipher.getInstance("AES/ECB/PKCS5Padding")
        decoder.init(Cipher.DECRYPT_MODE, SecretKeySpec(encKey, "AES/ECB/PKCS5Padding"))
        return String(decoder.doFinal(encodedPassword)).trimEnd()
    }

    fun generateSalt(): ByteArray{
        val salt = ByteArray(32)
        random.nextBytes(salt)
        return salt
    }

    fun generateEncKey() = aesKeyGen.generateKey().encoded

    fun encodePassword(password: String, encKey: ByteArray): ByteArray{
        val encoder = Cipher.getInstance("AES/ECB/PKCS5Padding")
        encoder.init(Cipher.ENCRYPT_MODE, SecretKeySpec(encKey, "AES/ECB/PKCS5Padding"))
        return encoder.doFinal(password.toByteArray())
    }

    fun encodeEncKey(encKey: ByteArray, unlockKey: ByteArray): ByteArray {
        val encoder = Cipher.getInstance("Twofish")
        encoder.init(Cipher.ENCRYPT_MODE, SecretKeySpec(unlockKey, "Twofish"))
        return encoder.doFinal(encKey)
    }
}