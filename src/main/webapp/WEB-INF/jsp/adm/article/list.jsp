<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../part/mainLayoutHead.jspf"%>
<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">
	<div>
		<select class="py-2 mb-5 select-board-id">
			<option value="1">공지사항</option>
			<option value="2">자유</option>
		</select>
		<script>
			$('.section-1 .select-board-id').val(param.boardId);
			$('.section-1 .select-board-id').change(function(){
				location.href = "list?boardId=" + this.value;
			})		
		</script>
	</div>
		<c:forEach items="${articles}" var="article">
			<div class="flex justify-between items-center">
				<span class="font-light text-gray-600">${article.regDate}</span>
				<a href="list?boardId=${article.boardId}" class="px-2 py-1 bg-gray-600 text-gray-100 font-bold rounded hover:bg-gray-500">${article.extra__boardName}</a>
			</div>
			<div class="mt-2">
				<a href="detail?id=${article.id}" class="text-2xl text-gray-700 font-bold hover:underline">${article.title}</a>
				<p class="mt-2 text-gray-600">${article.body}</p>
			</div>
			<div class="flex justify-between items-center mt-4 mb-16">
				<a href="detail?id=${article.id}" class="text-blue-500 hover:underline">자세히 보기</a>
				<div>
					<a href="detail?id=${article.id}" class="flex items-center">
						<img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAxNzA0MTNfMTk4%2FMDAxNDkyMDg3MjMyNzI4.vaL1AYJg8d9-g9IKaWTn50Ny4U8k4CdNiqI5QLp0A6wg.F6uFaUucl-cKaC5JZft5XjNgKcK1nKszjNbAea9aEa8g.JPEG.andyhan05%2F1492086240207.jpg&type=sc960_832" alt="defaultImage" class="mx-4 w-10 h-10 object-cover rounded-full" />
						<h1 class="text-gray-700 font-bold hover:underline">${article.extra__writer}</h1>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
<%@ include file="../part/mainLayoutFoot.jspf"%>