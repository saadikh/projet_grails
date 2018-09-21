package fr.mbds.grails.fr.mbds.grails.models

import fr.mbds.grails.fr.mbds.grails.springsec.User

class Message {
    User author
    User target
    String content

   // static hasMany = [user: User]

    static belongsTo = [user: User]

    static constraints = {
    }

}
