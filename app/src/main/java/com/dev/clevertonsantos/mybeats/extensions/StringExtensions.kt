package com.dev.clevertonsantos.mybeats.extensions

import java.util.regex.Pattern

private val EMAIL_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
)

fun String.isEmailValid() = EMAIL_PATTERN.matcher(this).matches()