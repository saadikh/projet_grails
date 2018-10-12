package fr.mbds.webservice.rest

import fr.mbds.grails.fr.mbds.grails.models.Match
import fr.mbds.grails.fr.mbds.grails.models.Message
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
        render(status: 200, text: "Connected! <br>"
                + "Welcome the user " + springSecurityService.currentUser.id + " <br>" +
                "<a href='/api/index'>go to the home</a>")
    }

    @Secured("ROLE_ADMIN")
    def index() {

        render("Welcome, here are the web services available: <br>" +
                "<ol>" +
                "<li><a href='http://localhost:8080/grails/api/user'>Users</a></li>" +
                "<li><a href='http://localhost:8080/grails/api/message'>Messages</a></li>" +
                "<li><a href='http://localhost:8080/grails/api/match'>Matches</a></li>" +
                "</ol>")
    }


    def users(){
        user()
    }
    def user() {
        switch (request.getMethod()) {
            case 'GET':
                println springSecurityService.getPrincipal().authorities
                if (!params.id) {
                    if (User.findAll().size() == 0) {
                        render(status: 200, text: "Not user")
                    }
                    def users = [users: User.findAll()]
                    render users as JSON

                } else {
                    if (!User.findById(params.id)) {
                        render(status: 404, text: "User not found")
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

                def userInstance
                userInstance = new User(username: params.get("username"), password: params.get("password"))


                if (userInstance.save(flush: true)) {
                    render(status: 201, text: "user successfully inserted")
                } else {
                    render(status: 400, text: "Parameters are not in the right format")
                }
                break
            case 'PUT':
                if (springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_USER" }) {
                    render(status: 403, text: "You're not a admin, you can't edit a user")
                    return
                }
                request.withFormat {
                    json {
                            def putUser = User.executeUpdate("update User b set b.username = '" + request.JSON.username + "'" +
                                    " , b.password = '" + request.JSON.password + "'" +
                                    " where b.id = " + params.id)
                            if (putUser) {
                                render(status: 202, text: "User successfully updated")
                            } else {
                                render(status: 400, text: "Error")
                            }
                        }
                    }
                break
            case 'DELETE':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_ADMIN" }) {
                    render(status: 403, text: "You're not admin, you can't delete a user")
                    return
                }
                if (!User.findById(params.id)) {
                    render(status: 404, text: "The user can't be found")
                    return
                }
                def delUser = User.executeUpdate("delete User where id = " + params.id)
                if (delUser) {
                    render(status: 202, text: "The user is successfully deleted")
                } else {
                    render(status: 400, text: "the request is poorly formatted")
                }
                break
            default:
                response.status = 405
        }
    }

    def messages(){
        messages()
    }
    def message(){
        switch (request.getMethod()) {
            case 'GET':
                println springSecurityService.getPrincipal().authorities
                if (!params.id) {
                    if (Message.findAll().size() == 0) {
                        render(status: 200, text: "No message")
                    }
                    def messages = [messages: Message.findAll()]
                    render messages as JSON

                } else {
                    if (!Message.findById(params.id)) {
                        render(status: 404, text: "Message not found")
                        return
                    }
                    if (Message.findById(params.id)) {
                        response.status = 200
                        render Message.findById(params.id) as JSON
                    } else {
                        response.status = 400
                    }
                }
                break
            case 'POST':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_USER" }) {
                    render(status: 403, text: "You're not admin, you can't add a message")
                    return
                }

                def messageInstance
                messageInstance = new Message(author: params.get("author"), target: params.get("target"), content: params.get("content"))


                if (messageInstance.save(flush: true)) {
                    render(status: 201, text: "message successfully inserted")
                } else {
                    render(status: 400, text: "Parameters are not in the right format")
                }
                break
            case 'PUT':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_USER" }) {
                    render(status: 403, text: "You're not admin, you can't edit a message")
                    return
                }
                request.withFormat {
                    json {
                        def putMessage = Message.executeUpdate("update Message b set b.author = '" + request.JSON.author + "'" +
                                " , b.target = '" + request.JSON.target + "'" + ", b.content = '" + request.JSON.content + "'" +
                                " where b.id = " + params.id)
                        if (putMessage) {
                            render(status: 202, text: "Message updated successfully")
                        } else {
                            render(status: 400, text: "Error")
                        }
                    }
                }
                break
            case 'DELETE':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_ADMIN" }) {
                    render(status: 403, text: "You're not admin, you can't delete a message")
                    return
                }
                if (!Message.findById(params.id)) {
                    render(status: 404, text: "The message was not found")
                    return
                }
                def delMessage = Message.executeUpdate("delete Message where id = " + params.id)
                if (delMessage) {
                    render(status: 202, text: "The message is successfully deleted")
                } else {
                    render(status: 400, text: "the request is poorly formatted")
                }
                break
            default:
                response.status = 405
        }
    }

    def matches(){
        match()
    }
    def match(){
        switch (request.getMethod()) {
            case 'GET':
                println springSecurityService.getPrincipal().authorities
                if (!params.id) {
                    if (Match.findAll().size() == 0) {
                        render(status: 200, text: "No match")
                    }
                    def matches = [matches: Match.findAll()]
                    render matches as JSON

                } else {
                    if (!Match.findById(params.id)) {
                        render(status: 404, text: "Match not found")
                        return
                    }
                    if (Match.findById(params.id)) {
                        response.status = 200
                        render Match.findById(params.id) as JSON
                    } else {
                        response.status = 400
                    }
                }
                break
            case 'POST':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_USER" }) {
                    render(status: 405, text: "You're not admin, so you can't add match")
                    return
                }

                def matchInstance
                matchInstance = new Match(winner: params.get("winner"), looser: params.get("looser"), winnerScore: params.get("winnerScore"), looserScore: params.get("looserScore"))


                if (matchInstance.save(flush: true)) {
                    render(status: 201, text: "match successfully inserted")
                } else {
                    render(status: 400, text: "Parameters are not in the right format")
                }
                break
            case 'PUT':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_USER" }) {
                    render(status: 405, text: "You're not a admin, you can't edit a match")
                    return
                }
                request.withFormat {
                    json {
                        def putMatch = Match.executeUpdate("update Match b set b.winner = '" + request.JSON.winner + "'" +
                                " , b.looser = '" + request.JSON.looser + "'" + ", b.winnerScore = '" + request.JSON.winnerScore + "'" +
                                ", b.looserScore = " + request.JSON.looserScore + "'" +
                                " where b.id = " + params.id)
                        if (putMatch) {
                            render(status: 202, text: "Match successfully updated")
                        } else {
                            render(status: 400, text: "Error")
                        }
                    }
                }
                break
            case 'DELETE':
                if (!springSecurityService.getPrincipal().authorities.any { it.authority == "ROLE_ADMIN" }) {
                    render(status: 401, text: "You're not admin, you can't delete a match")
                    return
                }
                if (!Match.findById(params.id)) {
                    render(status: 404, text: "The match can't be found")
                    return
                }
                def delMatch = Match.executeUpdate("delete Match where id = " + params.id)
                if (delMatch) {
                    render(status: 202, text: "The match is successfully deleted")
                } else {
                    render(status: 400, text: "the request is poorly formatted")
                }
                break
            default:
                response.status = 405
        }
    }

}
