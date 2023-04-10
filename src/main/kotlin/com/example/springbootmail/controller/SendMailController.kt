package com.example.springbootmail.controller

import com.example.springbootmail.helper.EmailHelper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SendMailController(
    private val emailHelper: EmailHelper,
) {


    @GetMapping("/test")
    fun get() {
        emailHelper.sendMail()
    }
}