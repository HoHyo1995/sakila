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


@WebServlet("/address/selectCityListServlet")
public class SelectCityListServlet extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// json타입으로 response한다
		response.setContentType("application/json;charset=utf-8");
		// 객체를 생성한다.
		cityDao = new CityDao();
		// html에서 보내준 currentPage를 받고 확인한다.
		System.out.println("Servlet의 currentPage = "+request.getParameter("currentPage"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// 배열을 선언한다.(메소드로 리턴받을 값 저장을 위해)+ 메소드의 리턴 값을 받는다
		List<City> list = cityDao.selectCityList(currentPage);
		// json 객체를 선언한다
		Gson gson = new Gson();
		// json타입으로 변경한 후 String타입으로 복사한다
		String jsonList = gson.toJson(list);
		// 응답한다
		response.getWriter().write(jsonList);
	}

}
