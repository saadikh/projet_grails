package fr.mbds.grails.fr.mbds.grails.models

import fr.mbds.grails.fr.mbds.grails.springsec.User

class Image {

    String name
    String description
    String url

    //no user equals no image
    static belongsTo = [user: User]

    static constraints = {
        name maxSize: 100
    }
}
