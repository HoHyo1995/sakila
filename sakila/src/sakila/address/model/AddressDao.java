package sakila.address.model;

import java.sql.*;
import java.util.*;

import sakila.db.DBHelper;


public class AddressDao {
	// 생성자 생성
	public AddressDao() {
		
	}
	// 고객등록할떄 먼저 어드레스 등록하는 메소드
	public int insertAddress(Connection conn, String address, String phone, int cityId, String district) {
		// insert확인유무(addressId값을 리턴받아서 customer 인설트 시 사용)
		int row = 0;
		int addressId = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO address(address,phone,city_id,district, last_update)VALUES(?,?,?,?,NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, address);
			stmt.setString(2, phone);
			stmt.setInt(3, cityId);
			stmt.setString(4, district);
			row = stmt.executeUpdate();
			System.out.println("영향을 받은 행:"+row);
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				addressId = rs.getInt(1);
				System.out.println("생성된 고객의 주소 번호: "+addressId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, null);
		}
		
		return addressId;
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
