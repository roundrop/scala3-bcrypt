package dev.roundrop.bcrypt

import com.password4j._
import java.nio.charset.StandardCharsets
import scala.util.Try

extension (s: String)

  private def defaultVersion = com.password4j.types.Bcrypt.valueOf('a')

  def bcrypt(rounds: Int = 10): Try[String] =
    Try(bcryptUnsafely(rounds))

  def bcrypt(salt: String): Try[String] =
    Try(bcryptUnsafely(salt))

  def isBcrypted(hash: String): Try[Boolean] =
    Try(isBcryptedUnsafely(hash))

  def bcryptUnsafely(rounds: Int = 10): String =
    if s.length > 72 then
      throw new IllegalArgumentException("Password length must be less than or equal to 72 characters")
    Password.hash(s).`with`(BcryptFunction.getInstance(defaultVersion, rounds)).getResult()

  def bcryptUnsafely(salt: String): String =
    if s.length > 72 then
      throw new IllegalArgumentException("Password length must be less than or equal to 72 characters")
    Password.hash(s).`with`(BcryptFunction.getInstanceFromHash(salt)).getResult()

  def isBcryptedUnsafely(hash: String): Boolean =
    Password.check(s, hash).`with`(BcryptFunction.getInstanceFromHash(hash))
