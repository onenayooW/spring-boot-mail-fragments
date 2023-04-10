package com.example.springbootmail.helper

import com.example.springbootmail.config.EmailTemplateProperty
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine
import java.nio.charset.StandardCharsets
import java.util.regex.Pattern

@Component
class EmailHelper(
    private val emailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine,
    private val emailTemplateProperty: EmailTemplateProperty,
) {

    companion object {
        fun isEmail(email: String) =
            email.length in 1..100 &&
                    Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches()
    }

    private fun sendEmail(subject: String, text: String, from: String, vararg to: String) {
        val mimeMessage = emailSender.createMimeMessage()

        val mimeMessageHelper = MimeMessageHelper(
            mimeMessage,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            StandardCharsets.UTF_8.name()
        )

        mimeMessageHelper.setSubject(subject)
        mimeMessageHelper.setFrom(from)
        mimeMessageHelper.setTo(to)
        mimeMessageHelper.setText(text, true)

        emailSender.send(mimeMessage)
    }


    fun sendMail(
        to: String = "onenayoo.wittawat@gmail.com",
    ) {

        val text = templateEngine.process(
            "demo.html",
            Context().apply {
                this.setVariables(
                    mapOf(
                        "pageTitle" to "Rap Yo",
                        "headerFragment" to templateEngine.process("fragments/header.html", Context())
                    )
                )
            }
        )


        sendEmail(
            subject = "Welcome New Truck2Hand Member",
            text = text,
            from = "no-reply@truck2hand.com",
            to = arrayOf(to)
        )

    }
}

enum class Language {
    TH,
    EN
}