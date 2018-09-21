<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Admin interface</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-4 col-sm-6">
                <div class="card">
                    <div class="content">
                        <div class="row">
                            <div class="col-xs-5">
                                <div class="icon-big icon-warning text-center">
                                    <i class="ti-user"></i>
                                </div>
                            </div>

                            <div class="col-xs-7">
                                <div class="numbers">
                                    <p> User number</p>
                                    ${nbUsers}
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-sm-6">
                <div class="card">
                    <div class="content">
                        <div class="row">
                            <div class="col-xs-5">
                                <div class="icon-big icon-success text-center">
                                    <i class="ti-map-alt"></i>
                                </div>
                            </div>

                            <div class="col-xs-7">
                                <div class="numbers">
                                    <p>Number of Matches</p>
                                    ${nbMatchs}
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-sm-6">
                <div class="card">
                    <div class="content">
                        <div class="row">
                            <div class="col-xs-5">
                                <div class="icon-big icon-danger text-center">
                                    <i class="ti-view-grid"></i>
                                </div>
                            </div>

                            <div class="col-xs-7">
                                <div class="numbers">
                                    <p>Number of messages</p>
                                    ${nbMessages}
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>

    </div>
</div>
<script type="text/javascript" src="/grails/assets/jquery-2.2.0.min.js?compile=false"></script>
<script type="text/javascript" src="/grails/assets/custom/moment.js?compile=false"></script>
<script type="text/javascript" src="/grails/assets/custom/chartist.min.js?compile=false" ></script>
<script type="text/javascript" src="/grails/assets/custom/demo.js?compile=false" ></script>
</body>
</html>
