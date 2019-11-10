package com.quantumhiggs.footballmatch.utils

object CommonFunction {

    fun checkNullOrEmpty(value: String?): String {
        return if (value.isNullOrBlank()) {
            "-"
        } else {
            value
        }
    }
}