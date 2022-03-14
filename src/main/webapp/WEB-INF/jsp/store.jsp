<%@ include file="header.jsp" %>

<body modelAttribute="products">
<h1>Store</h1>
	<ul>
	    Product     Price <br>
        <c:forEach var="item" items="${products}">
            ${item.title}   ${item.price}
            <br>
        </c:forEach>
    </ul>

</body>
<%@ include file="footer.jsp" %>