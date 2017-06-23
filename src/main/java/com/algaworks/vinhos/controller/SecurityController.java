package com.algaworks.vinhos.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marlon on 22/06/2017.
 */
@Controller
public class SecurityController {

	@RequestMapping("/login")
	public String login(@AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			return "redirect:/vinhos";
		}
		return "login";
	}
}
