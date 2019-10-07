package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Country;
import sakila.address.model.CountryDao;


@WebServlet("/address/InsertCountry")
public class InsertCountryServlet extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// country의 add에서 넘겨준 값을 받고 확인한다.
		String country = request.getParameter("country");
		System.out.println("country : "+country);
		// CountryDao의 인설트 메소드 실행을 위해 객체생성 및 변수를 선언한다.
		countryDao = new CountryDao();
		// country값을 객체에 넣어준다
		Country c = new Country();
		c.setCountry(country);
		// 메소드를 실행시킨다.
		countryDao.insertCountry(c);
		
	}

}
