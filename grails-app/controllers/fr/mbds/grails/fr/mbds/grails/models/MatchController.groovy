package fr.mbds.grails.fr.mbds.grails.models


import fr.mbds.grails.MatchService
import grails.converters.JSON
import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(["ROLE_ADMIN"])
class MatchController {

    GrailsApplication grailsApplication

    MatchService matchService


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Match.list(params), model:[matchCount: Match.count()]
    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def show(Match match) {
        respond match
    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def getByIdAsJson(Long id) {
        def responseData = [
                'match'      : Match.findById(id),
                'winner'         : Match.findById(id).winner,
                'looser':    Match.findById(id).looser,
                'winnerScore'      : Match.findById(id).winnerScore,
                'looserScore'      : Match.findById(id).looserScore
        ]
        render responseData as JSON
    }

    def create() {
        respond new Match(params)
    }

    @Transactional
    def save(Match match) {

        if (match == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (match.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond match.errors, view: 'create'
            return
        }



        try {
            matchService.save(match)
        } catch (ValidationException e) {
            respond match.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'match.label', default: 'Match'), match.id])
                redirect match
            }
            '*' { respond match, [status: CREATED] }
        }
    }

    def edit(Match match) {
        respond match
    }

    def update(Match match) {
        if (match == null) {
            notFound()
            return
        }

        try {
            matchService.save(match)
        } catch (ValidationException e) {
            respond match.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'match.label', default: 'Match'), match.id])
                redirect match
            }
            '*'{ respond match, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        matchService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'match.label', default: 'Match'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'match.label', default: 'Match'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
