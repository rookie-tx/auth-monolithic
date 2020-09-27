package cn.tx.auth.monlithic

import cn.tx.auth.monlithic.dao.PrivilegeDao
import cn.tx.auth.monlithic.dao.UserDao
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationTest(@Autowired val userDao: UserDao, @Autowired val privilegeDao: PrivilegeDao) {

    @Test
    fun testFindUser() {
    }
}