package sakila.address.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.*;


@WebServlet("/address/selectCountryListAll")
public class SelectCountryListAllServlet extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// json타입으로 넘길거라고 선언
		response.setContentType("application/json;charset=utf-8");
		// Dao메소드를 사용하기위해 객체생성
		countryDao = new CountryDao();
		// 리턴 값을 받기 위해 메소드 실행 및 변수선언해서 복사
		List<Country> list = countryDao.selectCountryListAll();
		// json객체선언
		Gson gson = new Gson();
		// json타입으로변경
		String jsonStr = gson.toJson(list);
		// 리스폰한다
		response.getWriter().write(jsonStr);
		
	}

}
