package fr.mbds.grails.fr.mbds.grails.springsec

import fr.mbds.grails.fr.mbds.grails.models.Image
import fr.mbds.grails.fr.mbds.grails.models.Match
import fr.mbds.grails.fr.mbds.grails.models.Message
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    //Date dateCreated

    static hasMany = [image: Image, messages: Message, match: Match]

    //static belongsTo = [message: Message]



    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        //image nullable: false, blank: false
        image lazy: false
    }

    static mapping = {
	    password column: '`password`'
    }

   /* @Override
    String toString(){
        return username
    }*/
}
