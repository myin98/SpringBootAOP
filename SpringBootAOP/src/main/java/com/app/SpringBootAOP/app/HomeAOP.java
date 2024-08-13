package com.app.SpringBootAOP.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.app.SpringBootAOP.dto.HomeDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class HomeAOP {

	
	@Pointcut("within(com.app.SpringBootAOP.controller.HomeController)")
	public void pointcut() {
		
	}
	
	@Pointcut("bean(*Controller)")
	public void pointcut2() {
		
	}
	
	@Before("pointcut2()")
	public void before(JoinPoint jp) {
		log.info("before() : {}", jp.getSignature());
		
		
	}
	
	@AfterReturning(pointcut = "pointcut()", returning = "returnValue")
	public void afterReturning(JoinPoint jp, Object returnValue) {
		log.info("afterReturning() : {}", returnValue);
	}
	
	@AfterThrowing(pointcut = "pointcut()", throwing = "exception")
	public void afterThrowing(JoinPoint jp, Exception exception) {
		log.info("afterThrowing() : {}", exception.getMessage());
	}
	
	@After("pointcut()")
	public void after(JoinPoint jp) {
		log.info("after() : {}", jp.getSignature());
	}
	
	@Around("pointcut2()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		log.info("around()");
		
		//log.info("Model : {}", model);
		
		String name = jp.getSignature().getName();
				log.info("name : {}",name);
				
		Object[] objs = jp.getArgs();
		for(Object obj : objs) {
			log.info("파라미터 : {} ",obj);
			if(obj instanceof HttpServletRequest) {
				log.info("요청 객체");
				HttpServletRequest req = (HttpServletRequest) obj;
				log.info("파라미터 값 : {}", req.getParameter("msg"));
				return "AOP : " + req.getParameter("msg");
			}
			if(obj instanceof Model) {
				Model model = (Model) obj;
				model.addAttribute("msg","model 값 입니다.");
			}
			if(obj instanceof HomeDTO) {
				HomeDTO homeDTO = (HomeDTO) obj;
				homeDTO.setMsg("DTO 값 입니다.");
			}
		}
		//return "AOP 값입니다.";
		return jp.proceed();
	}
	
	
}
