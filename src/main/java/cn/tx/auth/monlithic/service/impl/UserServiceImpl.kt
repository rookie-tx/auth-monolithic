package cn.tx.auth.monlithic.service.impl

import cn.tx.auth.monlithic.dao.UserDao
import cn.tx.auth.monlithic.security.SecurityUser
import cn.tx.auth.monlithic.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserServiceImpl(@Autowired val userDao: UserDao): UserService, UserDetailsService {

    private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userDao.findByUserPhoneNumber(username).orElse(null)
                ?: throw UsernameNotFoundException("用户不存在")
        val authorities = user.roles
            .stream()
            .flatMap { it.privileges?.stream() }
            ?.map { SimpleGrantedAuthority(it.privilegeCode) }
            ?.collect(Collectors.toList())

        return SecurityUser(user.userPhoneNumber, user.userPassword, authorities!!)
    }
}
