package com.springcloud.main.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.springcloud.main.entity.User;
import com.springcloud.main.repository.UserRepository;

import reactor.core.publisher.Mono;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	
	
	public List getAllProducts() {
		System.out.println("Finding All products:"+userRepository.findAll());
		return userRepository.findAll();
	}

	public User addUser(User user) {
		user.setRole("regular");
		return  userRepository.save(user);
	}

	@HystrixCommand(fallbackMethod = "getProductByCodeFallBack", 
		commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
	})
	public  User getUserByUserId(Integer id) {
		/*
		 * try { Thread.sleep(3500); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// TODO Auto-generated method stub
	//	User tempProduct = productRepository.findByProductCode(code);
		//System.out.println("Temporary Product: " + tempProduct.toString());
		//http://localhost:8080/shop/inventory/P001
		String url = "http://localhost:8080/shop/inventory/"+id ;
		//String url = "http://inventory-microservice/shop/inventory/"+code ;
		//ResponseEntity<InventoryResponse> inventoryResponse=restTemplate.getForEntity(url, InventoryResponse.class );


		System.out.println("Response received from inventory micro service");
		/*
		 * if(inventoryResponse.getStatusCode() == HttpStatus.OK) {
		 * if(inventoryResponse.getBody().getQuantity() > 0) {
		 * tempProduct.setStockStatus(true); productRepository.save(tempProduct); }else
		 * { tempProduct.setStockStatus(false); productRepository.save(tempProduct); }
		 */
		Optional<User> u = userRepository.findById(new Long(id));
		return u.get();
	}
	
	
	public User getProductByCodeFallBack(Integer id) {
		System.out.println("#############Fallback called#######################");
		return null;
	}
	

}
