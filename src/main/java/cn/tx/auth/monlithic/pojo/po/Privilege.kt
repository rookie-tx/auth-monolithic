package cn.tx.auth.monlithic.pojo.po

import javax.persistence.*

@Entity
@Table(name = "privilege")
class Privilege {

    @Id @Column(name = "privilege_id") var privilegeId: Int? = 0

    var privilegeName: String? = null

    var privilegeCode: String? = null

    override fun toString(): String {
        return "Privilege(privilegeId=$privilegeId, privilegeName=$privilegeName, privilegeCode=$privilegeCode)"
    }
}