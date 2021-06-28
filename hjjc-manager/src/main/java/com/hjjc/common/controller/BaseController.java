package com.hjjc.common.controller;

import org.springframework.stereotype.Controller;

import com.hjjc.common.utils.ShiroUtils;
import com.hjjc.system.domain.UserDO;
import com.hjjc.system.domain.UserToken;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}