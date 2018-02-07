package models;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.Constraints;

public class Product {
	
	static List<Product> products;
	
	static {
		products = new ArrayList<Product>();
		products.add(new Product("1111","pap1","papDesc1"));
		products.add(new Product("2222","pap2","papDesc2"));
		products.add(new Product("3333","pap3","papDesc3"));
		products.add(new Product("4444","pap4","papDesc4"));
		products.add(new Product("5555","pap5","papDesc5"));

	}
	@Constraints.Required
	public String ean;
	@Constraints.Required
	public String name;
	public String description;
	
	public Product() {}
	
	public Product (String ean, String name, String description) {
		this.ean= ean;
		this.name = name;
		this.description=description;
		
	}
	
	public String toString() {
		return String.format("%s-%s", ean, name);
		
	}
	
	public static List<Product> findAll() {
		return new ArrayList<Product>(products);
	}
	
	public static Product findByEan(String ean) {
		for (Product candidate : products) {
			if (candidate.ean.equals(ean)) {
				return candidate;
			}
		}
		return null;
	}
	
	public static List<Product> findByName(String term){
		final List<Product> results=new ArrayList<Product>();
		for (Product candidate:products) {
			if (candidate.name.toLowerCase().contains(term.toLowerCase())) {
				results.add(candidate);
			}
		}
		return results;
	}
	public static boolean remove(Product product) {
		return products.remove(product);
		
	}
	
	public void save() {
		products.remove(findByEan(this.ean));
		products.add(this);
				
	}
	
}
