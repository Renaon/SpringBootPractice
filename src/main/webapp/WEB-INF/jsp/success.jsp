<%@page pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
	
	<section class="u-clearfix u-section-1" id="sec-ec93">
      <div class="u-clearfix u-sheet u-sheet-1">
			<h1>Product Form Result</h1>
			<br>
			ID: ${form.id}
			<br>
			Title: ${form.title}
			<br>
			Cost: ${form.price}
			<br>
			<a href="add" class="u-border-none u-btn u-btn-round u-button-style u-hover-palette-1-light-1 u-palette-2-light-1 u-radius-6 u-btn-1">Добавить еще</a>    
			
	  </div>
    </section>
    
    <%@ include file="footer.jsp" %>