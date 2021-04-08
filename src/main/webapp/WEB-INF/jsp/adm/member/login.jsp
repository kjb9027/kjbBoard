<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 모바일에서 사이트가 PC에서의 픽셀크기 기준으로 작동하게 하기(반응형 하려면 필요) -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Insert title here</title>
</head>
<!-- 제이쿼리 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 테일윈드 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.1.1/tailwind.min.css">
<!-- 폰트 어썸 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="/resources/css/common.css" />
<script type="text/javascript">
	const login_checkAndSubmitDone = false;
	function login_checkAndSubmit(form) {
		//더블클릭 방지
		if (login_checkAndSubmitDone) {
			return;
		}
		//빈칸 방지
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();

			return;
		}
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();

			return;
		}
		//조건에 걸리지 않으면 submit
		form.submit();
		login_checkAndSubmitDone = ture;
	}
</script>
<body>
	<section class="section-login h-screen">
		<div class="container mx-auto h-full flex items-center justify-center">
			<div class="bg-white w-full shadow-md rounded px-8 pt-6 pb-8 md-4">
				<div class="logo-bar flex justify-center mt-3">
					<a href="#" class="logo"> <span><i
							class="fas fa-book-open"></i></span> <span>KJBBOARD</span>
					</a>
				</div>
				<form class="" action="doLogin" method="post"
					onsubmit="login_checkAndSubmit(this); return false;">
					<div class="flex flex-col md-4 md:flex-row">
						<div class="p-1 md:w-36 md:flex md:items-center">
							<span>아이디</span>
						</div>
						<div class="p-1 flex-grow">
							<input
								class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker"
								type="text" name="loginId" placeholder="아이디를 입력해주세요"
								maxlength="20" autofocus="autofocus"><br>
						</div>
					</div>
					<div class="flex flex-col md-4 md:flex-row">
						<div class="p-1 md:w-36 md:flex md:items-center">
							<span>비밀번호</span>
						</div>
						<div class="p-1 flex-grow">
							<input
								class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker"
								type="password" name="loginPw" placeholder="비밀번호를 입력해주세요"
								maxlength="20">
						</div>
					</div>
					<div class="flex flex-col md-4 md:flex-row">
						<div class="p-1 md:w-36 md:flex md:items-center">
							<span>로그인</span>
						</div>
						<div class="p-1 flex-grow">
							<input
								class="btn-primary bg-blue-500 hover:bg-blue-dark text-white font-bold py-2 px-4 rounded w-full"
								type="submit" value="로그인">
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
</html>