<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Cars CAPTCHA POC</title>
</head>
<body>
	<h1><img src="cars-logo-big.jpg" width="100" /> SIY CAPTCHA POC</h1>
	
	
	<%	if(request.getAttribute("captchaResult") != null) { %>
			<%=request.getAttribute("captchaResult")%>
	<%	} %>
	<form action="/CarsCaptcha" method="post">
		<script type="text/javascript"
			src="http://www.google.com/recaptcha/api/challenge?k=6LeN4csSAAAAAC2DyKkxS_QKsCJEE-F5TDSUbnI0"></script>
		<noscript>
			<iframe
				src="http://www.google.com/recaptcha/api/noscript?k=6LeN4csSAAAAAC2DyKkxS_QKsCJEE-F5TDSUbnI0"
				height="300" width="500" frameborder="0"></iframe>
			<br>
			<textarea name="recaptcha_challenge_field" rows="3" cols="40"></textarea>
			<input type="hidden" name="recaptcha_response_field" value="manual_challenge" />
		</noscript>
		<input type="submit" />
	</form>
</body>
</html>