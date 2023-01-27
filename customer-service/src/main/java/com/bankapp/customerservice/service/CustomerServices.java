package com.bankapp.customerservice.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.bankapp.customerservice.customexception.BusinessException;
import com.bankapp.customerservice.model.Customer;
import com.bankapp.customerservice.repository.CustomerRepository;
import com.bankapp.customerservice.utility.ErrorCodesConstants;
import com.bankapp.customerservice.utility.ErrorDescriptionConstants;

@Service
public class CustomerServices implements CustomerServiceInterface {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) throws BusinessException {

		try {
			Customer getCustomer = customerRepository.findByFirstNameAndLastName(customer.getFirstName(),
					customer.getLastName());
			if (getCustomer == null) {
				return customerRepository.save(customer);
			} else {
				throw new BusinessException(ErrorCodesConstants.six_zero_three,
						ErrorDescriptionConstants.Customer_Present);
			}
		} catch (BusinessException e) {
			throw new BusinessException(ErrorCodesConstants.six_zero_three, ErrorDescriptionConstants.Customer_Present);
		} catch (Exception e) {
			throw new BusinessException(ErrorCodesConstants.seven_zero_one,
					ErrorDescriptionConstants.seven_zero_one_code_desc);
		}
	}

	@Override
	public List<Customer> getAllCustomers() throws BusinessException {
		// TODO Auto-generated method stub

		try {
			List<Customer> customersList = customerRepository.findAll();
			if (customersList.isEmpty()) {
				throw new BusinessException(ErrorCodesConstants.six_zero_four,
						ErrorDescriptionConstants.six_zero_four_code_desc);
			}
			return customersList;
		} catch (BusinessException e) {
			throw new BusinessException(ErrorCodesConstants.six_zero_four,
					ErrorDescriptionConstants.six_zero_four_code_desc);
		} catch (Exception e) {
			throw new BusinessException(ErrorCodesConstants.seven_zero_two,
					ErrorDescriptionConstants.seven_zero_two_code_desc);
		}
	}

	public Customer getCustomer(int id) throws BusinessException {

		try {
			return customerRepository.findById(id)
					.orElseThrow(() -> new NoSuchElementException("No user present with ID: " + id));
		} catch (Exception e) {
			throw new BusinessException(ErrorCodesConstants.seven_zero_three,
					ErrorDescriptionConstants.seven_zero_three_code_desc);
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) throws BusinessException {

		int id = customer.getId();
		if (customerRepository.findById(id).isPresent()) {
			customerRepository.save(customer);
			return customerRepository.save(customer);
		} else
			throw new BusinessException(ErrorCodesConstants.six_zero_five,
					ErrorDescriptionConstants.six_zero_five_code_desc);

	}

	@Override
	public Customer updateCustomerUsingPatch(int id, Map<String, String> fields) throws BusinessException {

		try {
			Optional<Customer> customerObject = customerRepository.findById(id);
			if (customerObject.isPresent()) {
				fields.forEach((key, value) -> {
					Field field = ReflectionUtils.findField(Customer.class, key);
					System.out.println("Field: " + field);
					field.setAccessible(true);
					ReflectionUtils.setField(field, customerObject.get(), value);
				});

				return customerRepository.save(customerObject.get());
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new BusinessException(ErrorCodesConstants.seven_zero_six,
					ErrorDescriptionConstants.seven_zero_six_code_desc);
		}
	}

	@Override
	public boolean deleteCustomer(int id) {

		try {
			Optional<Customer> customer = customerRepository.findById(id);
			if (customer.isPresent()) {
				customerRepository.delete(customer.get());
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BusinessException(ErrorCodesConstants.seven_zero_seven,
					ErrorDescriptionConstants.seven_zero_seven_code_desc);
		}

	}

}
