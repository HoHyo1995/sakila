package sakila.customer.service;

import java.util.HashMap;
import java.util.Map;

import sakila.customer.model.CustomerDao;

public class CustomerService {
	private CustomerDao customerDao;
	public Map<String, Object> selectCustomerService(int currentPage){
		System.out.println("------selectCustomerService-----");
		System.out.println("curretPage = "+currentPage);
		// 리턴 값 받기 위한 Map 선언
		Map<String, Object> map = new HashMap<String, Object>();
		// Dao접근 
	return map;
		
	}
}
