package cn.tx.auth.monlithic.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
open class TestController {

    @GetMapping("/noauth")
    fun testNoAuth(): String = "noauth"

    @PreAuthorize("hasPermission('TEST', 'TEST')")
    @GetMapping("/auth/all")
    open fun testAllAuth(): String = "all auth"

    @GetMapping("/auth")
    fun testAuth(): String = "auth"
}