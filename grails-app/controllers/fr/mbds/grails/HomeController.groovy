package fr.mbds.grails

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

@Secured(["permitAll"])
class HomeController {

    def index() { }

    def show() {

        if (SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/adminInterface")
            return
        }
        response.sendRedirect(request.getContextPath() + "/userInterface")
    }
}
