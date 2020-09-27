package cn.tx.auth.monlithic.dao

import cn.tx.auth.monlithic.pojo.po.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleDao: JpaRepository<Role, Int>