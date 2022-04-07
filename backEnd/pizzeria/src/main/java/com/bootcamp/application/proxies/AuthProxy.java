package com.bootcamp.application.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.bootcamp.application.dtos.ProfileDTO;

@FeignClient(name = "autentication-service", url = "http://localhost:8091")
public interface AuthProxy {
	@GetMapping(path = "/profile")
	ProfileDTO getUserData(@RequestHeader String authorization);
}