package sakila.business.model;

import java.sql.*;

import sakila.db.DBHelper;

public class StoreDao {
	// 생성자 생성
	public StoreDao() {
		
	}
	// 총 행을 구하는 메소드
	public int selectStoreCount() {
		int count = 0 ;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from store";
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
