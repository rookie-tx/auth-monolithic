package cn.tx.auth.monlithic.security.filter

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter(manager: AuthenticationManager): BasicAuthenticationFilter(manager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authentication = getAuthentication(request)
        if (authentication == null) {
            chain.doFilter(request, response)
            return
        }

        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(TOKEN_HEADER)
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            val signingKey = JWT_SECRET.toByteArray()
            val parseToken = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(signingKey))
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))

            val username = parseToken.body.subject

            val authorities = (parseToken.body[AUTHORITIES] as List<*>)
                .stream()
                .map { SimpleGrantedAuthority(it.toString()) }
                .collect(Collectors.toList())

            if (username != null) {
                return UsernamePasswordAuthenticationToken(username, null, authorities)
            }
        }

        return null
    }
}