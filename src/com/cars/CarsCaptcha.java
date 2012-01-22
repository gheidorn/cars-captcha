package com.cars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarsCaptcha extends HttpServlet {

	private static final Logger log = Logger.getLogger(CarsCaptcha.class.getName());

	private static final long serialVersionUID = 7351698281412798594L;
	private static final String CAPTCHA_API_URL = "http://www.google.com/recaptcha/api/verify";
	private static final String CAPTCHA_PRIVATE_KEY = "6LeN4csSAAAAABJ59Aecl3oAZzAYT-B1Xqeux1vC";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recaptchaChallengeField = URLEncoder.encode((String) req.getParameter("recaptcha_challenge_field"), "UTF-8");
		String recaptchaResponseField = URLEncoder.encode((String) req.getParameter("recaptcha_response_field"), "UTF-8");
		String ip = URLEncoder.encode(req.getRemoteAddr(), "UTF-8");
		try {
			String queryParms = String.format("privatekey=%s&remoteip=%s&challenge=%s&response=%s", URLEncoder.encode(CAPTCHA_PRIVATE_KEY, "UTF-8"), ip, recaptchaChallengeField,
					recaptchaResponseField);
			InputStream response = new URL(CAPTCHA_API_URL + "?" + queryParms).openStream();
			InputStreamReader is = new InputStreamReader(response);
			BufferedReader br = new BufferedReader(is);
			String captchaResult = br.readLine();
			if (!"true".equals(captchaResult))
				captchaResult = br.readLine();
			req.setAttribute("captchaResult", captchaResult);
		} catch (MalformedURLException e) {
			log.severe("malformed url exception: " + CAPTCHA_API_URL);
			log.severe("malformed url exception: " + e.getMessage());
		} catch (IOException e) {
			log.severe("IOException: " + e.getMessage());
		}

		getServletContext().getRequestDispatcher("/captcha.jsp").forward(req, resp);
	}

}
