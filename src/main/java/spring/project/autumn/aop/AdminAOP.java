package spring.project.autumn.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdminAOP {
	
	@Pointcut("within(spring.project.autumn.service.AdminService)")
	public void pointcut() {}
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		Object[] objects = jp.getArgs();
		
		for (Object object : objects) {
			if (object instanceof HttpSession) {
				HttpSession ses = (HttpSession) object;
				ses.setAttribute("admin", false);
			}
		}
		
		for (Object object : objects) {
			if (object instanceof HttpServletRequest) {
				HttpServletRequest req = (HttpServletRequest) object;
				if (!"AC10vktpr?!@".equals(req.getParameter("admin"))) {
					return null;
				}
			}
		}
		return jp.proceed();
	}
	
}
