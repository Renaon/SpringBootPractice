<%@page pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>


<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta charset="utf-8">
      <meta name="keywords" content="Store">
      <meta name="description" content="">
      <meta name="page_type" content="np-template-header-footer-from-plugin">
      <title>Вход</title>
      <link rel="stylesheet" href="nicepage.css" media="screen">
  <link rel="stylesheet" href="Главная.css" media="screen">
      <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
      <script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>
      <meta name="generator" content="Nicepage 4.6.5, nicepage.com">
      <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">


      <script type="application/ld+json">{
  		"@context": "http://schema.org",
  		"@type": "Organization",
  		"name": "",
  		"logo": "images/rimworld-logo-6.png"
  }</script>
      <meta name="theme-color" content="#478ac9">
      <meta property="og:title" content="Главная">
      <meta property="og:type" content="website">
</head>
  <header class="u-clearfix u-header u-header" id="sec-9743"><div class="u-clearfix u-sheet u-sheet-1">
        <a href="https://nicepage.com" class="u-image u-logo u-image-1" data-image-width="400" data-image-height="400">
          <img src="images/rimworld-logo-6.png" class="u-logo-image u-logo-image-1">
        </a>
        <p class="u-text u-text-default u-text-1">RimShop</p>
        <nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1">
          <div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px;">
            <a class="u-button-style u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="#">
              <svg class="u-svg-link" viewBox="0 0 24 24"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#menu-hamburger"></use></svg>
              <svg class="u-svg-content" version="1.1" id="menu-hamburger" viewBox="0 0 16 16" x="0px" y="0px" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg"><g><rect y="1" width="16" height="2"></rect><rect y="7" width="16" height="2"></rect><rect y="13" width="16" height="2"></rect>
</g></svg>
            </a>
          </div>
          <div class="u-custom-menu u-nav-container">
            <ul class="u-nav u-unstyled u-nav-1"><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="" style="padding: 10px 20px;">Главная</a>
			<ul class="u-nav u-unstyled u-nav-1"><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="/" style="padding: 10px 20px;">Главная</a>
			</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="categories" style="padding: 10px 20px;">Категории</a>
			</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="add" style="padding: 10px 20px;">Добавить товар</a>
			</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="Войти.html" style="padding: 10px 20px;">Войти</a>
			</li></ul>
          </div>
          <div class="u-custom-menu u-nav-container-collapse">
            <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
              <div class="u-inner-container-layout u-sidenav-overflow">
                <div class="u-menu-close"></div>
                <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/" style="padding: 10px 20px;">Главная</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="categories" style="padding: 10px 20px;">Категории</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="add" style="padding: 10px 20px;">Добавить товар</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="login" style="padding: 10px 20px;">Войти</a>
</li></ul>
              </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
          </div>
        </nav>
      </div>
</header>


<h1>Add a product </h1>
<form:form action="success" modelAttribute="product">
    <form:input path="title" type='text'/>
    <br>
    <form:input path="price" type='text'/>
    <br>
	<form:input path="category" type='text' placeholder="Раздел"/>
    <br>
    <input type="submit" value="Submit" />
</form:form>

</body>

<%@ include file="footer.jsp" %>
