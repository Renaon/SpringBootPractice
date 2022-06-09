<%@page pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>


	<body data-home-page="/" data-home-page-title="Корзина" class="u-body u-xl-mode" modelAttribute="products">
    <section class="u-clearfix u-section-1" id="sec-4840">
      <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-clearfix u-custom-html u-expanded-width u-custom-html-1">
		<h1>Корзина</h1>
            <c:forEach var="item" items="${products}">
				<table cellspacing="0">
					<tr>
						<td><img src="${item.logo_path}" width="256" height="256"/></td>
						<td>${item.title}</td>
						<td>${item.price}</td>
						<td><a href="/productdel?id=${item.id}" class="u-button-style u-nav-link">del</a></td>
						<td><a href="/buy?id=${item.id}" class="u-button-style u-nav-link">Добавить в корзину</a></td>
					</tr>
				</table>
				<br>
            </c:forEach>
            <h2 modelAttribute="cart"> ИТОГО: ${cart.totalPrice}</h2>
          </ul>
        </div>
      </div>
    </section>

	<%@ include file="footer.jsp" %>
