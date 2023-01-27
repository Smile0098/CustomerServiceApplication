package com.bankapp.customerservice.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.customerservice.bean.RoiRange;
import com.bankapp.customerservice.configuration.RoiConfiguration;
import com.bankapp.customerservice.customexception.BusinessException;
import com.bankapp.customerservice.customexception.ControllerException;
import com.bankapp.customerservice.model.Customer;
import com.bankapp.customerservice.proxy.AccountProxy;
import com.bankapp.customerservice.service.CustomerServices;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private RoiConfiguration roiConfiguration;

	@Autowired
	private CustomerServices customerService;

	@Autowired
	private AccountProxy accountProxy;

	@GetMapping("/get-deposit-roi")
	public RoiRange getDepositROI() {
//		return new RoiRange(2, 5);

		return new RoiRange(roiConfiguration.getMaxRoi(), roiConfiguration.getMinRoi());

	}

	@PostMapping("/add-customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {

		Customer returnedCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<Customer>(returnedCustomer, HttpStatus.CREATED);

	}

	@GetMapping("/get-all-customers")
	public List<Customer> getAllCustomer() {

		return customerService.getAllCustomers();

	}

	@GetMapping("/get-customer/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable int id) {

		Customer retrieved = customerService.getCustomer(id);
		return new ResponseEntity<Customer>(retrieved, HttpStatus.OK);

	}

	@PutMapping("/update-customer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}

	@PatchMapping("/update-customer-patch/{id}")
	public Customer updateCustomerUsingPatch(@PathVariable int id, @RequestBody Map<String, String> fields) {

		return customerService.updateCustomerUsingPatch(id, fields);
	}

	@DeleteMapping("/delete-customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id) {

		try {
			boolean bool = customerService.deleteCustomer(id);
			if (bool) {

				ResponseEntity<?> responseRetrieved = accountProxy.deleteAccount(id);
				System.out.println("Response from account " + responseRetrieved);
				if (responseRetrieved.getStatusCode() == HttpStatus.OK) {
					return new ResponseEntity<>("Customer As well as account deleted", HttpStatus.OK);
				} else {
					throw new ControllerException("610",
							"Customer deleted from customer database but No object fetched from account service");
				}
			} else {
				throw new ControllerException("612", "No customer in database with id: " + id);
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorCode(), e.getErrorMessage());
		} catch (ControllerException ex) {
			throw new ControllerException(ex.getErrorCode(), ex.getErrorMessage());
		}

	}

}
