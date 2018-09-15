package m2_mbds

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush:true, failOnError: false)
        def gamingRole = new Role(authority: 'ROLE_USER').save(flush:true, failOnError: false)


        def adminUser = new User(username: 'admin', password: 'pw0').save(flush:true, failOnError: false)
        def playerUser1 = new User(username: 'player1', password: 'pw1').save(flush:true, failOnError: false)
        def playerUser2 = new User(username: 'player2', password: 'pw2').save(flush:true, failOnError: false)



        UserRole.create(adminUser, adminRole, true)
        UserRole.create(playerUser1, gamingRole, true)
        UserRole.create(playerUser2, gamingRole,  true)





        //def user1 = new User(username: 'toto', password: 'toto').save(flush: true, failOnError: true)
        //def user2 = new User(username: 'Player2', password: 'Player2').save(flush: true, failOnError: true)

        def match = new Match(winner: playerUser1, looser: playerUser2, winnerScore: 10, looserscore: 1).save(flush: true, failOnError: true)

        def message1 = new Message(author: playerUser1, target: playerUser2, content:"hello friend2").save(flush: true, failOnError: true)
        def message2 = new Message(author: playerUser2, target: playerUser1, content:"hello friend1").save(flush: true, failOnError: true)

    }
    def destroy = {
    }
}

