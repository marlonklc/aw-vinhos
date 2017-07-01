package com.algaworks.vinhos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Marlon on 01/07/2017.
 */
@RestController()
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("vinhos/dashboard");
		return mv;
	}
}
