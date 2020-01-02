package sakila.customer.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.db.DBHelper;

public class CustomerService {
	private CustomerDao customerDao;
	// 고객리스트 출력 서비스
	public Map<String, Object> selectCustomerService(int currentPage){
		System.out.println("------selectCustomerService-----");
		System.out.println("curretPage = "+currentPage);
		// 객체생성
		customerDao = new CustomerDao();
		// 페이징 작업
		int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
		int totalCount = customerDao.selectCustomerCount();
		System.out.println("totalCount --> "+totalCount);
		int lastPage = 0;
		if(totalCount % rowPerPage == 0) {
			lastPage = totalCount/rowPerPage;
		} else {
			lastPage = (totalCount/rowPerPage)+1;
		}
		// 리턴 값 받기 위한 Map 선언
		Map<String, Object> map = new HashMap<String, Object>();
		// Dao
		List<Customer> list = customerDao.selectCustomerList(beginRow, rowPerPage);
		// map에 담기
		map.put("list", list);
		map.put("currentPage", currentPage);
		map.put("lastPage", lastPage);
	return map;
		
	}
}
