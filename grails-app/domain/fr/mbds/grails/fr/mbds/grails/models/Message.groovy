package fr.mbds.grails.fr.mbds.grails.models

import fr.mbds.grails.fr.mbds.grails.springsec.User

class Message {
    User author
    User target
    String content

    Date dateCreated = new Date()
    boolean read = false

    static constraints = {
        author nullable: false, blank: false
        target nullable: false, blank: false
        content nullable: false, blank: false

    }

    @Override
    String toString(){
        return id
    }
}
