<%@page pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>


	<body data-home-page="/" data-home-page-title="Главная" class="u-body u-xl-mode" modelAttribute="products">
    <section class="u-clearfix u-section-1" id="sec-4840">
      <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-clearfix u-custom-html u-expanded-width u-custom-html-1">
		<h1>Store</h1>
          <ul> Product     Price <br>
            <c:forEach var="item" items="${products}">${item.image} ${item.title}   ${item.price}
				<a href="/productdel?id=${item.id}" class="u-button-style u-nav-link">del</a> 
				<a href="/buy?id=${item.id}" class="u-button-style u-nav-link">Добавить в корзину</a>
				<br>
            </c:forEach>
          </ul>
        </div>
      </div>
    </section>
    
    
    <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-fe3e"><div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Пример текста. Кликните, чтобы выбрать текстовый блок. Кликните еще раз или сделайте двойной клик, чтобы начать редактирование текста.</p>
      </div></footer>
    <section class="u-backlink u-clearfix u-grey-80">
      <a class="u-link" href="https://nicepage.com/website-templates" target="_blank">
        <span>Website Templates</span>
      </a>
      <p class="u-text">
        <span>created with</span>
      </p>
      <a class="u-link" href="" target="_blank">
        <span>Website Builder Software</span>
      </a>. 
    </section>
  </body>
</html>