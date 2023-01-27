package com.bankapp.customerservice.service;

import java.util.List;
import java.util.Map;

import com.bankapp.customerservice.customexception.BusinessException;
import com.bankapp.customerservice.model.Customer;

public interface CustomerServiceInterface {

	public Customer createCustomer(Customer customer) throws BusinessException;

	public List<Customer> getAllCustomers() throws BusinessException;

	public Customer updateCustomer(Customer customer) throws BusinessException;

	public Customer updateCustomerUsingPatch(int id, Map<String, String> fields);

	public boolean deleteCustomer(int id);

}
