<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html ng-app>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Projeto Agenda - <decorator:title /></title>
    <link href="<c:url value='/resources/css/bootstrap.min.css'  />" rel="stylesheet"/>
    <!--<link href="<c:url value='/resources/css/bootstrap-theme.min.css'  />" rel="stylesheet"/>-->
    <link rel="stylesheet" href="<c:url value='/resources/css/font-awesome/css/font-awesome.min.css'/>">
    <link href="<c:url value='/resources/css/sb-admin.css'  />" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/footer.css'  />" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/estilo.css'  />" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/datepicker.css'  />" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/datepicker.less'  />" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/bootstrap-spinner.css'  />" rel="stylesheet"/>
    <script src="<c:url value='/resources/js/jquery.js' />"></script>
    <script src="<c:url value='/resources/js/jquery.mask.min.js' />"></script>
    <script src="<c:url value='/resources/js/jquery.metisMenu.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap-datepicker.js' />"></script>
    <script src="<c:url value='/resources/js/jquery.spinner.min.js' />"></script>
    <script src="<c:url value='/resources/js/angular.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/resources/js/Main.js' />"></script>
    <script src="<c:url value='/resources/js/MensagemRetorno.js' />"></script>
    <script src="<c:url value='/resources/js/json2.js' />"></script>
    <decorator:head />
    <style>
</style>
<script>
$(function() {

    $('#side-menu').metisMenu();
    $(window).bind("load resize", function() {
        if ($(this).width() < 768) {
            $('div.sidebar-collapse').addClass('collapse');
        } else {
            $('div.sidebar-collapse').removeClass('collapse');
        }
    });
});	

</script>
</head>
<body>
   <div id="wrapper">

        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                    <a class="navbar-brand" href="#" style="padding: 5px 15px !important;">
    				<img src="<c:url value='/resources/images/logo.png' />" width="100" height="39" /> - 
    				Squamata Gastos
    			</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
              <li class="dropdown user-dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-comment"></i> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#"><i class="fa fa-user"></i> Perfil</a></li>
                <li><a href="#"><i class="fa fa-gear"></i> Preferências</a></li>
                <li class="divider"></li>
                <li><a href="<c:url value='/logout'/>"><i class="fa fa-power-off"></i> Log Out</a></li>
              </ul>
            </li>
            <li class="dropdown user-dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-cloud-download"></i> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#"><i class="fa fa-user"></i> Perfil</a></li>
                <li><a href="#"><i class="fa fa-gear"></i> Preferências</a></li>
                <li class="divider"></li>
                <li><a href="<c:url value='/logout'/>"><i class="fa fa-power-off"></i> Log Out</a></li>
              </ul>
            </li>
            <li class="dropdown user-dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#"><i class="fa fa-user"></i> Perfil</a></li>
                <li><a href="#"><i class="fa fa-gear"></i> Preferências</a></li>
                <li class="divider"></li>
                <li><a href="<c:url value='/logout'/>"><i class="fa fa-power-off"></i> Log Out</a></li>
              </ul>
            </li>
            </ul>
            <!-- /.navbar-top-links -->

        </nav>
        <!-- /.navbar-static-top -->

        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    
                    <li class="side-user hidden-xs">
                        <img class="img-circle" style="width: 80%; height: 80%;" src="https://fbcdn-sphotos-f-a.akamaihd.net/hphotos-ak-ash3/t1/1538621_718223968187921_1781263923_n.jpg" alt="">
                        <p class="name tooltip-sidebar-logout">
                            <span class="welcome">${usuarioSessaoVO.nome}</span> 
                            <a class="welcome" href="<c:url value='/logout'/>" data-toggle="tooltip" data-placement="top" title="Logout" data-popup-ordinal="1" id="open_64125943">
                            <i class="fa fa-sign-out"></i></a>
                        </p>
                        <div class="clearfix"></div>
                    </li>
            <li class="menuHome"><a href="<c:url value='/gastos/'/>"><i class="fa fa-home"></i> Home</a></li>
            <li class="menuCadastro">
              <a href="#"><i class="fa fa-briefcase"></i> Contas <b class="caret"></b></a>
              <ul class="nav nav-second-level collapse">
                <li><a href="<c:url value='/contas/'/>">Cadastrar</a></li>
                <li><a href="<c:url value='/contas/listar'/>">Listar</a></li>
              </ul>
            </li>
            <li>
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-tags"></i> Categorias <b class="caret"></b></a>
              <ul class="nav nav-second-level collapse">
                <li><a href="<c:url value='/categorias/'/>">Cadastrar</a></li>
                <li><a href="<c:url value='/categorias/listar'/>">Listar</a></li>
              </ul>
            </li>
            <li class="">
              <a href="#"><i class="fa fa fa-credit-card"></i> Formas Pagamento <b class="caret"></b></a>
              <ul class="nav nav-second-level collapse">
                <li><a href="<c:url value='/formasPagamento/'/>">Cadastrar</a></li>
                <li><a href="<c:url value='/formasPagamento/listar'/>">Listar</a></li>
              </ul>
            </li>
            <li class="last">
              <a href="#"><i class="fa fa-caret-square-o-down"></i> Relatorios <b class="caret"></b></a>
              <ul class="nav nav-second-level collapse">
                <li><a href="/formasPagamento/">Cadastrar</a></li>
                <li><a href="/formasPagamento/listar">Listar</a></li>
              </ul>
            </li>
                </ul>
                <!-- /#side-menu -->
            </div>
            <!-- /.sidebar-collapse -->
        </nav>
        <!-- /.navbar-static-side -->

        <div id="page-wrapper">
			<decorator:body />
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>
</html>