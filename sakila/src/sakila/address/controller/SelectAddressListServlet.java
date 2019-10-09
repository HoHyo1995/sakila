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


@WebServlet("/address/selectAddressListServlet")
public class SelectAddressListServlet extends HttpServlet {
	private AddressDao addressDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gson으로 리스폰한다고 선언
		response.setContentType("application/json;charset=utf-8"); 
		// 메소드 사용을 위한 객체생성 
		addressDao = new AddressDao();
		// 동적 배열 생성 및 메소드의 리턴값을 받는다.
		List<Address> list = addressDao.selectAddressList();
		// gson객체 선언
		Gson gson = new Gson();
		// gson타입으로 변경 후 스트링타입에 복사
		String jsonStr = gson.toJson(list);
		// 응답하기
		response.getWriter().write(jsonStr);
	}

}
