<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>

    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
</head>

<body>
<div class="wrapper">
    <div class="sidebar" data-background-color="white" data-active-color="danger">
        <div class="sidebar-wrapper">

            <ul class="nav">
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <li class="${request.forwardURI == '/grails/adminInterface'? 'active':''}">
                        <a href="/grails/adminInterface">
                            <i class="ti-stats-up"></i>
                            <p>Dashboard</p>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_USER">
                    <li class="${request.forwardURI.contains('/grails/user/show')? 'active':''}">

                        <a href="/grails/user/show/${sec.loggedInUserInfo(field: 'id')}">
                            <i class="ti-user"></i>

                            <p>Profile</p>
                        </a>

                    </li>
                </sec:ifAnyGranted>

                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <li class="${request.forwardURI.contains('/grails/user') && !request.forwardURI.contains('userInterface')? 'active':''}">
                        <a href="/grails/user">
                            <i class="ti-settings"></i>

                            <p>User management</p>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_USER">
                    <li class="${request.forwardURI.contains('/grails/match')  && !request.forwardURI.contains('match')? 'active':''}">
                        <a href="/grails/match">
                            <i class="ti-map-alt"></i>

                            <p>Match management</p>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_USER">
                    <li class="${request.forwardURI.contains('/grails/message')? 'active':''}">
                        <a href="/grails/message">
                            <i class="ti-layers-alt"></i>

                            <p>Message management</p>
                        </a>
                    </li>
                </sec:ifAnyGranted>
            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <sec:ifAnyGranted roles="ROLE_USER">
                        <span class="navbar-brand" href="#">User plateforme</span>
                    </sec:ifAnyGranted>
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <span class="navbar-brand" href="#">Admin plateforme</span>
                    </sec:ifAnyGranted>

                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <sec:ifLoggedIn>
                            <li>
                                <a href="/grails/user/show/${sec.loggedInUserInfo(field: 'id')}">
                                    <i class="ti-user"></i>

                                    <p>${sec.loggedInUserInfo(field: 'username')}</p>
                                </a>
                            </li>
                            <li>
                                <a href="/grails/logout">
                                    <i class="ti-power-off"></i>

                                    <p>Logout</p>
                                </a>
                            </li>
                        </sec:ifLoggedIn>
                        <sec:ifNotLoggedIn>
                            <li>
                                <a href="/grails/login">
                                    <i class="ti-unlock"></i>

                                    <p>Login</p>
                                </a>
                            </li>
                        </sec:ifNotLoggedIn>
                    </ul>

                </div>
            </div>
        </nav>


        <g:layoutBody/>

        <footer class="footer" role="contentinfo">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>

                        <li>
                            <a href="">
                                Directed by Mamadou and Maguette
                            </a>
                        </li>
                    </ul>
                </nav>

                <div class="copyright pull-right">
                    2018 - 2019
                </div>
            </div>
        </footer>

    </div>

</div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
