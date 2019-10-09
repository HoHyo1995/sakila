package sakila.address.model;

import java.sql.*;
import java.util.*;

import sakila.db.DBHelper;

public class CityDao {
	// 생성자 생성
	public CityDao() {
	
	}
	// 데이터 추가 메소드
	public void insertCity(City c) {
		System.out.println("countryId는"+c.getCountry().getCountryId()+"city이름은"+c.getCity());
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO city(city, country_id, last_update) VALUES(?,?,NOW())";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getCity());
			stmt.setInt(2, c.getCountry().getCountryId());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
	}
	// 전체를 페이징해서 출력하는 메소드
	public List<City> selectCityList(int currentPage){
		// 리턴 값을 받기위한 동적배열을 생성 및 선언한다
		List<City> list = new ArrayList<City>();
		// 넘어 온 currentPage의 값을 확인한다
		System.out.println("Dao의 currentPage = "+currentPage);
		// 마리아DB에 사용 될 변수를 선언하고 초기화 한다
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 시작 페이지 값을 설정+페이지당 볼 갯수를 정한다
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage-1)*ROW_PER_PAGE;
		// 쿼리문을 사용한다
		String sql = "SELECT ct.city_id, ct.city, ct.country_id, c.country, ct.last_update FROM city ct INNER JOIN country c on ct.country_id = c.country_id order by city_id desc limit ?,?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCityId(rs.getInt("ct.city_id"));
				city.setCity(rs.getString("ct.city"));
				city.setCountry(new Country());
				city.getCountry().setCountryId(rs.getInt("ct.country_id"));
				city.getCountry().setCountry(rs.getString("c.country"));
				city.setLastUpdate("ct.last_update");
				list.add(city);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	// 총 행을 구하는 메소드
	public int selectCityCount() {
		int count = 0 ;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from city";
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
