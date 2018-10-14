package fr.mbds.grails.fr.mbds.grails.models

import fr.mbds.grails.fr.mbds.grails.springsec.User

class Match {
    User winner
    User looser
    int winnerScore
    int looserScore


    static belongsTo = [user: User]

    static constraints = {
    }

    /*static mapping = {
        user lazy: false
    }*/

    @Override
    String toString(){
        return id
    }
}
