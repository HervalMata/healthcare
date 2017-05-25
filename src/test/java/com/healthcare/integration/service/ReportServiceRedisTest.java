package com.healthcare.integration.service;

import java.sql.Time;
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

import com.healthcare.model.entity.Admin;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Report;
import com.healthcare.model.entity.Role;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.model.enums.StateEnum;
import com.healthcare.repository.ReportRepository;
import com.healthcare.service.AdminService;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.ReportService;
import com.healthcare.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReportServiceRedisTest {
	@Autowired
	private ReportService reportService;

	@MockBean
	private ReportRepository reportRepository;

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

	String name = "Menu A";
	String url = "/menu/menu";
	String angularUrl = "/angular/a";
	String page = "A";
	String clazz = "Clazz";
	String imgUrl = "/img/a.jpg";
	Calendar createdAt = Calendar.getInstance();
	Integer displayOrder = 1;
	long baseId = 1;
	String dataColumns = "Data columns";
	Calendar endDate = Calendar.getInstance();
	String format = "Report format";
	String reportTitle = "Report title";
	Calendar startDate = Calendar.getInstance();

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
	Admin admin;
	Role role;
	Company company;

	@Before
	public void setup() {
		company = createNewCompany();
		role = createNewRole();
		admin = createNewAdmin();
	}

	@Test
	public void testSaveReport() {
		Report report = createNewReport();
		report.setId(7L);
		Mockito.when(reportRepository.save(report)).thenReturn(report);
		reportService.save(report);
		Report savedReport = reportService.findById(report.getId());
		Assert.assertNotNull(savedReport);
	}

	@Test
	public void testUpdateReport() {
		String newReportTitle = "New Report Title";

		Report report = createNewReport();
		report.setId(7L);
		Mockito.when(reportRepository.save(report)).thenReturn(report);
		reportService.save(report);
		Report reportSaved = reportService.findById(report.getId());
		reportSaved.setReportTitle(newReportTitle);
		Mockito.when(reportRepository.save(reportSaved)).thenReturn(reportSaved);
		reportService.save(reportSaved);
		Report reportMofified = reportService.findById(report.getId());
		Assert.assertEquals(reportMofified.getReportTitle(), newReportTitle);
	}

	@Test
	public void testDeleteReport() {
		Report report = createNewReport();
		report.setId(7L);
		Mockito.when(reportRepository.save(report)).thenReturn(report);
		reportService.save(report);
		Mockito.doNothing().when(reportRepository).delete(report.getId());
		reportService.deleteById(report.getId());
		Report deletedReport = reportService.findById(report.getId());
		Assert.assertNull(deletedReport);
	}

	private Report createNewReport() {
		Report report = new Report();
		report.setAdmin(admin);
		report.setBaseId(baseId);
		report.setCompany(company);
		report.setDataColumns(dataColumns);
		report.setEndDate(new Timestamp(endDate.getTimeInMillis()));
		report.setFormat(format);
		report.setReportTitle(reportTitle);
		report.setStartDate(new Timestamp(startDate.getTimeInMillis()));
		report.setCreatedAt(new Timestamp(createdAt.getTimeInMillis()));
		report.setRole(role);
		return report;
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
		admin.setStatus(1);
		admin.setRole(role);
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
