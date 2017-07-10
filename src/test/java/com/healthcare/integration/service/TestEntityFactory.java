package com.healthcare.integration.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Admin;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Employee;
import com.healthcare.model.entity.Role;
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.entity.WorkItem;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.model.enums.LanguageEnum;
import com.healthcare.model.enums.StateEnum;
import com.healthcare.model.enums.VisitStatusEnum;

public class TestEntityFactory {
	public static Calendar eligiableStart = Calendar.getInstance();
	public static Calendar eligiableEnd = Calendar.getInstance();
	public static String insuranceEligiable = "insuranceEligiable";
	public static Calendar insuranceStart = Calendar.getInstance();
	public static Calendar insuranceEnd = Calendar.getInstance();
	public static String username = "user";
	public static String password = "PASS";

	public static String firstName = "Homer";
	public static String middleName = "J";
	public static String lastName = "Simpson";
	public static String socialSecurityNumber = "1234";
	public static String phone = "1234560000";
	public static String addressType = "Home";
	public static String addressOne = "SpringField N345";
	public static String city = "City ABC";
	public static String zipcode = "00000";
	public static Calendar dob = Calendar.getInstance();
	public static String payerUserId = "99999";
	public static String medicaIdNumber = "556677";
	public static String medicareNumber = "223344";
	public static String emergencyContactPhone = "9876545555";
	public static String emergencyContactFirstName = "Marge";
	public static String emergencyContactMiddleName = "J";
	public static String emergencyContactLastName = "Bouvier";
	public static String emergencyContactCity = "Miami";
	public static String emergencyContactState = "Florida";
	public static String emergencyContactZipcode = "32656";
	public static String relationshipToParticipant = "Wife";
	public static String familyDoctor = "Dr. Z";
	public static String familyDoctorAddress = "Z St";
	public static String familyDoctorTel = "9996663334";
	public static String comment = "No comments";
	public static String vacationNote = ".";

	public static String licenseNo = "12D31";
	public static int trackingMode = 1;
	public static String contactPerson = "Joe";
	public static String addressTwo = "A st";
	public static String state = StateEnum.FLORIDA.name();
	public static String timezone = "UTC";
	public static String holiday = "12";
	public static String fax = "12212444";
	public static String email = "firstname@yahoo.com";
	public static String federalTax = "federalTax";
	public static Calendar federalTaxStart = Calendar.getInstance();
	public static Calendar federalTaxExpire = Calendar.getInstance();
	public static String stateTax = "stateTax";
	public static Calendar stateTaxStart = Calendar.getInstance();
	public static Calendar stateTaxExpire = Calendar.getInstance();
	public static Calendar worktimeStart = Calendar.getInstance();
	public static Calendar worktimeEnd = Calendar.getInstance();

	public static String ip = "127.0.0.1";
	public static String secondaryPhone = "1234560001";
	public static String profilePhoto = "XXXXXXXXXX";
	public static String deviceAddress = "City ABC";
	public static String rememberToken = "00000";

	public static Timestamp checkInTime = new Timestamp(new Date().getTime());
	public static Timestamp checkOutTime = new Timestamp(new Date().getTime());
	public static String userComments = "all ok";
	public static String notes = ".";
	public static String selectedTable = "TABLE 1";
	public static String selectedSeat = "AB";
	public static String userSignature = "userSignature";

	public static String itemName = "help on shopping";
	public static String itemNote = "help on shopping note";

	public static String levelName = "Level Name";
	public static long level = 1;
	public static long status = 1;
	public static int iStatus = 1;

	/**
	 * Employee
	 */
	public static Long id = 1L;
	public static String eFirstName = "firstName";
	public static String eLastName = "lastName";
	public static String gender = "gender";
	public static String eSocialSecurityNumber = "socialSecurityNumber";
	public static Calendar dateOfBirth = Calendar.getInstance();
	public static String physicalExam = "physicalExam";
	public static String certificateName = "certificateName";
	public static Calendar certificateStart = Calendar.getInstance();
	public static Calendar certificateEnd = Calendar.getInstance();
	public static Calendar workStart = Calendar.getInstance();
	public static Calendar workEnd = Calendar.getInstance();
	public static String position = "position";
	public static String manager = "manager";
	public static String type = "type";
	public static String statusEmp = "status";
	public static String backgroundCheck = "backgroundCheck";

