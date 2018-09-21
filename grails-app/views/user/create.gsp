<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-user" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.user}" method="POST" enctype="multipart/form-data">
                <div class="col-md-6">
                <fieldset class="form">
                    <div class="fieldcontain">
                        <label for="role">Role *</label>
                        <select name="role" id="role">
                            <option value="ROLE_USER">User</option>
                            <option value="ROLE_ADMIN">Admin</option>
                        </select>

                        <f:all bean="user"/>
                    </div>

                </fieldset>
                </div>
                <div class="col-md-6">

                                    <label class="custom-file">
                                        <span class="custom-file-control">Add image</span>
                                        <br><br>
                                        <input type="file" name="photos" id="photos" class="custom-file-input" multiple>

                                    </label>
                                    <ul id="photos-cavnas">

                                    </ul>
                                    <hr>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save btn btn-info" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
            <script type="text/javascript" src="/grails/assets/jquery-2.2.0.min.js?compile=false"></script>

            <script>
                $(function () {
                    var imagesPreview = function (input, photosDiv) {
                        if (input.files) {
                            var filesAmount = input.files.length;

                            for (i = 0; i < filesAmount; i++) {
                                var reader = new FileReader();

                                reader.onload = function (event) {
                                    var li = document.createElement("li");
                                    $($.parseHTML('<img>')).attr('src', event.target.result).appendTo($(li));
                                    $(li).appendTo(photosDiv)
                                }
                                reader.readAsDataURL(input.files[i]);
                            }
                        }

                    };
                    $('#photos').click(function (e) {
                        $("#photos-cavnas").html("");
                    });
                    $('#photos').on('change', function () {
                        imagesPreview(this, '#photos-cavnas');
                    });
                });
            </script>
    </body>
</html>
