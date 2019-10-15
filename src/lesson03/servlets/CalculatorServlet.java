package lesson03.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


//@WebServlet("/wowcalc")
//@WebServlet(urlPatterns= {"/calc","calc.do","calc.action"})
@WebServlet(urlPatterns= {"/calc3","/calc4","/calc5.do"})
public class CalculatorServlet extends GenericServlet {

	public CalculatorServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int a=Integer.parseInt(arg0.getParameter("a"));
		int b=Integer.parseInt(arg0.getParameter("b"));
		
		arg1.setContentType("text/plain");
		arg1.setCharacterEncoding("UTF-8");
		PrintWriter writer = arg1.getWriter();
		writer.println("a=" + a + ",b=" + b + "의 계산결과입니다.");
		writer.println("a+b=" + (a+b));
		writer.println("a-b=" +(a-b));
		writer.println("a*b=" + (a*b));
		writer.println("a/b=" + (a/b));
		writer.println("a%b=" + (a%b));
		//writer.println("");
		//writer.println("");
		
	}

}
