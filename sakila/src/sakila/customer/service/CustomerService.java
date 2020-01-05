package sakila.customer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.address.model.AddressDao;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.db.DBHelper;

public class CustomerService {
	private CustomerDao customerDao;
	private AddressDao addressDao;
	// 고객등록 및 주소등록서비스(고객 insert전에 주소 insert부터 실행한다)
	public int addCustomerService(Customer customer, String address, String phone, int cityId, String district) {
		System.out.println("------addCustomerService-----");
		int checking = 0;
		// 객체생성
		addressDao = new AddressDao();
		customerDao = new CustomerDao();
		// 트랜잭션
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);
			// 어드레스등록
			int addressId = addressDao.insertAddress(conn, address, phone, cityId, district);
			// 커스터머 등록
			checking = customerDao.insertCustomer(conn, customer, addressId);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				return 0;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return 0;
			}
		}finally {
			DBHelper.close(null, null, conn);
		}
		return checking;
	}
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
