package cn.tx.auth.monlithic.security.filter

/** 登录请求路径 */
const val AUTH_LOGIN_RUL = "/login"

/** 用户名 */
const val LOGIN_PARAM_USERNAME = "username"
/** 密码 */
const val LOGIN_PARAM_PASSWORD = "password"

const val JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf"
const val TOKEN_HEADER = "Authorization"
const val TOKEN_PREFIX = "Bearer "
const val TOKEN_TYPE = "jwt"
const val TOKEN_ISSUER = "auth-monolithic"
const val TOKEN_AUDIENCE = "auth-monolithic"
const val AUTHORITIES = "authorities"
