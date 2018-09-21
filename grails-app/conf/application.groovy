

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.mbds.grails.fr.mbds.grails.springsec.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.mbds.grails.fr.mbds.grails.springsec.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.mbds.grails.fr.mbds.grails.springsec.Role'

grails.plugin.springsecurity.logout.postOnly = false
//grails.mime.use.accept.header = true
//grails.plugin.springsecurity.requestMap.className = 'fr.mbds.grails.fr.mbds.grails.springsec.UserRole'
//grails.plugin.springsecurity.securityConfigType = 'Annotation'

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
		[pattern: '/uploads/**', access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
		[pattern: '/assets/**', filters: 'none'],
		[pattern: '/uploads/**', filters: 'none'],
		[pattern: '/**/js/**', filters: 'none'],
		[pattern: '/**/css/**', filters: 'none'],
		[pattern: '/**/images/**', filters: 'none'],
		[pattern: '/**/favicon.ico', filters: 'none'],
		[pattern: '/**', filters: 'JOINED_FILTERS']
]