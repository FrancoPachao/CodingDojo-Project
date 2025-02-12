package com.outsidethebox.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.outsidethebox.project.models.User;
import com.outsidethebox.project.services.OrderService;
import com.outsidethebox.project.utils.ModelUtils;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	/*
	 * @Autowired private UserService serv;
	 */
	@Autowired
	private OrderService  os;

	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp != null) {
			Integer pendingReviews = os.getOrdersWithoutReview(userTemp.getId()).size();
			model.addAttribute("pendingReviews", pendingReviews);
		}
		

		ModelUtils.setupModel(userTemp, model, "Inicio", "/public/inicio.jsp");
		
		return "index.jsp";
	}

	@GetMapping("/nosotros")
	public String nosotros(Model model, HttpSession session) {
		User userTemp = (User) session.getAttribute("userInSession");

		ModelUtils.setupModel(userTemp, model, "Nosotros", "/public/nosotros.jsp");

		return "index.jsp";
	}

	@GetMapping("/ayuda")
	public String ayuda(Model model, HttpSession session) {
		User userTemp = (User) session.getAttribute("userInSession");

		ModelUtils.setupModel(userTemp, model, "Preguntas frecuentes", "/public/faq.jsp");

		return "index.jsp";
	}
}
