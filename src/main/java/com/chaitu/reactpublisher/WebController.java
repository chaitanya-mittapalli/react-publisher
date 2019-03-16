package com.chaitu.reactpublisher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@EnableScheduling
public class WebController {
	
	public static Map<Integer, Customer> customers = new HashMap<>();
	public static List<String> regions = new ArrayList<>();
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	static {
		customers.put(0, new Customer("1",  "Snapchat"));
		customers.put(1, new Customer("2",  "Airbnb"));
		customers.put(2, new Customer("3",  "Costco"));
		customers.put(3, new Customer("4",  "TiVo"));
		customers.put(4, new Customer("5",  "Zillow"));
		customers.put(5, new Customer("6",  "Citrix"));
		customers.put(6, new Customer("7",  "Khan Academy"));
		customers.put(7, new Customer("8",  "Atomic  Fiction"));
		customers.put(8, new Customer("9",  "Wix"));
		customers.put(9, new Customer("10", "Ubisoft"));
		customers.put(10, new Customer("1",  "Snapchat"));
		customers.put(11, new Customer("2",  "Airbnb"));
		customers.put(12, new Customer("3",  "Costco"));
		customers.put(13, new Customer("4",  "TiVo"));
		customers.put(14, new Customer("1",  "Snapchat"));
		customers.put(15, new Customer("2",  "Airbnb"));
		customers.put(16, new Customer("1",  "Snapchat"));
		regions.add("US & CANADA");
		regions.add("LATAM");
		regions.add("EMEA");
		regions.add("APAC");
	}
	
	@Autowired
	private PubSubTemplate pubSubTemplate;
	
	@Value("${gcp.topic-name}")
	private String topicName;
	
	
	@GetMapping("/customer")
	@Scheduled(cron = "*/20 * * * * ?")
	public void generateCustomer() {		
		System.out.println("********************** Started to stream Batch at " + formatter.format(new Date()) + "************************");
		Random random = new Random();
		IntStream.range(1, 301).parallel().forEach(i -> {
			Customer customer  = customers.get(random.nextInt(17));
			Transaction transaction = new Transaction();
			transaction.setTxn_id(UUID.randomUUID().toString());
			transaction.setCust_nm(customer.getCustomerName());
			transaction.setCust_nbr(customer.getCustomerNumber());
			transaction.setTxn_dt(formatter.format(new Date()));
			String region = regions.get(random.nextInt(4));
			transaction.setRegion(regions.get(random.nextInt(4)));
			switch (region) {
			case "US & CANADA":
				transaction.setRevenue_amt(new Integer(random.nextInt(700) + 1).toString());
				transaction.setNo_of_pckgs(new Integer(random.nextInt(100) +1).toString());	
				break;
			case "LATAM":
				transaction.setRevenue_amt(new Integer(random.nextInt(100) + 1).toString());
				transaction.setNo_of_pckgs(new Integer(random.nextInt(10) + 1).toString());
				break;
			case "EMEA":
				transaction.setRevenue_amt(new Integer(random.nextInt(500) + 1).toString());
				transaction.setNo_of_pckgs(new Integer(random.nextInt(50) + 1).toString());
				break;
			case "APAC":
				transaction.setRevenue_amt(new Integer(random.nextInt(200) + 1).toString());
				transaction.setNo_of_pckgs(new Integer(random.nextInt(20) + 1).toString());
				break;
			default:
				break;
			}	
			this.pubSubTemplate.publish(topicName, transaction).addCallback(s3 -> {}, e3 -> {
                System.err.println("Failed to push the transaction " + transaction + " with message: " + e3.getMessage());
            });
		});
		System.out.println("********************** Completed to Stream Batch at " + formatter.format(new Date()) +"************************");
	}
}
