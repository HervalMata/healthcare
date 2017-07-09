package com.healthcare.integration.service;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hamcrest.collection.IsEmptyCollection;
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
import com.healthcare.model.entity.Employee;
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.model.entity.User;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.model.enums.LanguageEnum;
import com.healthcare.model.enums.StateEnum;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.EmployeeService;
import com.healthcare.service.ServicePlanService;
import com.healthcare.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServicePlanServiceTest {
	@Autowired
	private ServicePlanService servicePlanService;

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
	Calendar planStart = new GregorianCalendar(2017, Calendar.JUNE, 1);
	Calendar planEnd = new GregorianCalendar(2017, Calendar.AUGUST, 1);
	String days = "Tuesday";
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

	@Before
	public void setup() {
		user = createNewUser();
		employee = createNewEmployee();
	}

	@Test
	public void testSaveServicePlan() {
		ServicePlan servicePlan = createNewServicePlan();
		servicePlanService.save(servicePlan);
		Assert.assertNotNull(servicePlan.getId());
	}

	@Test
	public void testGetServicePlan() {
		ServicePlan servicePlan = createNewServicePlan();
		servicePlanService.save(servicePlan);
		Assert.assertNotNull(servicePlanService.findById(servicePlan.getId()));
	}

	@Test
	public void testUpdateServicePlan() {
		String newDocUrl = "/doc/new/a";
		ServicePlan servicePlan = createNewServicePlan();
		servicePlanService.save(servicePlan);
		Assert.assertEquals(servicePlan.getDocUrl(), docUrl);
		ServicePlan savedServicePlan = servicePlanService.findById(servicePlan.getId());
		savedServicePlan.setDocUrl(newDocUrl);
		servicePlanService.save(savedServicePlan);
		ServicePlan modifiedServicePlan = servicePlanService.findById(servicePlan.getId());
		Assert.assertEquals(modifiedServicePlan.getDocUrl(), newDocUrl);
	}

	@Test
	public void testDeleteServicePlan() {
		ServicePlan servicePlan = createNewServicePlan();
		servicePlanService.save(servicePlan);
		Assert.assertNotNull(servicePlan.getId());
		servicePlanService.deleteById(servicePlan.getId());
		Assert.assertNull(servicePlanService.findById(servicePlan.getId()));
	}
	
    @Test
    public void testserviceCalendarGeneration(){
    	// given
    	List<Date> expectedList = Arrays.asList(
    			new GregorianCalendar(2017, Calendar.JUNE, 06).getTime(), new GregorianCalendar(2017, Calendar.JUNE, 13).getTime(),
    			new GregorianCalendar(2017, Calendar.JUNE, 20).getTime(), new GregorianCalendar(2017, Calendar.JUNE, 27).getTime(),
    			new GregorianCalendar(2017, Calendar.JULY, 04).getTime(), new GregorianCalendar(2017, Calendar.JULY, 11).getTime(),
    			new GregorianCalendar(2017, Calendar.JULY, 18).getTime(), new GregorianCalendar(2017, Calendar.JULY, 25).getTime());
    	//when
    	ServicePlan serviceplan = servicePlanService.save(createNewServicePlan());
    	List<Date> result = servicePlanService.serviceCalendarGeneration(serviceplan.getId());
    	
    	//then
    	//assert not null
    	assertThat(result, notNullValue());
    	//assert is expected
    	assertThat(result, is(expectedList));
        //assert has item
        assertThat(result, hasItems(new GregorianCalendar(2017, Calendar.JUNE, 06).getTime()));
        //assert size
        assertThat(result, hasSize(8));	
        //check empty list
        assertThat(result, not(IsEmptyCollection.empty()));	
        //assert random list item betwen start and end plan date
        assertTrue(result.get(0).before(serviceplan.getPlanEnd()));
        assertTrue(result.get(1).after(serviceplan.getPlanStart()));
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

	private Employee createNewEmployee() {
		Employee employee = new Employee();
		employee.setAgency(createNewAgency());
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

	private Agency createNewAgency() {
		Agency agency = new Agency();
		Company company = createNewCompany();
		agency.setAddressOne(addressOne);
		agency.setAddressTwo(addressTwo);
		AgencyType agencyType = createNewAgencyType();
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
