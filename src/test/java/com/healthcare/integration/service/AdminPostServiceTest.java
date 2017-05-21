package com.healthcare.integration.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.Admin;
import com.healthcare.model.entity.AdminPost;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Role;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.model.enums.StateEnum;
import com.healthcare.service.AdminPostService;
import com.healthcare.service.AdminService;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminPostServiceTest {
	@Autowired
	private AdminPostService adminPostService;

	private static String ADMINPOST_KEY = "AdminPost";

	@Autowired
	private RedisTemplate<String, AdminPost> adminPostRedisTemplate;

	@Autowired
	private AdminService adminService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private AgencyTypeService agencyTpeService;

	String username = "username";
	String password = "password";
	String firstName = "John";
	String middleName = "B";
	String lastName = "Watson";
	String phone = "1234560000";
	String email = "firstname@yahoo.com";
	String ip = "127.0.0.1";
	String secondaryPhone = "1234560001";
	String profilePhoto = "XXXXXXXXXX";
	String deviceAddress = "City ABC";
	String rememberToken = "00000";
	long status = 1;

	String licenseNo = "12D31";
	int trackingMode = 1;
	String contactPerson = "Joe";
	String addressOne = "20, Green St";
	String addressTwo = "A st";
	String city = "Orlando";
	String state = StateEnum.FLORIDA.name();
	String zipcode = "2122";
	String timezone = "UTC";
	String holiday = "12";
	String fax = "12212444";

	String federalTax = "federalTax";
	Calendar federalTaxStart = Calendar.getInstance();
	Calendar federalTaxExpire = Calendar.getInstance();
	String stateTax = "stateTax";
	Calendar stateTaxStart = Calendar.getInstance();
	Calendar stateTaxExpire = Calendar.getInstance();
	Calendar worktimeStart = Calendar.getInstance();
	Calendar worktimeEnd = Calendar.getInstance();

	Calendar postDate = Calendar.getInstance();
	String postText = "This is post text";

	Admin admin;

	@Before
	public void setup() {
		admin = createNewAdmin();
	}

	@Test
	public void testSaveAdminPost() {
		AdminPost adminPost = createNewAdminPost();
		adminPostService.save(adminPost);
		Assert.assertNotNull(adminPost.getId());
		Assert.assertNotNull(adminPostRedisTemplate.opsForHash().get(ADMINPOST_KEY, adminPost.getId()));
	}

	@Test
	public void testGetAdminPost() {
		AdminPost adminPost = createNewAdminPost();
		adminPostService.save(adminPost);
		Assert.assertNotNull(adminPostService.findById(adminPost.getId()));
		Assert.assertNotNull(adminPostRedisTemplate.opsForHash().get(ADMINPOST_KEY, adminPost.getId()));
	}

	@Test
	public void testFindAllAdminPost() {
		long size = adminPostService.findAll().size();
		AdminPost adminPost = createNewAdminPost();
		adminPostService.save(adminPost);
		AdminPost adminPost2 = createNewAdminPost();
		adminPostService.save(adminPost2);
		Assert.assertEquals(size + 2, adminPostService.findAll().size());
		Assert.assertEquals(size + 2, adminPostRedisTemplate.opsForHash().entries(ADMINPOST_KEY).size());
	}

	@Test
	public void testUpdateAdminPost() {
		String newPostText = "This is new post text";

		AdminPost adminPost = createNewAdminPost();
		adminPostService.save(adminPost);
		Assert.assertEquals(adminPost.getPostText(), postText);
		Assert.assertEquals(
				((AdminPost) adminPostRedisTemplate.opsForHash().get(ADMINPOST_KEY, adminPost.getId())).getPostText(),
				postText);
		AdminPost adminPostSaved = adminPostService.findById(adminPost.getId());
		adminPostSaved.setPostText(newPostText);
		adminPostService.save(adminPostSaved);
		AdminPost adminPostMofified = adminPostService.findById(adminPost.getId());
		Assert.assertEquals(adminPostMofified.getPostText(), newPostText);
		Assert.assertEquals(
				((AdminPost) adminPostRedisTemplate.opsForHash().get(ADMINPOST_KEY, adminPost.getId())).getPostText(),
				newPostText);
	}

	@Test
	public void testDeleteAdminPost() {
		AdminPost adminPost = createNewAdminPost();
		adminPostService.save(adminPost);
		Assert.assertNotNull(adminPost.getId());
		Assert.assertNotNull(adminPostRedisTemplate.opsForHash().get(ADMINPOST_KEY, adminPost.getId()));
		adminPostService.deleteById(adminPost.getId());
		Assert.assertNull(adminPostService.findById(adminPost.getId()));
		Assert.assertNull(adminPostRedisTemplate.opsForHash().get(ADMINPOST_KEY, adminPost.getId()));
	}

	private AdminPost createNewAdminPost() {
		AdminPost adminPost = new AdminPost();
		adminPost.setPostDate(new Timestamp(postDate.getTimeInMillis()));
		adminPost.setPostText(postText);
		adminPost.setStatus(1);
		adminPost.setAdmin(admin);
		return adminPost;
	}

	private Admin createNewAdmin() {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setFirstName(firstName);
		admin.setMiddleName(middleName);
		admin.setLastName(lastName);
		admin.setGender(GenderEnum.MAN.name());
		admin.setPhone(phone);
		admin.setEmail(email);
		admin.setDeviceAddress(deviceAddress);
		admin.setIp(ip);
		admin.setProfilePhoto(profilePhoto);
		admin.setRememberToken(rememberToken);
		admin.setSecondaryPhone(secondaryPhone);
		admin.setStatus(status);
		admin.setRole(createNewRole());
		return adminService.save(admin);
	}

	private Role createNewRole() {
		String levelName = "levelName";
		long level = 1;
		long status = 1;

		Role role = new Role();
		role.setLevel(level);
		role.setLevelName(levelName);
		role.setStatus(status);
		role.setAgency(createNewAgency());
		return roleService.save(role);
	}

	private Agency createNewAgency() {
		Agency agency = new Agency();
		Company company = createNewCompany();
		agency.setAddressOne(addressOne);
		agency.setAddressTwo(addressTwo);
		agency.setAgencyType(createNewAgencyType());
		agency.setCity(city);
		agency.setCompany(company);
		agency.setCompany1(company);
		agency.setContactPerson(contactPerson);
		agency.setEmail(email);
		agency.setFax(fax);
		agency.setHoliday(holiday);
		agency.setLicenseNo(licenseNo);
		agency.setName("Agency Name");
		agency.setPhone(phone);
		agency.setState(state);
		agency.setTimezone(timezone);
		agency.setTrackingMode(trackingMode);
		agency.setZipcode(zipcode);
		return agencyService.save(agency);
	}

	private Company createNewCompany() {
		Company company = new Company();
		company.setAddressOne(addressOne);
		company.setAddressTwo(addressTwo);
		company.setCity(city);
		company.setEmail(email);
		company.setFax(fax);
		company.setFederalTax(federalTax);
		company.setFederalTaxExpire(new Timestamp(federalTaxExpire.getTimeInMillis()));
		company.setFederalTaxStart(new Timestamp(federalTaxStart.getTimeInMillis()));
		company.setFederalTaxStatus(1);
		company.setLicenseNo(licenseNo);
		company.setName("Company Name");
		company.setPhone(phone);
		company.setState(state);
		company.setStateTax(stateTax);
		company.setStateTaxExpire(new Timestamp(stateTaxExpire.getTimeInMillis()));
		company.setStateTaxStart(new Timestamp(stateTaxStart.getTimeInMillis()));
		company.setStateTaxStatus(1);
		company.setStatus(1);
		company.setWorktimeEnd(new Time(worktimeEnd.getTimeInMillis()));
		company.setWorktimeStart(new Time(worktimeStart.getTimeInMillis()));
		company.setZipcode(zipcode);
		return companyService.save(company);
	}

	private AgencyType createNewAgencyType() {
		AgencyType agencyType = new AgencyType();
		agencyType.setName("Agency Type Name");
		agencyType.setStatus(1);
		return agencyTpeService.save(agencyType);
	}
}
