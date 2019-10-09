package sakila.address.model;

import java.sql.*;
import java.util.*;

import sakila.db.DBHelper;


public class AddressDao {
	// 생성자 생성
	public AddressDao() {
		
	}
	// 전체리스트 100개출력
	public List<Address> selectAddressList() {
		// 객체 생성
		List<Address> list = new ArrayList<Address>();
		// DB에 사용할 변수 선언 및  초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// limit값 넣어주기
		int limit = 100;
		// 쿼리문 스트랑타입으로
		String sql = "SELECT a.address_id, a.address, a.address2, a.district, a.city_id, ci.city, co.country, a.postal_code, a.phone, a.last_update FROM address a INNER JOIN city ci INNER JOIN country co ON a.city_id = ci.city_id AND ci.country_id = co.country_id LIMIT ?";
		// DB에 저장된 값을 불러와 객체에 저장
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Address address =new Address();
				address.setAddressId(rs.getInt("a.address_id"));
				address.setAddress(rs.getString("a.address"));
				address.setAddress2(rs.getString("a.address2"));
				address.setDistrict(rs.getString("a.district"));
				address.setCity(new City());
				address.getCity().setCityId(rs.getInt("a.city_id"));
				address.getCity().setCity(rs.getString("ci.city"));
				address.getCity().setCountry(new Country());
				address.getCity().getCountry().setCountry(rs.getString("co.country"));
				address.setPostalCode(rs.getString("a.postal_code"));
				address.setPhone(rs.getString("a.phone"));
				address.setLastUpdate(rs.getString("a.last_update"));
				list.add(address);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
		}
	// 총 행의 수를 구하는 메소드
	public int selectAddressCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from address";
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
