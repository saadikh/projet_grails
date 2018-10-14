package fr.mbds.grails.fr.mbds.grails.models

import fr.mbds.grails.UserRoleService
import fr.mbds.grails.UserService
import fr.mbds.grails.fr.mbds.grails.springsec.UserRole
import grails.gorm.transactions.Transactional
import grails.util.Holders
import org.springframework.context.ApplicationContext

@Transactional
class CustomerService {

    ApplicationContext ctx = Holders.grailsApplication.mainContext
    UserService userService
    UserRoleService userRoleService

    void init(){
        userService = ctx.getBean("userService")
        userRoleService = ctx.getBean("userRoleService")
    }

    def serviceMethod() {

    }

    boolean delete(Long id){
        if (id == null) {
            return false
        }
        def ur = UserRole.get(id,1)
        def ur2 = UserRole.get(id,2)
        if(ur){userRoleService.delete(ur)}
        if(ur2){userRoleService.delete(ur2)}
        userService.delete(id)
        return true
    }
}
