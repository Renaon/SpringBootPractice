<%@page pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<h1>Add a product </h1>
<form:form action="success" modelAttribute="product" enctype="multipart/form-data">
    <form:input path="title" type='text'/>
    <br>
    <form:input path="price" type='text'/>
    <br>
	<form:input path="category" type='text' placeholder="Раздел"/>
    <br>
	<form:form action="uploadlogo" method="post" enctype="multipart/form-data">
		<p>Загрузите логотип товара</p>
		<input type="file" name="file" />
	</form:form>
    <br>
    <input type="submit" value="Submit" multiple/>
</form:form>

</body>

<%@ include file="footer.jsp" %>
