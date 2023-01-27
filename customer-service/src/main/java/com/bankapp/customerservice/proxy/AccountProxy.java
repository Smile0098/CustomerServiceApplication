package com.bankapp.customerservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acount-service")
public interface AccountProxy {

	@DeleteMapping("/account/delete-account/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable int id);

}
