package grailsproject


import fr.mbds.grails.fr.mbds.grails.models.Message
import fr.mbds.grails.fr.mbds.grails.springsec.Role
import fr.mbds.grails.fr.mbds.grails.springsec.User
import fr.mbds.grails.fr.mbds.grails.springsec.UserRole

class BootStrap {

    def init = { servletContext ->

        def adminRole = Role.findOrSaveWhere('authority': 'ROLE_ADMIN')
        def userRole = Role.findOrSaveWhere('authority': 'ROLE_USER')
        def admin = User.findOrSaveWhere(username: 'admin', password: 'admin', enabled: true)
        def user = User.findOrSaveWhere(username: 'user', password: 'user', enabled: true)

        //String path = 'uploads/images/users/'


        //def img = new Image(name: 'Thiaw', description: 'profile', url: path + 'thiaw.jpg', user: admin).save(flush: true, failOnError: true)
        //def msg = new Message(author: admin, target: user, content: "salut")

        //admin.addToMessages(msg)


        if (!admin.authorities.contains(adminRole)) {
            UserRole.create(admin, adminRole, true)
        }

        if (!user.authorities.contains(userRole)) {
            UserRole.create(user, userRole, true)
        }


        User.withSession { it.flush() }
        //def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush:true, failOnError: false)
        //def gamingRole = new Role(authority: 'ROLE_USER').save(flush:true, failOnError: false)


        //def adminUser = new User(username: 'admin', password: 'pw0').save(flush:true, failOnError: false)

        //String path = "C:\\Users\\thiaw\\Documents\\mbds\\grails\\grailsProject\\grails-app\\uploads\\images\\users\\"
        //String path1 = 'uploads/images/users/'
        //def m = new Image(name: 'La Roustide', description: 'restaurant', url: path).save(flush: true, failOnError: true)






        //UserRole.create(adminUser, adminRole, true)




}

    def destroy = {
    }
}
