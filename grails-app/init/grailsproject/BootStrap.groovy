package grailsproject


import fr.mbds.grails.fr.mbds.grails.springsec.Role;
import fr.mbds.grails.fr.mbds.grails.springsec.User;
import fr.mbds.grails.fr.mbds.grails.springsec.UserRole;

class BootStrap {

    def init = { servletContext ->

        def adminRole = Role.findOrSaveWhere('authority': 'ROLE_ADMIN')
        def userRole = Role.findOrSaveWhere('authority': 'ROLE_USER')
        def admin = User.findOrSaveWhere(username: 'admin', password: 'admin', enabled: true, image: 'admin')
        def user = User.findOrSaveWhere(username: 'user', password: 'user', enabled: true,image: 'user')
        def mamadou = User.findOrSaveWhere(username: 'mamadou', password: 'mamadou', enabled: true, image:'momoImage')
        def maguette = User.findOrSaveWhere(username: 'maguette', password: 'maguette', enabled: true, image:'maguiImage')

        if (!admin.authorities.contains(adminRole)) {
            UserRole.create(admin, adminRole, true)
        }

        if (!user.authorities.contains(userRole)) {
            UserRole.create(user, userRole, true)
        }

        if (!maguette.authorities.contains(userRole)) {
            UserRole.create(maguette, userRole, true)
        }
        if (!mamadou.authorities.contains(userRole)) {
            UserRole.create(mamadou, userRole, true)
        }
        User.withSession { it.flush() }
    }

    def destroy = {
    }
}
