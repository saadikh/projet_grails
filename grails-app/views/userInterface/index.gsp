<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="drag_drop.css"/>
    <asset:stylesheet src="userMessages.css"/>
    <title>User home</title>
</head>

<body>
<div class="content">
    <div class="container-fluid">
        <div class="card card-map">
            <div class="header">
                <h4 class="title"><span class="ti-map-alt"></span> Matches</h4>
            </div>

                <div class="message">
                    <ul class="messageList">
                        <g:each in="${matches}">
                            <li>
                                <h3>${matches.winner}</h3>

                            </li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </div>
    </div>
<div class="bloc-principal">
</div>

%{--<div class="bloc-message">
    <div class="nav" role="navigation"><br/>
        <ul>
            <li><g:link controller="message" class="create" action="create">Create Message</g:link></li>
        </ul>
    </div>
</div>--}%

</body>
</html>