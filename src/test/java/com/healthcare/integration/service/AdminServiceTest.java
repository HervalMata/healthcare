package com.healthcare.integration.service;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.Admin;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Role;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.service.AdminService;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminServiceTest {
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

	
	private Admin admin;
	private Role role;
	private Company company;
	private Agency agency;
	private AgencyType agencyType;
	
	@Before
	public void setup() {
		company = companyService.save(TestEntityFactory.createNewCompany());
    	agencyType = agencyTpeService.save(TestEntityFactory.createNewAgencyType());
    	agency = agencyService.save(TestEntityFactory.createNewAgency(company,agencyType));
    	role = roleService.save(TestEntityFactory.createNewRole(agency));
		admin = null;
	}

	
	@After
	public void rollback() {
		if(admin!=null){
			adminService.deleteById(admin.getId());
		}
		roleService.deleteById(role.getId());
        agencyService.deleteById(agency.getId());
        agencyTpeService.deleteById(agencyType.getId());
        companyService.deleteById(company.getId());
	}

	@Test
	public void testSaveAdmin() {
		admin = createNewAdmin();
		adminService.save(admin);
		Assert.assertNotNull(admin.getId());
	}

	@Test
	public void testGetAdmin() {
		admin = createNewAdmin();
		adminService.save(admin);
		Assert.assertNotNull(adminService.findById(admin.getId()));
	}

	@Test
	public void testUpdateAdmin() {
		String newPhone = "5967897788";
		String newEmail = "firstname2@yahoo.com";

		admin = createNewAdmin();
		adminService.save(admin);
		Assert.assertEquals(admin.getPhone(), phone);
		Assert.assertEquals(admin.getEmail(), email);
		Admin adminSaved = adminService.findById(admin.getId());
		adminSaved.setPhone(newPhone);
		adminSaved.setEmail(newEmail);
		adminService.save(adminSaved);
		Admin adminMofified = adminService.findById(admin.getId());
		Assert.assertEquals(adminMofified.getPhone(), newPhone);
		Assert.assertEquals(adminMofified.getEmail(), newEmail);
	}

	@Test
	public void testDeleteAdmin() {
		Admin admin = createNewAdmin();
		adminService.save(admin);
		Assert.assertNotNull(admin.getId());
		adminService.deleteById(admin.getId());
		Assert.assertNull(adminService.findById(admin.getId()));
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
		admin.setRole(role);
		return admin;
	}
}
