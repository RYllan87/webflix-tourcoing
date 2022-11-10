package fr.formation.webflix.controllers.admin.user;

import fr.formation.webflix.entities.UserEntity;
import fr.formation.webflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

	@Autowired
	private UserService userService;

	@RequestMapping("")
	public String adminUser(Model model) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("page", "user/index.html");
		model.addAttribute("title", " - Users List");
		return "admin/index.html";
	}

	@RequestMapping("{id}")
	public String adminUserDetail(@PathVariable("id") Long userId, Model model) {
		Optional<UserEntity> user = userService.findById(userId);
		if (user.isPresent()) {
			model.addAttribute("u", user.get());
			return "admin/user/detail.html";
		}
		return "error_404.html";
	}

	@GetMapping("add")
	public String adminUserAdd(UserEntity userEntity, Model model) {
		model.addAttribute("page",  "user/add.html");
		model.addAttribute("title",  " - User Add");
		return "admin/index.html";
	}

	//	@RequestMapping(value = "/admin/users/add", method = RequestMethod.POST)
	@PostMapping("add")
	public String adminUserAddPost(@Valid UserEntity userEntity, BindingResult bindingResult,
	                               Model model) {
		if (!userEntity.getPassword().equals(userEntity.getConfirmPassword())) {
			bindingResult.rejectValue("confirmPassword",
					"userEntity.confirmPassword",
					"Les mots de passe ne sont pas identiques");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("page",  "user/add.html");
			model.addAttribute("title",  " - User Add");
			return "admin/index.html";
		}

		userEntity.setDateCreated(Calendar.getInstance());
		userEntity.setPassword(hash(userEntity.getPassword()));

		userService.save(userEntity);
		return "redirect:/admin/users";
	}

	public String hash(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
