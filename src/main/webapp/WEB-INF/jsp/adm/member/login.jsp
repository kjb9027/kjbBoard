<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.0.4/tailwind.min.css" />
</head>
<body>
	<script type="text/javascript">
		const login_checkAndSubmitDone = false;
		function login_checkAndSubmit(form) {
			if (login_checkAndSubmitDone) {
				return false;
			}
			form.loginId.value = form.loginId.value.trim();
			if (form.loginId.value.length == 0) {
				alert('아이디를 입력해주세요');
				form.loginId.focus();

				return false;
			}
			if (form.loginPw.value.length == 0) {
				alert('비밀번호를 입력해주세요');
				form.loginPw.focus();

				return false;
			}
			form.submit();
			login_checkAndSubmitDone = ture;
		}
	</script>
	<section class="section-login">
		<div class="container mx-auto">
			<form action="doLogin" method="post"
				onsubmit="login_checkAndSubmit(this); return false;">
				<div class="flex">
					<div class="p-4 w-36">
						<span>아이디</span>
					</div>
					<div class="flex-grow p-4">
						<input class="w-full" type="text" name="loginId"
							placeholder="아이디를 입력해주세요" maxlength="20" autofocus="autofocus"><br>
					</div>
				</div>
				<div class="flex">
					<div class="p-4 w-36">
						<span>비밀번호</span>
					</div>
					<div class="flex-grow p-4">
						<input class="w-full" type="password" name="loginPw"
							placeholder="비밀번호를 입력해주세요" maxlength="20">
					</div>
				</div>
				<div class="flex">
					<div class="p-4 w-36">
						<span>로그인</span>
					</div>
					<div class="flex-grow p-4">
						<input class="w-full" type="submit" value="로그인">
					</div>
				</div>
			</form>
		</div>
	</section>

</body>
</html>