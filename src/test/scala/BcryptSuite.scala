package com.github.roundrop.bcrypt

class BcryptSuite extends munit.FunSuite:

  test("bcrypt, isBcrypted"):
    for
      hash <- "my password".bcrypt()
    yield
      val result = "my password".isBcrypted(hash)
      assert(result.isSuccess)

    for
      hash <- "my wrong password".bcrypt()
    yield
      val result = "my wrong password".isBcrypted(hash)
      assert(result.isFailure)

  test("bcrypt with rounds"):
    for
      hash <- "my password".bcrypt(12)
    yield
      val result = "my password".isBcrypted(hash)
      assert(result.isSuccess)

  test("bcrypt with overlong password should be Failure"):
    val result1 = ("a" * 73).bcrypt()
    assert(result1.isFailure)
    assert(result1.failed.get.isInstanceOf[IllegalArgumentException])
    val result2 = ("a" * 1024).bcrypt()
    assert(result2.isFailure)
    assert(result2.failed.get.isInstanceOf[IllegalArgumentException])

  test("bcrypt with salt"):
    val salt = "$2a$13$8K1p/a0dL1LXMIgoEDFrwO"
    for
      hash <- "my password".bcrypt(salt)
    yield
      val result = "my password".isBcrypted(hash)
      assert(result.isSuccess)

  test("wrong salt"):
    val salt = "wrong-salt"
    val result = "my password".bcrypt(salt)
    assert(result.isFailure)
    assert(result.failed.get.isInstanceOf[IllegalArgumentException])

  test("bcryptUnsafely, isBcryptedUnsafely"):
    val hash = "my password".bcryptUnsafely()
    assert("my password".isBcryptedUnsafely(hash) == true)
    assert("my wrong password".isBcryptedUnsafely(hash) == false)

  test("bcryptUnsafely with rounds"):
    val hash = "my password".bcryptUnsafely(12)
    assert("my password".isBcryptedUnsafely(hash) == true)
    assert("my wrong password".isBcryptedUnsafely(hash) == false)

  test("bcryptUnsafely with maximum length password"):
    val pw72 = "a" * 72
    val hash = pw72.bcryptUnsafely()
    assert(pw72.isBcryptedUnsafely(hash) == true)

  test("bcryptUnsafely with overlong password should throw IllegalArgumentException"):
    intercept[IllegalArgumentException]:
      ("a" * 73).bcryptUnsafely()
    intercept[IllegalArgumentException]:
      ("a" * 1024).bcryptUnsafely()

  test("bcryptUnsafely with salt"):
    val salt = "$2a$10$8K1p/a0dL1LXMIgoEDFrwO"
    val hash = "my password".bcryptUnsafely(salt)
    assert("my password".isBcryptedUnsafely(hash) == true)
    assert("my wrong password".isBcryptedUnsafely(hash) == false)
