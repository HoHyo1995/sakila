package sakila.customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.jndi.cosnaming.IiopUrl.Address;

import sakila.customer.model.Customer;
import sakila.customer.service.CustomerService;


@WebServlet("/customer/addCustomer")
public class AddCustomerServlet extends HttpServlet {
	private Customer customer;
	private CustomerService customerService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gson으로 리스폰한다고 선언
		response.setContentType("application/json;charset=utf-8");
		// 확인
		System.out.println("post-----/customer/addCustomer");
		// 객체생성
		customer = new Customer();
		customerService = new CustomerService();
		// 값 받기
		customer.setFirstName(request.getParameter("firstName"));
		customer.setLastName(request.getParameter("lastName"));
		customer.setStoreId(Integer.parseInt(request.getParameter("storeId")));
		customer.setEmail(request.getParameter("email"));
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String district = request.getParameter("district");
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		String postalCode = request.getParameter("postalCode");
		//  ajax로 넘긴 값 넘어왔는지 확인
		System.out.println("phone = "+phone);
		System.out.println("district = "+district);
		System.out.println("cityId = "+cityId);
		System.out.println("postalCode = "+postalCode);
		System.out.println("address = "+address);
		customer.toString();
		int checking = customerService.addCustomerService(customer, address, phone, cityId, district);
		
		
		// gson객체 선언
		Gson gson = new Gson();
		// gson타입으로 변경 후 스트링타입에 복사
		String jsonStr = gson.toJson(address);
		// 응답하기
		response.getWriter().write(jsonStr);
	}

}
