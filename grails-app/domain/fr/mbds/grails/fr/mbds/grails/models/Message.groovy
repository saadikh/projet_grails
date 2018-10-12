package fr.mbds.grails.fr.mbds.grails.models

import fr.mbds.grails.fr.mbds.grails.springsec.User

class Message {
    User author
    User target
    String content

    static belongsTo = [user: User]

    //static hasMany = [users: User]

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
