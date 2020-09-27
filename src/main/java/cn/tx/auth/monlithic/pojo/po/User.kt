package cn.tx.auth.monlithic.pojo.po

import javax.persistence.*

@Table
@Entity(name = "user")
class User {

    @Id @Column(name = "user_id") var userId: Int? = 0

    lateinit var userPhoneNumber: String

    lateinit var userEmail: String

    lateinit var userPassword: String

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "role_id")])
    lateinit var roles: List<Role>

    override fun toString(): String {
        return "User(userId=$userId, phoneNumber='$userPhoneNumber', email='$userEmail', password='$userPassword', roles=$roles)"
    }
}