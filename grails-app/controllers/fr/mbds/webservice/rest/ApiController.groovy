package fr.mbds.webservice.rest

import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class ApiController {

    def index() {
        switch (request.getMethod()){
            case "POST":
                render "POST"
                break
            case "GET":
                render"hello"
                break
        }
    }


}
