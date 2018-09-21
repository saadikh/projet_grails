<html>
<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title><g:message code='springSecurity.login.title'/></title>

</head>

<body>
<div class="content">
    <div class="container-fluid">

        <div class="col-md-6 col-md-offset-3">
            <div class="card">
                <div class="header">
                    <h4 class="title">Please identify yourself</h4>
                </div>
                <div class="content">
                    <div id="login">
                            <g:if test='${flash.message}'>
                                <p class="text-danger">${flash.message}</p>
                            </g:if>

                            <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm"
                                  class="cssform"
                                  autocomplete="off">

                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input type="text" class="form-control border-input"
                                           placeholder="${usernameParameter ?: 'username'}"
                                           name="${usernameParameter ?: 'username'}" id="username">
                                </div>

                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control border-input"
                                           placeholder="${passwordParameter ?: 'username'}"
                                           name="${passwordParameter ?: 'password'}" id="password"/>
                                </div>
                                <div class="form-group">
                                    <div class="form-check">
                                        <label class="custom-control custom-checkbox mb-2 mr-sm-2 mb-sm-0">
                                            <input class="custom-control-input" name="${rememberMeParameter ?: 'remember-me'}"
                                                   id="remember_me" type="checkbox" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">Stay connected</span>
                                        </label>
                                    </div>
                                </div>

                                <div class="text-center">
                                    <button type="submit" id="submit"
                                            class="btn btn-info btn-fill btn-wd">connection</button>
                                </div>

                            </form>

                    </div>
                </div>
            </div>
        </div>



    </div>
</div>

<script>
    (function() {
        document.forms['loginForm'].elements['${usernameParameter ?: 'username'}'].focus();
    })();
</script>
</body>
</html>