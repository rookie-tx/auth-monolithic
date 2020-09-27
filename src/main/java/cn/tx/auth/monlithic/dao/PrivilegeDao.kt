package cn.tx.auth.monlithic.dao

import cn.tx.auth.monlithic.pojo.po.Privilege
import org.springframework.data.jpa.repository.JpaRepository

interface PrivilegeDao : JpaRepository<Privilege, Int>