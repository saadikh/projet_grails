package fr.mbds.grails.fr.mbds.grails.models

import fr.mbds.grails.fr.mbds.grails.springsec.User

class Match {
    User winner
    User looser
    int winnerScore
    int looserScore
    Date dateCreated


    //static hasMany = [user: User]


    static constraints = {

        //dateCreated nullable: false

    }
}
