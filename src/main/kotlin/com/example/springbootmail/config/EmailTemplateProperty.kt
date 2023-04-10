package com.example.springbootmail.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.templatemode.TemplateMode
import java.nio.charset.StandardCharsets


@Configuration
class EmailTemplateProperty {
    @Bean
    fun springTemplateEngine(): SpringTemplateEngine? {
        val templateEngine = SpringTemplateEngine()
        templateEngine.addTemplateResolver(htmlTemplateResolver())
        return templateEngine
    }

    @Bean
    fun htmlTemplateResolver(): SpringResourceTemplateResolver? {
        val emailTemplateResolver = SpringResourceTemplateResolver()
        emailTemplateResolver.prefix = "classpath:/templates/"
        emailTemplateResolver.suffix = ".html"
        emailTemplateResolver.templateMode = TemplateMode.HTML
        emailTemplateResolver.characterEncoding = StandardCharsets.UTF_8.name()
        return emailTemplateResolver
    }


}