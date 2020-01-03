package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.City;
import sakila.address.model.CityDao;

// customer추가에서 select를 뛰우기 위한 서블렛
@WebServlet("/address/selectPickCityList")
public class SelectPickCityListServlet extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// json타입으로 response한다
		response.setContentType("application/json;charset=utf-8");
		System.out.println("-------------selectPickCityListServlet-----------");
		System.out.println(request.getParameter("countryNum"));
		int countryNum= Integer.parseInt(request.getParameter("countryNum"));
		cityDao = new CityDao();
		List<City> list = cityDao.selectPickCityList(countryNum);
		System.out.println(list);
		// gson객체 선언
		Gson gson = new Gson();
		// gson타입으로 변경 후 스트링타입에 복사
		String jsonStr = gson.toJson(list);
		// 응답하기
		response.getWriter().write(jsonStr);
	}

}
