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
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Menu;
import com.healthcare.model.entity.Role;
import com.healthcare.model.enums.StateEnum;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.MenuService;
import com.healthcare.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuServiceTest {
	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private AgencyTypeService agencyTpeService;

	String name = "Menu A";
	String url = "/menu/menu";
	String angularUrl = "/angular/a";
	String page = "A";
	String clazz = "Clazz";
	String imgUrl = "/img/a.jpg";
	Calendar createdAt = Calendar.getInstance();
	Integer displayOrder = 1;

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
	Role role;

	@Before
	public void setup() {
		role = createNewRole();
	}

	@Test
	public void testSaveMenu() {
		Menu menu = createNewMenu();
		menuService.save(menu);
		Assert.assertNotNull(menu.getId());
	}

	@Test
	public void testGetMenu() {
		Menu menu = createNewMenu();
		menuService.save(menu);
		Assert.assertNotNull(menuService.findById(menu.getId()));
	}

	@Test
	public void testUpdateMenu() {
		String newImgUrl = "/img/new.jpg";

		Menu menu = createNewMenu();
		menuService.save(menu);
		Assert.assertEquals(menu.getImgUrl(), imgUrl);
		Menu menuSaved = menuService.findById(menu.getId());
		menuSaved.setImgUrl(newImgUrl);
		menuService.save(menuSaved);
		Menu menuMofified = menuService.findById(menu.getId());
		Assert.assertEquals(menuMofified.getImgUrl(), newImgUrl);
	}

	@Test
	public void testDeleteMenu() {
		Menu menu = createNewMenu();
		menuService.save(menu);
		Assert.assertNotNull(menu.getId());
		menuService.deleteById(menu.getId());
		Assert.assertNull(menuService.findById(menu.getId()));
	}

	private Menu createNewMenu() {
		Menu menu = new Menu();
		menu.setAngularUrl(angularUrl);
		menu.setClazz(clazz);
		menu.setCreatedAt(new Timestamp(createdAt.getTimeInMillis()));
		menu.setDisplayOrder(displayOrder);
		menu.setImgUrl(imgUrl);
		menu.setName(name);
		menu.setPage(page);
		menu.setRole(role);
		menu.setStatus(1);
		menu.setUrl(url);
		return menu;
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
