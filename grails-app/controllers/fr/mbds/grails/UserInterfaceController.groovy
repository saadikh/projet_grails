package fr.mbds.grails

import fr.mbds.grails.fr.mbds.grails.models.Match
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class UserInterfaceController {
    def springSecurityService

    def index() {
        def currentUser = springSecurityService.getCurrentUser()

        List<Match> matches = Match.findAllByUser(currentUser)

        [matches: matches]
    }

    def getJsonMatchs(){
        def responseData = [
                'match'         : Match.findAll(),
                'winner': Match.findAll().winner,
                'looser': Match.findAll().looser,
                'winnerScore': Match.findAll().winnerScore,
                'looserScore': Match.findAll().looserScore,
        ]
        render responseData as JSON
    }
}
