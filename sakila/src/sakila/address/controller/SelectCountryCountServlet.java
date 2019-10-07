package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.*;


@WebServlet("/address/selectCountryCount")
public class SelectCountryCountServlet extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리턴 타입을 json타입으로 보내겠다고 선언
		response.setContentType("application/json");
		// 객체 생성
		countryDao = new CountryDao();
		// 카운트값 메소드 실행시킨 후 변수선언 후 받는다
		int count = countryDao.selectCountryCount();
		// json 객체 생성
		Gson gson = new Gson();
		// json타입으로 변경
		String jsonCount = gson.toJson(count);
		// response하기
		response.getWriter().write(jsonCount);
	}

}
