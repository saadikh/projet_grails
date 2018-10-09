package fr.mbds.grails

import fr.mbds.grails.fr.mbds.grails.springsec.UserRole
import grails.gorm.services.Service

@Service(UserRole)
interface UserRoleService {

    UserRole get(Serializable id)

    List<UserRole> list(Map args)

    Long count()

    void delete(Serializable id)

    UserRole save(UserRole userRole)

}