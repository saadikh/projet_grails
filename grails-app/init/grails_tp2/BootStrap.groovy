package grails_tp2

import fr.thiaw.grails.Match
import fr.thiaw.grails.Message
import fr.thiaw.grails.Role
import fr.thiaw.grails.User
import fr.thiaw.grails.UserRole

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def gamingRole = new Role(authority: 'ROLE_USER').save(flush: true)


        def adminUser = new User(username: 'admin', password: 'pw0').save()
        def playerUser1 = new User(username: 'player1', password: 'pw1').save()
        def playerUser2 = new User(username: 'player2', password: 'pw2').save()



        UserRole.create(adminUser, adminRole, true)
        UserRole.create(playerUser1, gamingRole, true)
        UserRole.create(playerUser2, gamingRole,  true)

        def match = new Match(winner: playerUser1, looser: playerUser2, winnerScore: 10, looserscore: 1).save(flush: true, failOnError: true)

        def message1 = new Message(author: playerUser1, target: playerUser2, content:"hello friend").save(flush: true, failOnError: true)
        def message2 = new Message(author: playerUser2, target: playerUser1, content:"hello friend").save(flush: true, failOnError: true)

    }
    def destroy = {
    }
}
