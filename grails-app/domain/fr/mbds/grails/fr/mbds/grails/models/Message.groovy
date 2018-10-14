package fr.mbds.grails.fr.mbds.grails.models

import fr.mbds.grails.fr.mbds.grails.springsec.User

class Message {
    User author
    User target
    String content

    static belongsTo = [user: User]

    Date dateCreated = new Date()
    boolean read = false

    //static hasMany = [users: User]

    static constraints = {
        author nullable: false, blank: false
        target nullable: false, blank: false
        content nullable: false, blank: false

    }

    /*static mapping = {
        user lazy: false
    }*/

    @Override
    String toString(){
        return id
    }
}
