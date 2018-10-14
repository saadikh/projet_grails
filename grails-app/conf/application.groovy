

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.mbds.grails.fr.mbds.grails.springsec.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.mbds.grails.fr.mbds.grails.springsec.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.mbds.grails.fr.mbds.grails.springsec.Role'


grails.plugin.springsecurity.rest.token.storage.jwt.useEncryptedJwt = true
grails.plugin.springsecurity.conf.rest.token.storage.jwt.secret = 'qrD6h8K6S9503Q06Y6Rfk21TErImPYqa'
grails.plugin.springsecurity.rest.login.active = true
grails.plugin.springsecurity.rest.login.endpointUrl = "/api/login"
grails.plugin.springsecurity.rest.login.failureStatusCode = 401

grails.plugin.springsecurity.logout.postOnly = false

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
		[pattern: '/', access: ['permitAll']],
		[pattern: '/error', access: ['permitAll']],
		[pattern: '/userInterface', access: ['permitAll']],
		[pattern: '/adminInterface', access: ['ROLE_ADMIN']],
		[pattern: '/shutdown', access: ['permitAll']],
		[pattern: '/assets/**', access: ['permitAll']],
		[pattern: '/**/js/**', access: ['permitAll']],
		[pattern: '/**/css/**', access: ['permitAll']],
		[pattern: '/**/images/**', access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [

		[pattern: '/assets/**', filters: 'none'],
		[pattern: '/uploads/**', filters: 'none'],
		[pattern: '/**/js/**', filters: 'none'],
		[pattern: '/**/css/**', filters: 'none'],
		[pattern: '/**/images/**', filters: 'none'],
		[pattern: '/**/favicon.ico', filters: 'none'],
		[pattern: '/**', filters: 'JOINED_FILTERS'],

		[
				pattern: '/api/**',
				filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
		],

		[
				pattern: '/**',
				filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
		]
]


/*grails.plugin.springsecurity.successHandler.alwaysUseDefault = true
grails.plugin.springsecurity.successHandler.defaultTargetUrl = "/api/success"*/

/* Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.mbds.webservice.rest.sec.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.mbds.webservice.rest.sec.UserRole'
grails.plugin.springsecurity.authority.className = 'org.mbds.webservice.rest.sec.Role'
*/


/*grails.plugin.springsecurity.rememberMe.persistent = false
grails.plugin.springsecurity.rest.login.useJsonCredentials = true
grails.plugin.springsecurity.rest.login.failureStatusCode = 401
grails.plugin.springsecurity.rest.token.storage.useGorm = true
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'fr.mbds.grails.fr.mbds.grails.AuthenticationToken'
grails.plugin.springsecurity.rest.token.storage.gorm.tokenValuePropertyName = 'token'
grails.plugin.springsecurity.rest.token.storage.gorm.usernamePropertyName = 'username'*/
