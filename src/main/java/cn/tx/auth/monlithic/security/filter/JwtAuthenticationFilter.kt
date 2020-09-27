package cn.tx.auth.monlithic.security.filter

import cn.tx.auth.monlithic.security.SecurityUser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(private val manager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    init {
        super.setFilterProcessesUrl(AUTH_LOGIN_RUL)
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val username = request.getParameter(LOGIN_PARAM_USERNAME)
        val password = request.getParameter(LOGIN_PARAM_PASSWORD)


        return manager.authenticate(UsernamePasswordAuthenticationToken(username, password))
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse,
                                          chain: FilterChain, authResult: Authentication) {
        val user = authResult.principal as SecurityUser
        val authorities = user.authorities
            .stream()
            .map { it.authority }
            ?.collect(Collectors.toList())

        val token = Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(JWT_SECRET.toByteArray()), SignatureAlgorithm.HS256)
            .setHeaderParam("typ", TOKEN_TYPE)
            .setIssuer(TOKEN_ISSUER)
            .setAudience(TOKEN_AUDIENCE)
            .setSubject(user.username)
            .setExpiration(Date(System.currentTimeMillis() + 86400000))
            .claim(AUTHORITIES, authorities)
            .compact()

        response.addHeader(TOKEN_HEADER, TOKEN_PREFIX + token)
    }
}

