package com.hcl.controller;

import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.config.IAuthenticationFacade;
import com.hcl.model.Product;
import com.hcl.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
	
	@GetMapping("/display")
	public String generatePage(Model model) {
		Authentication authentication = authenticationFacade.getAuthentication();
		boolean loggedIn = authentication.isAuthenticated();
		String role = authentication.getAuthorities().toString().replace("[", "").replace("]", "");
		model.addAttribute("role", role);
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("productList",productService.getAllProducts());
		model.addAttribute(authentication);
		Iterator<Product> it = productService.getAllProducts().iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		return "display";
	}
	@GetMapping("/add")
	public String showNewForm(Model model ) {
		Product p = new Product();
		model.addAttribute("product",p);
		return "new_product";
	}
	
	@GetMapping(value = "/allProducts")
	public List<Product> getAllProducts() {
		List<Product> list = productService.getAllProducts();
		return list;
	}

	@PostMapping(value = "/insert")
	public String addEmp(@ModelAttribute Product product,@RequestParam Double price, @RequestParam int quantity) {
		double total = price*quantity;
		product.setTotal(total);
		 productService.addProduct(product);
		 return "redirect:/display";
	}

	@GetMapping(value = "/product/{id}")
	public Product findByEmpId(@PathVariable("id") Long id) throws Exception{
		return productService.getProductById(id);
	}
//	@PostMapping("/update/product/{id}")
//	public String updateProduct(@PathVariable(value = "id") Long id, Model model, @RequestBody Product product) throws Exception{
//		productService.updateProduct(id, product);
//		return "redirect:/display";
//	}

	@GetMapping("/product/update/{id}")
	public String updateProduct(@PathVariable(value = "id") Long id, Model model) throws Exception {
		Product p1 = productService.getProductById(id);
		model.addAttribute("product", p1);
		return "update_product";
	}

	@GetMapping("/product/delete/{id}") 
	public String deleteProduct(@PathVariable(value = "id") Long id) { 		 
			productService.deleteProduct(id);
			return "redirect:/display";
		}
}
