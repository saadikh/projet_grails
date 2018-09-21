<html>

<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title><g:message code='springSecurity.denied.title'/></title>
</head>

<body>
    <div class="content">
        <div class="container-fluid">
            <div class="col-md-12">
                <div class="card">
                    <div class="content">
                        <div class="row">
                            <div class="col-md-12">
                                <h5><div class="errors"><i class="ti-lock" style="font-size: 50px;    vertical-align: middle;"></i>&nbsp;&nbsp;&nbsp;<g:message code='springSecurity.denied.message'/></div></h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>