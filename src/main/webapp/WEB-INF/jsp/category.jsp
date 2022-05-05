<%@page pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

    <section class="u-clearfix u-grey-5 u-section-1" id="sec-2cea">
      <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <p class="u-text u-text-2">
			<c:forEach var="item" items="${products}"> 
				${item.title}   ${item.price} <br>
            </c:forEach>
		</p>
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