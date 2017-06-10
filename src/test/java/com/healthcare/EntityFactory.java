package com.healthcare;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Employee;
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.enums.DayEnum;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.model.enums.LanguageEnum;
import com.healthcare.model.enums.StateEnum;
import com.healthcare.model.enums.VisitStatusEnum;

public class EntityFactory {
	public Calendar eligiableStart = Calendar.getInstance();
	public Calendar eligiableEnd = Calendar.getInstance();
	public String insuranceEligiable = "insuranceEligiable";
	public Calendar insuranceStart = Calendar.getInstance();
	public Calendar insuranceEnd = Calendar.getInstance();
	public String username = "user";
	public String password = "PASS";

	public String firstName = "Homer";
	public String middleName = "J";
	public String lastName = "Simpson";
	public String socialSecurityNumber = "1234";
	public String phone = "1234560000";
	public String addressType = "Home";
	public String addressOne = "SpringField N345";
	public String city = "City ABC";
	public String zipcode = "00000";
	public Calendar dob = Calendar.getInstance();
	public String payerUserId = "99999";
	public String medicaIdNumber = "556677";
	public String medicareNumber = "223344";
	public String emergencyContactPhone = "9876545555";
	public String emergencyContactFirstName = "Marge";
	public String emergencyContactMiddleName = "J";
	public String emergencyContactLastName = "Bouvier";
	public String emergencyContactCity = "Miami";
	public String emergencyContactState = "Florida";
	public String emergencyContactZipcode = "32656";
	public String relationshipToParticipant = "Wife";
	public String familyDoctor = "Dr. Z";
	public String familyDoctorAddress = "Z St";
	public String familyDoctorTel = "9996663334";
	public String comment = "No comments";
	public String vacationNote = ".";

	public String licenseNo = "12D31";
	public int trackingMode = 1;
	public String contactPerson = "Joe";
	public String addressTwo = "A st";
	public String state = StateEnum.FLORIDA.name();
	public String timezone = "UTC";
	public String holiday = "12";
	public String fax = "12212444";
	public String email = "firstname@yahoo.com";
	public String federalTax = "federalTax";
	public Calendar federalTaxStart = Calendar.getInstance();
	public Calendar federalTaxExpire = Calendar.getInstance();
	public String stateTax = "stateTax";
	public Calendar stateTaxStart = Calendar.getInstance();
	public Calendar stateTaxExpire = Calendar.getInstance();
	public Calendar worktimeStart = Calendar.getInstance();
	public Calendar worktimeEnd = Calendar.getInstance();

	public Timestamp checkInTime = new Timestamp(new Date().getTime());
	public Timestamp checkOutTime = new Timestamp(new Date().getTime());
	public String userComments = "all ok";
	public String notes = ".";
	public String selectedTable = "TABLE 1";
	public String selectedSeat = "AB";
	public String userSignature = "userSignature";
	
	public String approvedBy = "Manager";
	public Calendar planStart = Calendar.getInstance();
	public Calendar planEnd = Calendar.getInstance();
	String days = DayEnum.MONDAY.name() + "," + DayEnum.THURSDAY.name();
	public String docUrl = "/doc/a";
	
	public String gender = GenderEnum.MAN.name();
	public String physicalExam = "physicalExam";
	public String certificateName = "certificateName";
	public Calendar certificateStart = Calendar.getInstance();
	public Calendar certificateEnd = Calendar.getInstance();
	public Calendar workStart = Calendar.getInstance();
	public Calendar workEnd = Calendar.getInstance();
	public String position = "position";
	public String manager = "manager";
	public String type = "type";
	public String statusString = "status";
	public String backgroundCheck = "backgroundCheck";
	
	public String seat = "10A";

	protected void init() {
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
		planStart.set(Calendar.YEAR, 2017);
		planStart.set(Calendar.MONTH, 6);
		planStart.set(Calendar.DAY_OF_MONTH, 1);
		planEnd.set(Calendar.YEAR, 2017);
		planEnd.set(Calendar.MONTH, 12);
		planEnd.set(Calendar.DAY_OF_MONTH, 1);
	}

	protected Agency createNewAgency(AgencyType agencyType, Company company) {
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

	protected Company createNewCompany() {
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

	protected AgencyType createNewAgencyType() {
		AgencyType agencyType = new AgencyType();
		agencyType.setName("Agency Type Name");
		agencyType.setStatus(1);
		return agencyType;
	}

	protected User createNewUser() {
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

	protected ServicePlan createNewServicePlan(User user){
		ServicePlan servicePlan = new ServicePlan();
		servicePlan.setApprovedBy(approvedBy);
		servicePlan.setDays(days);
		servicePlan.setDocUrl(docUrl);
		servicePlan.setEmployee(null);
		servicePlan.setPlanEnd(new Timestamp(planEnd.getTimeInMillis()));
		servicePlan.setPlanStart(new Timestamp(planStart.getTimeInMillis()));
		servicePlan.setUser(user);
		return servicePlan;
	}
	
	protected Visit createNewVisit(User user, Agency agency) {
		Visit visit = new Visit();
		visit.setUser(user);
		visit.setAgency(agency);
		visit.setCheckInTime(checkInTime);
		visit.setCheckOutTime(checkOutTime);
		visit.setSelectedSeat(selectedTable);
		visit.setSelectedSeat(selectedSeat);
		visit.setUserSignature(userSignature);
        // visit.setServicePlan(servicePlan);// TODO not yet finished ServicePlan CRUD
		// visit.setSelectedMeal(selectedMeal);// TODO not yet finished Meal CRUD
		// visit.setUserBarcodeId(userBarcodeId);// TODO not yet validates there is asigned when agency accepts Patient
		visit.setUserComments(userComments);
		visit.setNotes(notes);
		visit.setStatus(VisitStatusEnum.BOOKED.name());
		return visit;
	}
	
	protected Employee createNewEmployee(Agency agency) {
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
		return employee;
	}
	
	protected Activity createNewActivity(Employee employee){
		Activity activity = new Activity();
		activity.setCreatedAt(new Timestamp(workStart.getTimeInMillis()));
		activity.setInstructorEmployee(employee);
		activity.setName(firstName);
		activity.setStatus(Integer.getInteger("1"));
		return activity; 		
	}
	
	protected VisitActivity createNewVisitActivity(Visit visit, Activity activity){
		VisitActivity visitActivity = new VisitActivity();
		visitActivity.setActivity(activity);
		visitActivity.setSeat(seat);
		visitActivity.setVisit(visit);
		return visitActivity;
	}
}
