<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
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
                        <g:each in="${matchs}">
                            <>
                                <h3>${matchs.winner}</h3>

                                <p>${it.looser}</p>
                            </li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>