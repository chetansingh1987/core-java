package com.practice.practical;

import static java.util.Optional.of;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class StreamPractical {

	@Test
	public void  test1() {
		List<Customer> customerList = new Repo().getCustomerList();
		customerList.stream().forEach(c->System.out.println(getDiscountLine(c)));
	}

	public String getDiscountLine(Customer customer) {
		return customer.getMemberCard().map(card->getDiscountPercentage(card))
									   .map(d->"Discount%: " +d)
									   .orElse("NA");
		//return getDiscountPercentage(customer.getMemberCard()).map(d->"Discount%: " + d).orElse("NA"); //StreamPractical.map.ma"Discount%: " + getDiscountPercentage(customer.getMemberCard());
	}
	
	public Optional<Integer> getDiscountPercentage(MemberCard card) {
			if (card.getFidelityPoints() >= 100) {
				return of(5);
			}
			if (card.getFidelityPoints() >= 50) {
				return of(3);
			}
		return Optional.empty();
	}

}
class Repo {
	public static List<Customer> getCustomerList() {
		MemberCard m1 = new MemberCard(100);
		Customer c1 = new Customer("Customer A",m1);
		MemberCard m2 = new MemberCard(50);
		Customer c2 = new Customer("Customer B",m2);
		MemberCard m3 = new MemberCard(10);
		Customer c3 = new Customer("Customer C",m3);	
		Customer c4 = new Customer("Customer D",null);
		return Arrays.asList(c1,c2,c3,c4);
	}
}
class Customer {
	String name ;
	MemberCard memberCard;
	public Customer(String name, MemberCard card) {
		super();
		this.name = name;
		this.memberCard = card;
	}
	public Optional<MemberCard> getMemberCard() {
		return Optional.ofNullable(memberCard);
	}

}
class MemberCard {
	int fidelityPoints ;

	public MemberCard(int fidelityPoints) {
		super();
		this.fidelityPoints = fidelityPoints;
	}

	public int getFidelityPoints() {
		return fidelityPoints;
	}
}