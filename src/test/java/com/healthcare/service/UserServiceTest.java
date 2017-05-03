package com.healthcare.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.User;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.model.enums.LanguageEnum;
import com.healthcare.model.enums.StateEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	private UserService userService;
	private String phone;

	@Test
	public void shouldSaveAUser() {
		String firstName = "Homer";
		String middleName = "J";
		String lastName = "Simpson";
		String socialSecurityNumber = "1234";
		String addressOne = "SpringField N345";
		String city = "City ABC";
		String zipcode = "00000";
		String familyDoctor = "Dr. Z";
		String familyDoctorAddress = "Z St";
		String familyDoctorTel = "9996663334";

		User user = new User();
		user.setFirstName(firstName);
		user.setMiddleName(middleName);
		user.setLastName(lastName);
		user.setGender(GenderEnum.MAN.name());
		user.setSocialSecurityNumber(socialSecurityNumber);
		user.setLanguage(LanguageEnum.ENGLISH.name());
		user.setAddressOne(addressOne);
		user.setCity(city); // Enum?
		user.setState(StateEnum.FLORIDA.name());
		user.setZipcode(zipcode);
		user.setPhone(phone);
		user.setFamilyDoctor(familyDoctor);
		user.setFamilyDoctorAddress(familyDoctorAddress);
		user.setFamilyDoctorTel(familyDoctorTel);
		/**
		 * DOB 
		 * Provider User ID 
		 * Payer User ID 
		 * Medicaid Number 
		 * Medicare Number
		 * Home 
		 * Emergency contact phone number 1 
		 * Emergency contact phone number 2 
		 * Name 
		 * Relationsip 
		 * Phone 
		 * Address 
		 * City 
		 * State 
		 * Zip Code 
		 * Comment: (Type of medication taking)"
		 */
		Assert.fail();
	}
}
