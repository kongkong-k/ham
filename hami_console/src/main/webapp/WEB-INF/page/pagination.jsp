<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="clearfix text-right">
    <%--隐藏域--%>
    <input type="hidden" id="pageNum" name="pageNum" value="${mq.pageNum}">
    <input type="hidden" id="totalPage" value="${page.totalPage}">
    <ul class="pagination no-margin">
        <li id="prev" class="disabled"><a href="#">Prev</a></li>
        <c:forEach begin="1" end="${page.totalPage}" var="myPageNum">
            <li <c:if test="${myPageNum == mq.pageNum}">class="active"</c:if>><a
                    pageNumButton href="#">${myPageNum}</a></li>
        </c:forEach>
        <li id="next"><a href="#">Next</a></li>
    </ul>
</div>
</body>
</html>
