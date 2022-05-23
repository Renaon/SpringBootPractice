<%@page pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<h1>Add a product </h1>
	<form method="POST" enctype="multipart/form-data" action="/upload">
        File to upload: <input type="file" name="file"><br />
			<input type="submit" value="Upload">
    </form>
<form:form method="POST" action="success" modelAttribute="product" enctype="multipart/form-data">
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
