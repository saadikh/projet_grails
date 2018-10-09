package fr.mbds.grails.springsec

import fr.mbds.grails.UserService
import fr.mbds.grails.fr.mbds.grails.models.CustomerService
import fr.mbds.grails.fr.mbds.grails.models.Match
import fr.mbds.grails.fr.mbds.grails.models.Message
import fr.mbds.grails.fr.mbds.grails.springsec.Role
import fr.mbds.grails.fr.mbds.grails.springsec.User
import fr.mbds.grails.fr.mbds.grails.springsec.UserRole
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(["ROLE_ADMIN"])
class UserController {
    UserService userService

    CustomerService customerService

    def springSecurityService
    static allowedMethods = [save: "POST", update: "PUT", delete: ["POST", "DELETE"]]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model: [userCount: User.count()]
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def show(User user) {

        def currentUser = springSecurityService.getCurrentUser()

        if (currentUser.authorities.authority[0] == "ROLE_USER" && currentUser != user) {
            response.sendRedirect(request.getContextPath() + "/accessDenied")
        } else {
            respond user
        }
        respond user

    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def getByIdAsJson(Long id) {
        def responseData = [
                'user'         : User.findById(id),
                'username': User.findById(id).username,
        ]
        render responseData as JSON
    }

    @Secured(["ROLE_ADMIN"])
    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view: 'create'
            return
        }


        user.save flush: true

        /*if (params.message) {
            user.addToMessages(Message.findById(params.messages))

        } else {
            println("no message defined")
        }*/


        if(params.role == "ROLE_USER"){
            UserRole.create(user, Role.findOrSaveWhere('authority': 'ROLE_USER'), true)
        }

        if(params.role == "ROLE_ADMIN"){
            UserRole.create(user, Role.findOrSaveWhere('authority': 'ROLE_ADMIN'), true)
        }



        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def edit(User user) {
        respond user
    }
    @Transactional
    def update(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view: 'edit'
            return
        }

        user.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: OK] }
        }
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    def delete(User user) {
        if (user == null) {
            notFound()
            return
        }

        //customerService.delete(user.getId())

        Collection<UserRole> ur = UserRole.findAllByUser(user)
        /*Collection<Message> ums = UserRole.findAllByUser(user)
        Collection<Match> umt = UserRole.findAllByUser(user)
        ums*.delete()
        umt*.delete()*/
        ur*.delete(flush: true)
        user.delete(flush: true)


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
