package sakila.address.model;

import java.sql.*;
import java.util.*;

import sakila.db.DBHelper;

public class CountryDao {
	// 생성자 생성
	public CountryDao() {
		
	}
	// 전체리스트를 페이징하면서 출력하는 메소드
	public List<Country> selectCountryList(int currentPage){
		// 배열 생성
		List<Country> list = new ArrayList<Country>();
		// DB에 필요한 변수 선언 및 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 페이지당 행의 수와 시작지점 변수 선언
		final int ROW_PER_PAGE= 10;
		int beginRow = (currentPage-1)*ROW_PER_PAGE;
		// 쿼리문 String타입으로 선언
		String sql = "select country_id, country, last_update from country order by country_id desc limit ?,?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country c = new Country();
				c.setCountryId(rs.getInt("country_id"));
				c.setCountry(rs.getString("country"));
				c.setLastUpdate(rs.getString("last_update"));
				list.add(c);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	// country를 추가하는 메소드
	public void insertCountry(Country country) {
		System.out.println("country의 값은? "+country.getCountry());
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into country(country,last_update) values(?,now())";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, country.getCountry());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
	}
	// 총 행을 구하는 메소드
	public int selectCountryCount() {
		int count = 0 ;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from country";
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
