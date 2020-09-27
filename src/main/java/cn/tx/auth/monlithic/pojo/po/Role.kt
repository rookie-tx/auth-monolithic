package cn.tx.auth.monlithic.pojo.po

import javax.persistence.*

@Entity
@Table(name = "role")
class Role {

    @Id @Column(name = "role_id") var roleId: Int? = 0

    var roleName: String? = null

    var roleCode: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission", joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "role_id")],
            inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "privilege_id")])
    var privileges: List<Privilege>? = null

    override fun toString(): String {
        return "Role(roleId=$roleId, roleName='$roleName', roleCode='$roleCode', privileges=$privileges)"
    }
}