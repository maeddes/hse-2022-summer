package de.hsesslingen.firstapp;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * A collection of hacks and bad examples - playground only!
 * 
 */

@SpringBootApplication(scanBasePackages={"de.hsesslingen"})
@RestController
public class FirstappApplication {

	List<DemoItem> items = new ArrayList<>();

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public String mainEntrypoint(){

		return "default page";
	}

	@GetMapping("/test")
	public String testMethod(){

		return "successful";
	}

	@GetMapping("/hello")
	public String sayHello(){

		return "Hello Esslingen, Summer Semester 22";
	}

	@GetMapping("/echo/{value}")
	public String echoValue(@PathVariable String value){

		return "echo "+value;
	}
	/**
	 * Usage of RequestParam, e.g. http://localhost:8090/item?name=User
	 * http://localhost:8080/person?age=60
	 */
	@GetMapping("/item")
	@ResponseBody
	public DemoItem getItem(@RequestParam(value = "name", defaultValue = "default") String name) {
		return new DemoItem(name);
	}

	/**
	 * Usage of PathVariable, e.g. http://localhost:8090/item/User
	 */
	@GetMapping("/items/{name}")
	public DemoItem getItemByName(@PathVariable String name){
		return new DemoItem(name);
	}

	/**
	 * Return list of items
	 */
	@GetMapping("/items")
	public List<DemoItem> getAllItems() {
		return items;
	}

	//@PutMapping("/items")
	//@PostMapping(consumes = "application/json", produces = "application/json")

	/**
	 * Create using PathVariable
	 */
	@PostMapping("/items/{name}")
	public DemoItem setName(@PathVariable String name) {
		DemoItem tempItem = new DemoItem(name);
		items.add(tempItem);
		return tempItem;
	}

	/**
	 * Create using RequestBody
	 */
    @PostMapping(path = "/items", consumes = "application/json", produces = "application/json")
    DemoItem createDemoItem(@RequestBody DemoItem item) {
		System.out.println("Item: "+item);
		items.add(item);
        return item;
    }

	/**
	 * Delete using PathVariable
	 * Don't delete elements from list you are iterating over

	@DeleteMapping("/items/{name}")
	public void deleteItem(@PathVariable String name){

		for (DemoItem demoItem : items) {

			if(demoItem.getName().equals(name)){

				System.out.println("Deleting: "+demoItem);
				items.remove(demoItem);
			}			
		}
	}
	/**
	 * Delete using PathVariable and Iterator
	 */

	@DeleteMapping("/items/{name}")
	public void deleteItem(@PathVariable String name){

		// Java >= 8
		items.removeIf(item -> item.getName().equals(name));
		
		// Using Iterator (Java < 8)
		//
		// Iterator<DemoItem> iterator = items.iterator();
		// while(iterator.hasNext()){
		// 	DemoItem item = iterator.next();
		// 	if(item.getName().equals(name)){ 
		// 		System.out.println("Deleting: "+item);
		// 		iterator.remove();
		// 		System.out.println("Deleted: "+item);
		// 	}

		// }
	}

	@PutMapping("/items")
	public DemoItem updateItem(@RequestBody DemoItem item){

		DemoItem returnItem = null;

		// Java >= 8
		ListIterator<DemoItem> iterator = items.listIterator();
		while(iterator.hasNext()){
			DemoItem iterItem = iterator.next();
			if(item.getName().equals(item.getName())){ 
				System.out.println("Updating: "+iterItem);
				iterator.set(item);
				returnItem = item;
				System.out.println("Updated: "+item);
			}
		}

		return returnItem;
	}



	public static void main(String[] args) {
		SpringApplication.run(FirstappApplication.class, args);
	}

}
