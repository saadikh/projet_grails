

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.thiaw.grails.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.thiaw.grails.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.thiaw.grails.Role'
grails.plugin.springsecurity.requestMap.className = 'fr.thiaw.grails.UserRole'
grails.plugin.springsecurity.securityConfigType = 'Annotation'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
	//[pattern: '/**/', 			access: ['ROLE_ADMIN']],
	//[pattern: '/role/**', 			access: ['ROLE_ADMIN']]

]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

