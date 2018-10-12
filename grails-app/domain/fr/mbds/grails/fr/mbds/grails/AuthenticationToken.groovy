package fr.mbds.grails.fr.mbds.grails

class AuthenticationToken {

    String username
    String token
    static constraints = {
        username nullable: false
        token nullable: false
    }
}
