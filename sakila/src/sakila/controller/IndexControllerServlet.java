package sakila.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.*;
import sakila.business.model.*;
import sakila.customer.model.*;
import sakila.inventory.model.*;


@WebServlet("/indexControllerServlet")
public class IndexControllerServlet extends HttpServlet {
	// 객체 변수 생성
	private CountryDao countryDao;
	private CityDao cityDao;
	private AddressDao addressDao;
	private CustomerDao customerDao;
	private StoreDao storeDao;
	private StaffDao staffDao;
	private PaymentDao paymentDao;
	private RentalDao rentalDao;
	private CategoryDao categoryDao;
	private FilmCategoryDao filmCategoryDao;
	private FilmDao filmDao;
	private LanguageDao languageDao;
	private ActorDao actorDao;
	private FilmActorDao filmActorDao;
	private InventoryDao inventoryDao;
	private FilmTextDao filmTextDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리턴타입을 json타입으로 할꺼고 json안에 한글은 utf8로 처리 하겠다.
		response.setContentType("application/json;charset=utf-8");
		
		// 객체생성
			countryDao = new CountryDao();
			cityDao = new CityDao();
			addressDao = new AddressDao();
			customerDao = new CustomerDao();
			storeDao = new StoreDao();
			staffDao = new StaffDao();
			paymentDao = new PaymentDao();
			rentalDao = new RentalDao();
			categoryDao = new CategoryDao();
			filmCategoryDao = new FilmCategoryDao();
			filmDao = new FilmDao();
			languageDao = new LanguageDao();
			actorDao = new ActorDao();
			filmActorDao = new FilmActorDao();
			inventoryDao = new InventoryDao();
			filmTextDao = new FilmTextDao();
			
			// 여러가지 값을 넣기위한 map생성
			Map<String, Integer> map = new HashMap<String, Integer>();
			
			int countryRow = countryDao.selectCountryCount();
			map.put("countryRow", countryRow);
			
			int cityRow = cityDao.selectCityCount();
			map.put("cityRow", cityRow);
			
			int addressRow = addressDao.selectAddressCount();
			map.put("addressRow", addressRow);
			
			int customerRow = customerDao.selectCustomerCount();
			map.put("customerRow", customerRow);
			
			int storeRow = storeDao.selectStoreCount();
			map.put("storeRow", storeRow);
			
			int staffRow = staffDao.selectStaffCount();
			map.put("staffRow", staffRow);
			
			int paymentRow = paymentDao.selectPaymentCount();
			map.put("paymentRow", paymentRow);
			
			int rentalRow = rentalDao.selectRentalCount();
			map.put("rentalRow", rentalRow);
			
			int categoryRow = categoryDao.selectCategoryCount();
			map.put("categoryRow", categoryRow);
			
			int filmCategoryRow = filmCategoryDao.selectFilmCategoryCount();
			map.put("filmCategoryRow", filmCategoryRow);
			
			int filmRow = filmDao.selectFilmCount();
			map.put("filmRow", filmRow);
			
			int languageRow = languageDao.selectLanguageCount();
			map.put("languageRow", languageRow);
			
			int actorRow = actorDao.selectActorCount();
			map.put("actorRow", actorRow);
			
			int filmActorRow = filmActorDao.selectFilmActorCount();
			map.put("filmActorRow", filmActorRow);
			
			int inventoryRow = inventoryDao.selectInventoryCount();
			map.put("inventoryRow", inventoryRow);
			
			int filmTextRow = filmTextDao.selectFilmTextCount();
			map.put("filmTextRow", filmTextRow);
			
			// json을 사용하기 위한 객체 선언
			Gson gson = new Gson();
			// map의 값을 json타입으로 바꾸는 메소드
			String jsonStr = gson.toJson(map);
			// 응답해주는 메소드
			response.getWriter().write(jsonStr);
	}

}
