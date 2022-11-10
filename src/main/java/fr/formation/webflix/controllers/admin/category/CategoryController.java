package fr.formation.webflix.controllers.admin.category;

import fr.formation.webflix.entities.CategoryEntity;
import fr.formation.webflix.repositories.CategoryRepository;
import fr.formation.webflix.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public String index(@ModelAttribute("category") CategoryEntity c, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("page", "category/index");
        return "admin/index";
    }

    @PostMapping("")
    public String formPost(@ModelAttribute("category") @Valid CategoryEntity c, BindingResult bindingResult, Model model) {
        Optional<CategoryEntity> h = categoryRepository.findByName(c.getName());
        if (h.isPresent()) {
            bindingResult.rejectValue("name", "category.name", "Cette catégorie existe déjà");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("page", "category/index");
            return "admin/index";
        }

        categoryService.save(c);
        return "redirect:/admin/categories";
    }

    @GetMapping("{id}/delete")
    public String formPost(@PathVariable Long id, Model model) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }
}
