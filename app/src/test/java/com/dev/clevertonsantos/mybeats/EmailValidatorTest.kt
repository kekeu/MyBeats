package com.dev.clevertonsantos.mybeats

import com.dev.clevertonsantos.mybeats.extensions.isEmailValid
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun checkCorrectEmailReturnsTrue() {
        assertTrue("teste_@email.com".isEmailValid())
    }

    @Test
    fun checkCorrectEmailSubDomainReturnsTrue() {
        assertTrue("teste@email.co.uk".isEmailValid())
    }

    @Test
    fun checkInvalidEmailNoTldReturnsFalse() {
        assertFalse("name@email".isEmailValid())
    }

    @Test
    fun checkInvalidEmailDoubleDotReturnsFalse() {
        assertFalse("name@email..com".isEmailValid())
    }

    @Test
    fun checkInvalidEmailNoUsernameReturnsFalse() {
        assertFalse("@email.com".isEmailValid())
    }

    @Test
    fun checkEmptyStringReturnsFalse() {
        assertFalse("".isEmailValid())
    }
}