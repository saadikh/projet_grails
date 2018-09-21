package fr.mbds.grails

import fr.mbds.grails.fr.mbds.grails.models.Message
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MessageSpec extends Specification implements DomainUnitTest<Message> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
