package fr.mbds.grails.springsec


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

    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def getByIdAsJson(Long id) {
        def responseData = [
                'user'         : User.findById(id),
                'username': User.findById(id).username,
                'image'      : User.findById(id).image
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

        if (params.message) {
            user.addToMessages(Message.findById(params.messages))

        } else {
            println("no message defined")
        }

        user.save flush: true

        if(params.role == "ROLE_USER"){
            UserRole.create(user, Role.findOrSaveWhere('authority': 'ROLE_USER'), true)
        }

        if(params.role == "ROLE_ADMIN"){
            UserRole.create(user, Role.findOrSaveWhere('authority': 'ROLE_ADMIN'), true)
        }

       /* String imagename =  new Date().getTime() + '.jpg'
        String filenae = 'C:/wamp/www/img/' + imagename

        File imageFile = new File(filenae)
        imageFile.createNewFile()

        params.profileImage.transferTo(imageFile)

        user.image = imagename*/

        List fileList = request.getFiles('photos')
        int j = 0;
        fileList.each { file ->
            if (file.getOriginalFilename()) {
                String path = 'uploads/images/users/'
                file.transferTo(new File(grailsApplication.config.getProperty('image.path') + "image_" + j + "user" + user.id + ".jpg"))
                Image image = new Image(
                        name: "image_" + j + "user" + user.id,
                        description: "image_" + j + "user" + user.id,
                        url: path + "image_" + j + "user" + user.id + ".jpg",
                        user: user).save(flush: true, failOnError: true)
                user.addToImage(image)
                j++
            }
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
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        user.delete flush: true

        request.withFormat {
            form multipartForm {

                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
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
