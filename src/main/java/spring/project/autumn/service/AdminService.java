package spring.project.autumn.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	public void admin(HttpServletRequest req, HttpSession ses) {
		ses.setAttribute("admin", true);
	}
	
}
