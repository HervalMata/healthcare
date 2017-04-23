package com.healthcare.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.model.response.Response;
import com.healthcare.service.AdminService;

@CrossOrigin
@RestController(value = "AdminRestAPI")
@RequestMapping(value = "/api/admin")
public class AdminController extends AbstractBasedAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminService adminService;

	// @RequestMapping("/hello")
	// public String login() {
	// Admin admin = adminService.getUser("admin");
	// logger.debug("==debug===" + admin.getEmail());
	// return "login:" + admin.getEmail();
	// }

	@RequestMapping(value = "/get_email", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody Response getEmailByUsername(@RequestParam("driver_id") String username) {
		//return adminService.getUser("admin");
		return null;
	}
}
