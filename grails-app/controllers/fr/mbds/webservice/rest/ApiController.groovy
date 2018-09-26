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
            case 'POST':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_USER" }) {
                    render(status: 405, text: "Vous n'etes pas adhérant, impossible d'jouter un livre")
                    return
                }

                def userInstance
                userInstance = new User(username: params.get("username"), password: params.get("password"))


                if (userInstance.save(flush: true)) {
                    render(status: 201, text: "user inseré avec succès")
                } else {
                    render(status: 400, text: "Des parametres ne sont pas au bon format")
                }
                break
            case 'PUT':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_USER" }) {
                    render(status: 405, text: "Vous n'etes pas adhérant, impossible de modifer un livre")
                    return
                }
                request.withFormat {
                    json {
                            def putUser = User.executeUpdate("update User b set b.username = '" + request.JSON.username + "'" +
                                    " , b.password = '" + request.JSON.password + "'" +
                                    " where b.id = " + params.id)
                            if (putUser) {
                                render(status: 202, text: "User mis à jour avec succès")
                            } else {
                                render(status: 400, text: "erreur")
                            }
                        }
                    }
                break
            case 'DELETE':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_ADMIN" }) {
                    render(status: 401, text: "Vous n'etes pas admin, impossible de supprimer un livre")
                    return
                }
                if (!User.findById(params.id)) {
                    render(status: 404, text: "Le user est introuvable")
                    return
                }
                def delUser = User.executeUpdate("delete User where id = " + params.id)
                if (delUser) {
                    render(status: 202, text: "Le user est supprimée avec succès")
                } else {
                    render(status: 400, text: "la requête est mal formatée")
                }
                break
            default:
                response.status = 405
        }
    }

}
