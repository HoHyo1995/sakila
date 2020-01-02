package sakila.customer.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sakila.address.model.Address;
import sakila.db.DBHelper;

public class CustomerDao {
	// 생성자 생성
	public CustomerDao() {
		
	}
	// 리스트 출력
	public List<Customer> selectCustomerList(int currentPage) {
		System.out.println("------selectCustomerDao-----");
		System.out.println("curretPage = "+currentPage);
		// 리턴받을 리스트 배열 생성
		List<Customer> list = new ArrayList<Customer>();
		// DB에 사용할 변수 선언 및  초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.customer_id, c.store_id, CONCAT(c.first_name,' ',c.last_name) AS NAME, c.email, a.address, left(c.last_update,10) AS last_update, left(c.create_date,10) AS create_date\r\n" + 
				"FROM customer c INNER JOIN address a\r\n" + 
				"ON c.address_id = a.address_id";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt("c.customer_id"));
				customer.setStoreId(rs.getInt("c.store_id"));
				// concat사용으로 firstName에 성 이름으로 집어넣음
				customer.setFirstName(rs.getString("NAME"));
				customer.setEmail(rs.getString("c.email"));
				customer.setAddress(new Address());
				customer.getAddress().setAddress(rs.getString("a.address"));
				customer.setLastUpdate(rs.getString("last_update"));
				customer.setCreateDate(rs.getString("create_date"));
				list.add(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	// 총 행을 구하는 메소드
	public int selectCustomerCount() {
		int count = 0 ;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from customer";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return count;
	}
}
