package fr.mbds.grails.fr.mbds.grails.models


import fr.mbds.grails.MessageService
import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class MessageController {

    GrailsApplication grailsApplication

    MessageService messageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Message.list(params), model:[messageCount: Message.count()]
    }

    def show(Message message) {
        respond message
    }

    def create() {
        respond new Message(params)
    }

    @Transactional
    def save(Message msg) {
        if (msg == null) {
            notFound()
            return
        }

        try {
            messageService.save(msg)
        } catch (ValidationException e) {
            respond msg.errors, view:'create'
            return
        }



        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'msg.label', default: 'Message'), msg.id])
                redirect msg
            }
            '*' { respond msg, [status: CREATED] }
        }
    }

    def edit(Message message) {
        respond message
    }

    @Transactional
    def update(Message msg) {
        if (msg == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }


        if (msg.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond msg.errors, view:'edit'
            return
        }

        msg.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'msg.label', default: 'Message'), msg.id])
                redirect msg
            }
            '*' { respond msg, [status: OK] }
        }
    }

    @Transactional
    def delete(Message msg) {

        if (msg == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        //supprime les refs du message vers les autres users
        msg.delete flush: true


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'msg.label', default: 'Message'), msg.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
