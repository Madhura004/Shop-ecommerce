package com.shopify.app.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopify.app.entity.Product;
import com.shopify.app.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/create")
	public ResponseEntity<String> saveProduct(@RequestParam("productTitle") String productTitle,
			@RequestParam("originalPrice") double originalPrice,
			@RequestParam("discountPercentage") int discountPercentage, @RequestParam("description") String description,
			@RequestParam("productQuantity") int productQuantity,
			@RequestParam("productCategory") String productCategory, @RequestParam("image") MultipartFile image)
			throws IOException {

		try {

			service.saveProduct(productTitle, originalPrice, discountPercentage, description, productQuantity,
					productCategory, image);
			return ResponseEntity.ok("Product saved successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed To save product");
		}

	}

	@GetMapping
	public List<Product> getAllProducts() {
		List<Product> list = null;
		try {

			list = service.getAllProducts();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@GetMapping("/get")
	public ResponseEntity<Product> getProductByProductId(@RequestParam("id") long id) {
		Product product = null;
		try {

			product = service.getProductByProductId(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(product);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(@RequestParam("id") long id,
			@RequestParam("productTitle") String productTitle, @RequestParam("originalPrice") double originalPrice,
			@RequestParam("discountPercentage") int discountPercentage, @RequestParam("description") String description,
			@RequestParam("productQuantity") int productQuantity,
			@RequestParam("productCategory") String productCategory, @RequestParam("image") MultipartFile image)
			throws IOException {

		try {

			service.updateProduct(id, productTitle, originalPrice, discountPercentage, description, productQuantity,
					productCategory, image);
			return ResponseEntity.ok("Product updated successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed To updated product");
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteProduct(@RequestParam("id") long id) {
		try {

			service.deleteProduct( id ) ; 
			return ResponseEntity.ok("Product deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed To delete product");
		}
	}

}
