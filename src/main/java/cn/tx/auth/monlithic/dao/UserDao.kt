package cn.tx.auth.monlithic.dao

import cn.tx.auth.monlithic.pojo.po.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserDao: JpaRepository<User, Int> {

    fun findByUserPhoneNumber(phoneNumber: String) : Optional<User>
}