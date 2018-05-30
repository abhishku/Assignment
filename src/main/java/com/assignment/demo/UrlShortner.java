package com.assignment.demo;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

public class UrlShortner {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BASE = ALPHABET.length();
	public static String urlShorten(Long num) {
		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			sb.append(ALPHABET.charAt((int) (num % BASE)));
			num /= BASE;
		}
		return sb.reverse().toString();
	}
	public static String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return principal.getName();
	}
}

