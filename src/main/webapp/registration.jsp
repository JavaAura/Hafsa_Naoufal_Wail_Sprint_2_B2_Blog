<%
	// Check if the session attribute "loggedInUser" is set
	if (session.getAttribute("loggedInUser") != null) {
		response.sendRedirect("index.jsp"); // Redirect to index or home page if logged in
		return; // Ensure no further processing happens after redirect
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Sign Up Form by Colorlib</title>

	<!-- Font Icon -->
	<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

	<!-- Main css -->
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="main">

	<!-- Sign up form -->
	<section class="signup">
		<div class="container">
			<div class="signup-content">
				<div class="signup-form">
					<h2 class="form-title">Sign up</h2>

					<form method="post" action="register" class="register-form" id="register-form">
						<!-- Hidden field to specify the source -->
						<input type="hidden" name="source" value="signup" /> <!-- Indicating this is a signup form -->

						<div class="form-group">
							<label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
							<input type="text" name="name" id="name" placeholder="Your Name" required />
						</div>
						<div class="form-group">
							<label for="email"><i class="zmdi zmdi-email"></i></label>
							<input type="email" name="email" id="email" placeholder="Your Email" required />
						</div>
						<div class="form-group">
							<label for="pass"><i class="zmdi zmdi-lock"></i></label>
							<input type="password" name="password" id="pass" placeholder="Password" required />
						</div>
						<div class="form-group">
							<label for="re_pass"><i class="zmdi zmdi-lock-outline"></i></label>
							<input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password" required />
						</div>
						<div class="form-group">
							<label for="birthday"><i class="zmdi zmdi-calendar"></i></label>
							<input type="date" name="birthdateStr" id="birthday" placeholder="Your Birthday" required />
						</div>
						<div class="form-group">
							<input type="checkbox" name="agree-term" id="agree-term" class="agree-term" required />
							<label for="agree-term" class="label-agree-term">
								<span><span></span></span>
								I agree all statements in <a href="#" class="term-service">Terms of service</a>
							</label>
						</div>
						<div class="form-group form-button">
							<input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
						</div>
					</form>
				</div>
				<div class="signup-image">
					<figure>
						<img src="images/signup-image.jpg" alt="sign up image">
					</figure>
					<a href="login.jsp" class="signup-image-link">I am already a member</a>
				</div>
			</div>
		</div>
	</section>
</div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>
