package com.sheverdyaevartem.hh.sign_in.data.dto

data class SmsCodeVerifiedResponse(val success: Boolean, val id: String) : NetworkResponse()