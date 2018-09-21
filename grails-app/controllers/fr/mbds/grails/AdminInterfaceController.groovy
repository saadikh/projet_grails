package fr.mbds.grails

import fr.mbds.grails.fr.mbds.grails.models.Match
import fr.mbds.grails.fr.mbds.grails.models.Message
import fr.mbds.grails.fr.mbds.grails.springsec.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class AdminInterfaceController {

    def index() {
        def nbUsers = User.count
        def nbMatchs = Match.count
        def nbMessages = Message.count
        [
                nbUsers     : nbUsers,
                nbMatchs      : nbMatchs,
                nbMessages: nbMessages
        ]
    }

    def graphsData() {
        Map<Integer, Integer> nbMessageByUser = new HashMap<>();
        int i = 0
        for (Message message : Message.findAll()) {
            nbMessageByUser.put(message.id, message.user.size())
        }
        def responseData = [
                'message' : nbMessageByUser,
                'users': User.executeQuery("SELECT  concat(day(e.dateCreated), '-', month(e.dateCreated), '-', year(e.dateCreated)), COUNT(*) FROM User e GROUP BY concat(day(e.dateCreated), '-', month(e.dateCreated), '-', year(e.dateCreated))")
        ]
        render responseData as JSON
    }

}