	/**
	 * ServicePlan
	 */
	public static String approvedBy = "Manager";
	public static Calendar planStart = Calendar.getInstance();
	public static Calendar planEnd = Calendar.getInstance();
	public static String days = "Monday";
	public static String docUrl = "/doc/a";

	/**
	 * Activity
	 */

	public static String name = "name";
	public static Calendar createdAt = Calendar.getInstance();
	public static Calendar updatedAt = Calendar.getInstance();
	public static Calendar timeStart = Calendar.getInstance();
	public static Calendar timeEnd = Calendar.getInstance();
	public static String date = "date";
	public static String location = "location";
	public static String note = "note";

	public void init() {
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

	public static Agency createNewAgency(Company company, AgencyType agencyType) {
		Agency agency = new Agency();
		agency.setAgencyType(agencyType);
		agency.setCompany(company);
		agency.setCompany1(company);
		agency.setAddressOne(addressOne);
		agency.setAddressTwo(addressTwo);
		agency.setCity(city);
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
		return agency;
	}

	public static Company createNewCompany() {
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
		return company;
	}

	public static AgencyType createNewAgencyType() {
		AgencyType agencyType = new AgencyType();
		agencyType.setName("Agency Type Name");
		agencyType.setStatus(1);
		return agencyType;
	}

	public static User createNewUser() {
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

	public static Visit createNewVisit(User user, Agency agency, ServicePlan servicePlan) {
		Visit visit = new Visit();
		visit.setUser(user);
		visit.setAgency(agency);
		visit.setCheckInTime(checkInTime);
		visit.setCheckOutTime(checkOutTime);
		visit.setSelectedSeat(selectedTable);
		visit.setSelectedSeat(selectedSeat);
		visit.setUserSignature(userSignature);
		visit.setServicePlan(servicePlan);
		// visit.setSelectedMeal(selectedMeal);// TODO not yet finished Meal
		// CRUD
		// visit.setUserBarcodeId(userBarcodeId);// TODO not yet validates there
		// is asigned when agency accepts Patient
		visit.setUserComments(userComments);
		visit.setNotes(notes);
		visit.setStatus(VisitStatusEnum.BOOKED.name());
		return visit;
	}

	public static Role createNewRole(Agency agency) {
		Role role = new Role();
		role.setLevel(level);
		role.setLevelName(levelName);
		role.setStatus(status);
		role.setAgency(agency);
		return role;
	}

	public static WorkItem createNewWorkItem() {
		WorkItem workItem = new WorkItem();
		workItem.setItemName(itemName);
		workItem.setItemNote(itemNote);
		return workItem;
	}

	public static Employee createNewEmployee(Agency agency) {
		Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstName(eFirstName);
		employee.setLastName(eLastName);
		employee.setGender(gender);
		employee.setSocialSecurityNumber(eSocialSecurityNumber);
		employee.setDateOfBirth(new Timestamp(dateOfBirth.getTimeInMillis()));
		employee.setPhysicalExam(physicalExam);
		employee.setCertificateName(certificateName);
		employee.setCertificateStart(new Timestamp(certificateStart.getTimeInMillis()));
		employee.setCertificateEnd(new Timestamp(certificateEnd.getTimeInMillis()));
		employee.setWorkStart(new Timestamp(workStart.getTimeInMillis()));
		employee.setWorkEnd(new Timestamp(workEnd.getTimeInMillis()));
		employee.setPosition(position);
		employee.setManager(manager);
		employee.setType(type);
		employee.setStatus(statusEmp);
		employee.setBackgroundCheck(backgroundCheck);
		employee.setAgency(agency);
		return employee;
	}

	public static Admin createNewAdmin(Role role) {
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
		return admin;
	}

	public static ServicePlan createNewServicePlan(Employee employee, User user) {
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

	public static Activity createNewActivity(Employee employee) {
		Activity activity = new Activity();
		activity.setName(name);
		activity.setStatus(iStatus);
		// activity.setCreatedAt(new Timestamp(createdAt.getTimeInMillis()));
		// activity.setUpdatedAt(new Timestamp(updatedAt.getTimeInMillis()));
		activity.setInstructorEmployee(employee);
		activity.setTimeStart(String.valueOf(new Time(timeStart.getTimeInMillis())));
		activity.setTimeEnd(String.valueOf(new Time(timeEnd.getTimeInMillis())));
		activity.setDate(date);
		activity.setLocation(location);
		activity.setNote(note);
		return activity;
	}

}
