package cn.tx.auth.monlithic.security.config

import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import java.io.Serializable

class CustomPermissionEvaluator: PermissionEvaluator {

    override fun hasPermission(authentication: Authentication?, targetDomainObject: Any?, permission: Any?): Boolean {
        if (authentication == null || targetDomainObject == null
            || targetDomainObject !is String || permission !is String) {
            return false
        }

        return hasPrivilege(authentication, targetDomainObject, permission)
    }

    override fun hasPermission(authentication: Authentication?, targetId: Serializable?, targetType: String?,
                               permission: Any?): Boolean {
        if (authentication == null || targetType == null || permission !is String) {
            return false
        }

        return hasPrivilege(authentication, targetType, permission)
    }

    private fun hasPrivilege(authentication: Authentication, targetType: String, permission: String): Boolean {
        val actualPermission = targetType + "_" + permission

        for (authority in authentication.authorities) {
            if (authority.authority == actualPermission) {
                return true
            }

            if (authority.authority == "ALL") {
                return true
            }
        }

        return false
    }
}