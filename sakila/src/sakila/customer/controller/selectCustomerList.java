package sakila.customer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.customer.service.CustomerService;


@WebServlet("/customer/selectCustomerList")
public class selectCustomerList extends HttpServlet {
	private CustomerService customerService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post-----/customer/selectCustomerList");
		System.out.println("curretPage = "+request.getParameter("currentPage"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// gson으로 리스폰한다고 선언
		response.setContentType("application/json;charset=utf-8");
		
		// 메소드 사용을 위한 객체생성
		customerService = new CustomerService();
		// 데이터를 받기위한 리스트 선언
		Map<String, Object> map = customerService.selectCustomerService(currentPage);
		// gson객체 선언
		Gson gson = new Gson();
		// gson타입으로 변경 후 스트링타입에 복사
		String jsonStr = gson.toJson(map);
		// 응답하기
		response.getWriter().write(jsonStr);
	}

}
