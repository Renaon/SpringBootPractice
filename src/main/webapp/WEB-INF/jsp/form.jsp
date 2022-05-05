<%@page pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

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
