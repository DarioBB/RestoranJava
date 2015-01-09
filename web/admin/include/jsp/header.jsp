<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String sitelink = "http://www.restoran.hr";
String sitename = "Restoran.hr";
String $filename = "categories_unos";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Poslovna aplikacija by D.CMS</title>
	<script type="text/javascript" src="include/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="include/js/show_hide.js"></script>
	<script type="text/javascript" src="include/js/functions.js"></script>
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script> 
	<script type="text/javascript" src="include/js/google_maps.js"></script>

	<link href="style.css" rel="stylesheet" type="text/css" />
	<link href="dropdown.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="include/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="include/js/ckeditor/adapters/jquery.js"></script>

</head>

<body>

<div id="header">

<div id="title">
	<div id="logout"><a href="index.jsp?odjava=odjava">odjava</a> | <a href="<% out.println(sitelink);%>" target="_blank"><% out.println(sitename);%></a></div>
</div>

<div id="nav">

<ul id="nv">
	
	<li class="cli<% if($filename=="index"){ out.println("2"); } else {out.println("1"); }%>">
		<a href="main.jsp"><span>Početna</span></a>
	</li>
	<li class="cli<% if($filename=="categories_pregled" || $filename=="categories_unos"){ out.println("2"); } else {out.println("1"); }%>">
		<a href="javascript:;">
			<span>Kategorije &raquo;</span>
		<!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
		<ul>
			<li class="unos"><a href="categories_unos.jsp">Unos</a></li>
			<li class="pregled topsub_last"><a href="categories_pregled.jsp">Pregled</a></li>
			
		</ul>
	<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
	<%-- <li class="cli<% out.println($filename=="input_categories_unos" || $filename=="input_categories_pregled" || $filename=="input_pregled" || $filename=="input_unos")?"2":"1"%>">
		<a href="javascript:;">
			<span>Inputi &raquo;</span>
		<!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
		<ul>
			<li class="unos"><a href="input_categories_unos.jsp">Unos kategorija inputa</a></li>
			<li class="pregled"><a href="input_categories_pregled.jsp">Pregled kategorija inputa</a></li>
			<li class="unos"><a href="input_unos.jsp">Unos inputa</a></li>
			<li class="pregled topsub_last"><a href="input_pregled.jsp">Pregled inputa</a></li>
		</ul>
	<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
	<li class="cli<% out.println($filename=="items_pregled" || $filename=="items_unos" || $filename=="units_pregled" || $filename=="units_unos" || $filename=="season_prices_table_unos" || $filename=="season_prices_table_pregled" || $filename=="season_prices_table_labels_unos" || $filename=="season_prices_table_labels_pregled" || $filename=="reserved_terms_unos" || $filename=="hotelski_smjestaj_pregled" || $filename=="hotelski_smjestaj_unos")?"2":"1"%>">
		<a href="javascript:;">
			<span>Smještajni kapaciteti &raquo;</span>
		<!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
		<ul>
			<li class="unos"><a href="items_unos.jsp">Objekti Unos</a></li>
			<li class="pregled"><a href="items_pregled.jsp">Objekti Pregled</a></li>
			<li class="unos"><a href="units_unos.jsp">Jedinice Unos</a></li>
			<li class="pregled topsub_last"><a href="units_pregled.jsp">Jedinice Pregled</a></li>
		</ul>
	<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
	--%>
</ul>

</div>

<div id="subnav">

</div>

</div>

<div id="content">