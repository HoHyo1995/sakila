package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Country;
import sakila.address.model.CountryDao;


@WebServlet("/address/selectCountryList")
public class SelectCountryListServlet extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리스폰을 json으로 하겠다고 선언
		response.setContentType("application/json");
		// html에서 넘겨준 값을 받고 확인한다
		System.out.println("currentPage는??"+request.getParameter("currentPage"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// 객체 생성
		countryDao = new CountryDao();
		// 배열 생성
		List<Country> list = countryDao.selectCountryList(currentPage);
		// json객체 생성
		Gson gson = new Gson();
		// json타입으로 변경한다
		String jsonList = gson.toJson(list);
		// 응답한다
		response.getWriter().write(jsonList);
	}

}
