package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.*;


@WebServlet("/address/insertCityServlet")
public class InsertCityServlet extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 넘어온 값을 확인하고 받는다
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		String city = request.getParameter("city");
		System.out.println("컨트리아이디"+countryId+" 시티"+city);
		// 메소드 사용을 위해 객체 선언한다
		cityDao = new CityDao();
		// 넘어 온 값들을  city 객체에 넣어주기 위해 객체를 선언하고 넣는다
		City c = new City();
		c.setCountry(new Country());
		c.getCountry().setCountryId(countryId);
		c.setCity(city);
		// 메소드를 실행한다
		cityDao.insertCity(c);
	}

}
