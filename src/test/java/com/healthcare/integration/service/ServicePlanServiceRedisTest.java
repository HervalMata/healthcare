package com.healthcare.integration.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Employee;
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.model.entity.User;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.model.enums.LanguageEnum;
import com.healthcare.model.enums.StateEnum;
import com.healthcare.repository.ServicePlanRepository;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.EmployeeService;
import com.healthcare.service.ServicePlanService;
import com.healthcare.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServicePlanServiceRedisTest {
	@Autowired
	private ServicePlanService servicePlanService;

	@MockBean
	private ServicePlanRepository servicePlanRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AgencyTypeService agencyTypeService;

	User user;
	Employee employee;

	String approvedBy = "Manager";
	Calendar planStart = Calendar.getInstance();
	Calendar planEnd = Calendar.getInstance();
	String days = "Monday";
	String docUrl = "/doc/a";
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
	String levelName = "Level Name";
	long level = 1;
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

	String gender = GenderEnum.MAN.name();
	String socialSecurityNumber = "socialSecurityNumber";
	Calendar dob = Calendar.getInstance();
	String physicalExam = "physicalExam";
	String certificateName = "certificateName";
	Calendar certificateStart = Calendar.getInstance();
	Calendar certificateEnd = Calendar.getInstance();
	Calendar workStart = Calendar.getInstance();
	Calendar workEnd = Calendar.getInstance();
	String position = "position";
	String manager = "manager";
	String type = "type";
	String statusString = "status";
	String backgroundCheck = "backgroundCheck";

	Calendar eligiableStart = Calendar.getInstance();
	Calendar eligiableEnd = Calendar.getInstance();
	String insuranceEligiable = "insuranceEligiable";
	Calendar insuranceStart = Calendar.getInstance();
	Calendar insuranceEnd = Calendar.getInstance();
	Calendar affectStart = Calendar.getInstance();
	Calendar affectEnd = Calendar.getInstance();

	String addressType = "Home";
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

	private Agency agency;
	private Company company;
	private AgencyType agencyType;

	@Before
	public void setup() {
		company = createNewCompany();
    	agencyType = createNewAgencyType();
    	agency = createNewAgency(company,agencyType);
		user = createNewUser();
		employee = createNewEmployee(agency);
	}

	private Long id = 7L;
	@After
	public void rollback() {
		servicePlanService.deleteById(id);
		employeeService.deleteById(employee.getId());
		userService.deleteById(user.getId());
        agencyService.deleteById(agency.getId());
        agencyTypeService.deleteById(agencyType.getId());
        companyService.deleteById(company.getId());
	}

	@Test
	public void testSaveServicePlan() {
		ServicePlan servicePlan = createNewServicePlan();
		servicePlan.setId(id);
		Mockito.when(servicePlanRepository.save(servicePlan)).thenReturn(servicePlan);
		servicePlan = servicePlanService.save(servicePlan);
		Assert.assertNotNull(servicePlanService.findById(servicePlan.getId()));
	}

	@Test
	public void testUpdateServicePlan() {
		String newDocUrl = "/doc/new/a";
		ServicePlan servicePlan = createNewServicePlan();
		servicePlan.setId(id);
		Mockito.when(servicePlanRepository.save(servicePlan)).thenReturn(servicePlan);
		servicePlan = servicePlanService.save(servicePlan);
		ServicePlan savedServicePlan = servicePlanService.findById(servicePlan.getId());
		savedServicePlan.setDocUrl(newDocUrl);
		Mockito.when(servicePlanRepository.save(savedServicePlan)).thenReturn(savedServicePlan);
		servicePlanService.save(savedServicePlan);
		ServicePlan modifiedServicePlan = servicePlanService.findById(servicePlan.getId());
		Assert.assertEquals(modifiedServicePlan.getDocUrl(), newDocUrl);
	}

	@Test
	public void testDeleteServicePlan() {
		ServicePlan servicePlan = createNewServicePlan();
		servicePlan.setId(id);
		Mockito.when(servicePlanRepository.save(servicePlan)).thenReturn(servicePlan);
		servicePlan = servicePlanService.save(servicePlan);
		Mockito.doNothing().when(servicePlanRepository).delete(servicePlan.getId());
		Assert.assertNotNull(servicePlanService.deleteById(servicePlan.getId()));
	}

	private ServicePlan createNewServicePlan() {
		ServicePlan servicePlan = new ServicePlan();
		servicePlan.setApprovedBy(approvedBy);
		servicePlan.setDays(days);
		servicePlan.setDocUrl(docUrl);
		servicePlan.setEmployee(employee);
		servicePlan.setPlanEnd(new Timestamp(planEnd.getTimeInMillis()));
		servicePlan.setPlanStart(new Timestamp(planStart.getTimeInMillis()));
		servicePlan.setUser(user);
		return servicePlan;
	}

	private User createNewUser() {
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
		user.setCity(city);
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

		return userService.save(user);
	}

	private Employee createNewEmployee(Agency agency) {
		Employee employee = new Employee();
		employee.setAgency(agency);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setGender(gender);
		employee.setSocialSecurityNumber(socialSecurityNumber);
		employee.setDateOfBirth(new Timestamp(dob.getTimeInMillis()));
		employee.setPhysicalExam(physicalExam);
		employee.setWorkStart(new Timestamp(workStart.getTimeInMillis()));
		employee.setWorkEnd(new Timestamp(workEnd.getTimeInMillis()));
		employee.setCertificateName(certificateName);
		employee.setCertificateStart(new Timestamp(certificateStart.getTimeInMillis()));
		employee.setCertificateEnd(new Timestamp(certificateEnd.getTimeInMillis()));
		employee.setPosition(position);
		employee.setManager(manager);
		employee.setType(type);
		employee.setStatus(statusString);
		employee.setBackgroundCheck(backgroundCheck);
		return employeeService.save(employee);
	}

	private Agency createNewAgency(Company company, AgencyType agencyType) {
		Agency agency = new Agency();
		agency.setAddressOne(addressOne);
		agency.setAddressTwo(addressTwo);
		agency.setAgencyType(agencyType);
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
		return agencyTypeService.save(agencyType);
	}
}
