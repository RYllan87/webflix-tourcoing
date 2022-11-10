package fr.formation.webflix.controllers;

import fr.formation.webflix.entities.UserEntity;
import fr.formation.webflix.enums.Gender;
import fr.formation.webflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import java.util.Optional;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		// ModelAndView
		System.out.println("Je suis à l'index de mon site Webflix !!");
		return "index.html";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin/index.html";
	}
}


//		bindingResult.rejectValue("confirmPassword", "userEntity.confirmPassword", "Les mots de passes ne sont pas identiques");
