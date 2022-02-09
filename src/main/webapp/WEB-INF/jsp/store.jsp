<%@ include file="header.jsp" %>

<body modelAttribute="products">
<h1>Store</h1>
	<ul>
        <c:forEach var="item" items="${products}">
            Product:  ${item.title}  Price:  ${item.price}
        </c:forEach>
    </ul>

</body>
<%@ include file="footer.jsp" %>