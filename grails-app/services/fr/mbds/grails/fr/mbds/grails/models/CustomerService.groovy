package fr.mbds.grails.fr.mbds.grails.models

import fr.mbds.grails.UserRoleService
import fr.mbds.grails.UserService
import fr.mbds.grails.fr.mbds.grails.springsec.User
import fr.mbds.grails.fr.mbds.grails.springsec.UserRole
import grails.gorm.transactions.Transactional
import grails.util.Holders
import org.springframework.context.ApplicationContext

import javax.jws.soap.SOAPBinding.Use

@Transactional
class CustomerService {

    ApplicationContext ctx = Holders.grailsApplication.mainContext
    UserService userService
    UserRoleService userRoleService
    private java.util.Collection<fr.mbds.grails.fr.mbds.grails.springsec.UserRole> ur

    void init(){
        userService = ctx.getBean("userService")
        userRoleService = ctx.getBean("userRoleService")
    }

    def serviceMethod() {

    }

    boolean deleteUser(User user){
        if(user == null){
            return false
        }

        Collection<UserRole> ur = UserRole.findAllByUser(User.findAllByUsername(user.username))
        ur*.delete(flush: true)
        user.delete(flush: true)
        return true
    }
    /*boolean delete(Long id){
        if (id == null) {
            return false
        }
        def ur = UserRole.get(id,1)
        def ur2 = UserRole.get(id,2)
        if(ur){userRoleService.delete(ur)}
        if(ur2){userRoleService.delete(ur2)}
        userService.delete(id)
        return true
    }*/
}
