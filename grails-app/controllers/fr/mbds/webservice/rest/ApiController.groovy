package fr.mbds.webservice.rest

import fr.mbds.grails.fr.mbds.grails.springsec.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.apache.tomcat.jni.Library

import java.awt.print.Book
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

@Secured('permitAll')
class ApiController {
    def springSecurityService

    @Secured("ROLE_ADMIN")
    def success() {
        render(status: 200, text: "Connecté! <br>"
                + "Bienvenue l'utilisateur " + springSecurityService.currentUser.id + " <br>" +
                "<a href='/api/index'>aller à l'accueil</a>")
    }

    @Secured("ROLE_ADMIN")
    def index() {

        render("Bienvenue, voici les web services disponibles: <br>" +
                "<ol>" +
                "<li><a href='http://localhost:8080/grails/api/user'>Les Users</a></li>" +
                "<li><a href='http://localhost:8080/grails/api/message'>Les Messages</a></li>" +
                "</ol>")
    }

    def user() {
        switch (request.getMethod()) {
            case 'GET':
                println springSecurityService.getPrincipal().authorities
                if (!params.id) {
                    if (User.findAll().size() == 0) {
                        render(status: 200, text: "Pas de user")
                    }
                    def users = [users: User.findAll()]
                    render users as JSON

                } else {
                    if (!User.findById(params.id)) {
                        render(status: 404, text: "User introuvable")
                        return
                    }
                    if (User.findById(params.id)) {
                        response.status = 200
                        render User.findById(params.id) as JSON
                    } else {
                        response.status = 400
                    }
                }
                break
     
            default:
                response.status = 405
        }
    }

}
