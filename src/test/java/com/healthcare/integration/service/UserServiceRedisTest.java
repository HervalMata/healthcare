package com.healthcare.integration.service;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.User;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.model.enums.LanguageEnum;
import com.healthcare.model.enums.StateEnum;
import com.healthcare.repository.UserRepository;
import com.healthcare.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceRedisTest {
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	Calendar eligiableStart = Calendar.getInstance();
	Calendar eligiableEnd = Calendar.getInstance();
	String insuranceEligiable = "insuranceEligiable";
	Calendar insuranceStart = Calendar.getInstance();
	Calendar insuranceEnd = Calendar.getInstance();
	String username = "user";
	String password = "PASS";

	String firstName = "Homer";
	String middleName = "J";
	String lastName = "Simpson";
	String socialSecurityNumber = "1234";
	String phone = "1234560000";
	String addressType = "Home";
	String addressOne = "SpringField N345";
	String city = "City ABC";
	String zipcode = "00000";
	Calendar dob = Calendar.getInstance();
	String payerUserId = "99999";
	String medicaIdNumber = "556677";
	String medicareNumber = "223344";
	String emergencyContactPhone = "9876545555";
	String emergencyContactFirstName = "Marge";
	String emergencyContactMiddleName = "J";
	String emergencyContactLastName = "Bouvier";
	String emergencyContactCity = "Miami";
	String emergencyContactState = "Florida";
	String emergencyContactZipcode = "32656";
	String relationshipToParticipant = "Wife";
	String familyDoctor = "Dr. Z";
	String familyDoctorAddress = "Z St";
	String familyDoctorTel = "9996663334";
	String comment = "No comments";
	String vacationNote = ".";

	@Before
	public void setup() {
		eligiableStart.set(Calendar.YEAR, 2017);
		eligiableStart.set(Calendar.MONTH, 1);
		eligiableStart.set(Calendar.DAY_OF_MONTH, 1);
		eligiableEnd.set(Calendar.YEAR, 2017);
		eligiableEnd.set(Calendar.MONTH, 12);
		eligiableEnd.set(Calendar.DAY_OF_MONTH, 31);
		insuranceStart.set(Calendar.YEAR, 2016);
		insuranceStart.set(Calendar.MONTH, 1);
		insuranceStart.set(Calendar.DAY_OF_MONTH, 1);
		insuranceEnd.set(Calendar.YEAR, 2018);
		insuranceEnd.set(Calendar.MONTH, 12);
		insuranceEnd.set(Calendar.DAY_OF_MONTH, 31);
		dob.set(Calendar.YEAR, 1950);
		dob.set(Calendar.MONTH, 1);
		dob.set(Calendar.DAY_OF_MONTH, 1);
	}

	@Test
	public void shouldSaveAUserToRedisAndRetrievedItFromRedis() {
		User user = createNewUser();
		user.setId(10L);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		userService.save(user);
		User userSaved = userService.findById(10L);
		Assert.assertNotNull(userSaved);
	}

	@Test
	public void shouldUpdateAUserToRedis() {
		String newPhone = "5967897788";
		String newAddress = "Av. 57 y 23 St.";

		User user = createNewUser();
		user.setId(10L);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		userService.save(user);
		User userSaved = userService.findById(user.getId());
		userSaved.setPhone(newPhone);
		userSaved.setAddressOne(newAddress);
		Mockito.when(userRepository.save(userSaved)).thenReturn(userSaved);
		userService.save(userSaved);
		User userMofified = userService.findById(user.getId());
		Assert.assertEquals(userMofified.getPhone(), newPhone);
		Assert.assertEquals(userMofified.getAddressOne(), newAddress);
	}

	@Test
	public void shouldDeleteAUser() {
		User user = createNewUser();
		user.setId(10L);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		userService.save(user);
		Mockito.doNothing().when(userRepository).delete(10L);
		userService.deleteById(user.getId());
		User userDeleted = userService.findById(10L);
		Assert.assertNull(userDeleted);
	}

	private User createNewUser() {
		User user = new User();
		user.setEligiableStart(new Timestamp(eligiableStart.getTimeInMillis()));
		user.setEligiableEnd(new Timestamp(eligiableEnd.getTimeInMillis()));
		user.setInsuranceEligiable(insuranceEligiable);
		user.setInsuranceStart(new Timestamp(insuranceStart.getTimeInMillis()));
		user.setInsuranceEnd(new Timestamp(insuranceEnd.getTimeInMillis()));
		user.setUsername(username);
		user.setPassword(password);

		user.setFirstName(firstName);
		user.setMiddleName(middleName);
		user.setLastName(lastName);
		user.setGender(GenderEnum.MAN.name());
		user.setDateOfBirth(dob.getTime());
		user.setSocialSecurityNumber(socialSecurityNumber);
		user.setInsurance(payerUserId);
		user.setMedicaIdNumber(medicaIdNumber);
		user.setMedicareNumber(medicareNumber);
		user.setLanguage(LanguageEnum.ENGLISH.name());
		user.setAddressType(addressType);
		user.setAddressOne(addressOne);
		user.setCity(city); // Enum?
		user.setState(StateEnum.FLORIDA.name());
		user.setZipcode(zipcode);
		user.setPhone(phone);

		user.setEmergencyContactPhone(emergencyContactPhone);
		user.setEmergencyContactFirstName(emergencyContactFirstName);
		user.setEmergencyContactMiddleName(emergencyContactMiddleName);
		user.setEmergencyContactLastName(emergencyContactLastName);
		user.setRelationshipToParticipant(relationshipToParticipant);
		user.setEmergencyContactCity(emergencyContactCity);
		user.setEmergencyContactState(emergencyContactState);
		user.setEmergencyContactZipcode(emergencyContactZipcode);

		user.setFamilyDoctor(familyDoctor);
		user.setFamilyDoctorTel(familyDoctorTel);
		user.setFamilyDoctorAddress(familyDoctorAddress);

		user.setComment(comment);
		user.setVacationNote(vacationNote);

		return user;
	}
}
