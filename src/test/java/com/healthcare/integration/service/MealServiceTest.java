package com.healthcare.integration.service;

import com.healthcare.model.entity.*;
import com.healthcare.model.enums.GenderEnum;
import com.healthcare.model.enums.LanguageEnum;
import com.healthcare.model.enums.StateEnum;
import com.healthcare.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AgencyTypeService agencyTypeService;

    @Autowired
    private ServicePlanService servicePlanService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;


    private Meal meal;
    private Visit visit;
    private Agency agency;
    private Company company;
    private AgencyType agencyType;
    private ServicePlan servicePlan;
    private Employee employee;
    private User user;
    private Activity activity;

    @Before
    public void setup() {
        meal = createNewMeal();
        mealService.save(meal);
        visit = createNewVisit();
        visitService.save(visit);
        agency = createNewAgency();
        agencyService.save(agency);
        company = createNewCompany();
        companyService.save(company);
        agencyType = createNewAgencyType();
        agencyTypeService.save(agencyType);
        servicePlan = createNewServicePlan();
        servicePlanService.save(servicePlan);
        employee = createNewEmployee();
        employeeService.save(employee);
        user = createNewUser();
        userService.save(user);
        activity = createNewActivity();
        activityService.save(activity);

    }

    /**
     * Meal
     */
    String mealClass = "mealClass";
    String ingredients = "ingredients";
    String notes = "notes";
    Integer verifiedByNutritionist = 1;

    /**
     * Visit
     */
    Calendar checkInTime = Calendar.getInstance();
    String selectedTable = "selectedTable";
    String selectedSeat = "selectedSeat";
    String userSignature = "userSignature";
    String userBarcodeId = "123456789";
    Calendar checkOutTime = Calendar.getInstance();
    String userComments = "userComments";
    String statusVisit = "statusVisit";

    /**
     * Agency
     */
    String agencyName = "Agency Name";

    /**
     * Company
     */
    String daysWork = "5";

    /**
     * ServicePlan
     */
    String approvedBy = "Manager";
    Calendar planStart = Calendar.getInstance();
    Calendar planEnd = Calendar.getInstance();
    String days = "Monday";
    String docUrl = "/doc/a";
    String username = "username";
    String password = "password";
    String middleName = "B";
    String phone = "1234560000";
    String email = "firstname@yahoo.com";
    String ip = "127.0.0.1";
    String secondaryPhone = "1234560001";
    String profilePhoto = "XXXXXXXXXX";
    String deviceAddress = "City ABC";
    String rememberToken = "00000";
    String levelName = "Level Name";
    long level = 1;

    /**
     * User
     */
    Long idUser = 1L;
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

    Calendar dob = Calendar.getInstance();

    Calendar eligiableStart = Calendar.getInstance();
    Calendar eligiableEnd = Calendar.getInstance();
    String insuranceEligiable = "insuranceEligiable";
    Calendar insuranceStart = Calendar.getInstance();
    Calendar insuranceEnd = Calendar.getInstance();

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

    /**
     * Employee
     */
    Long id = 1L;
    String firstName = "firstName";
    String lastName = "lastName";
    String gender = "gender";
    String socialSecurityNumber = "socialSecurityNumber";
    Calendar dateOfBirth = Calendar.getInstance();
    String physicalExam = "physicalExam";
    String certificateName = "certificateName";
    Calendar certificateStart = Calendar.getInstance();
    Calendar certificateEnd = Calendar.getInstance();
    Calendar workStart = Calendar.getInstance();
    Calendar workEnd = Calendar.getInstance();
    String position = "position";
    String manager = "manager";
    String type = "type";
    String statusEmp = "status";
    String backgroundCheck = "backgroundCheck";

    /**
     * Activity
     */

    String name = "name";
    Integer status = 1;
    Calendar createdAt = Calendar.getInstance();
    Calendar updatedAt = Calendar.getInstance();
    Calendar timeStart = Calendar.getInstance();
    Calendar timeEnd = Calendar.getInstance();
    String date = "date";
    String location = "location";
    String note = "note";

    @Test
    public void testSaveMeal() {
        Meal meal = createNewMeal();
        mealService.save(meal);
        Assert.assertNotNull(meal.getId());
    }

    @Test
    public void testGetMeal() {
        Meal meal = createNewMeal();
        mealService.save(meal);
        Assert.assertNotNull(mealService.findById(meal.getId()));
    }

    @Test
    public void testUpdateMeal() {
        String newMealClass = "mealClass 2";
        String newIngredients = "ingredients";

        Meal meal = createNewMeal();
        mealService.save(meal);
        Assert.assertEquals(meal.getMealClass(), mealClass);
        Assert.assertEquals(meal.getIngredients(), ingredients);
        Meal mealSaved = mealService.findById(meal.getId());
        mealSaved.setMealClass(newMealClass);
        mealSaved.setIngredients(newIngredients);
        mealService.save(mealSaved);
        Meal mealMofified = mealService.findById(meal.getId());
        Assert.assertEquals(mealMofified.getMealClass(), newMealClass);
        Assert.assertEquals(mealMofified.getIngredients(), newIngredients);
    }

    @Test
    public void testDeleteMeal() {
        Meal meal = createNewMeal();
        mealService.save(meal);
        Assert.assertNotNull(meal.getId());
        mealService.deleteById(meal.getId());
        Assert.assertNull(mealService.findById(meal.getId()));
    }

    private Meal createNewMeal() {
        Meal meal = new Meal();
        meal.setMealClass(mealClass);
        meal.setName(name);
        meal.setIngredients(ingredients);
        meal.setNotes(notes);
        meal.setVerifiedByNutritionist(verifiedByNutritionist);
        meal.setStatus(status);
        meal.setVisit(createNewVisit());

        return meal;
    }

    private Visit createNewVisit() {
        Visit visit = new Visit();
        visit.setAgency(createNewAgency());
        visit.setCheckInTime(new Timestamp(checkInTime.getTimeInMillis()));
        visit.setServicePlan(createNewServicePlan());
        visit.setSelectedMeal(meal);
        visit.setSelectedTable(selectedTable);
        visit.setSelectedSeat(selectedSeat);
        visit.setUserSignature(userSignature);
        visit.setUserBarcodeId(userBarcodeId);
        visit.setCheckOutTime(new Timestamp(checkOutTime.getTimeInMillis()));
        visit.setUserComments(userComments);
        visit.setNotes(notes);
        visit.setStatus(statusVisit);
        visit.setUser(createNewUser());

        return visitService.save(visit);
    }

    private Agency createNewAgency() {
        Agency agency = new Agency();
        agency.setName(agencyName);
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
        agency.setPhone(phone);
        agency.setState(state);
        agency.setTimezone(timezone);
        agency.setTrackingMode(trackingMode);
        agency.setZipcode(zipcode);
        return agencyService.save(agency);
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
        company.setDaysWork(daysWork);
        return companyService.save(company);
    }

    protected AgencyType createNewAgencyType() {
        AgencyType agencyType = new AgencyType();
        agencyType.setName("Agency Type Name");
        agencyType.setStatus(1);
        return agencyTypeService.save(agencyType);
    }

    private ServicePlan createNewServicePlan() {
        ServicePlan servicePlan = new ServicePlan();
        servicePlan.setApprovedBy(approvedBy);
        servicePlan.setDays(days);
        servicePlan.setDocUrl(docUrl);
        servicePlan.setEmployee(employee);
        servicePlan.setPlanEnd(new Timestamp(planEnd.getTimeInMillis()));
        servicePlan.setPlanStart(new Timestamp(planStart.getTimeInMillis()));
        servicePlan.setUser(createNewUser());
        return servicePlanService.save(servicePlan);
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
        user.setId(idUser);
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
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setSocialSecurityNumber(socialSecurityNumber);
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
        employee.setAgency(createNewAgency());
        return employeeService.save(employee);
    }

    private Activity createNewActivity() {
        Activity activity = new Activity();
        activity.setName(name);
        activity.setStatus(status);
        //activity.setCreatedAt(new Timestamp(createdAt.getTimeInMillis()));
        //activity.setUpdatedAt(new Timestamp(updatedAt.getTimeInMillis()));
        activity.setInstructorEmployee(createNewEmployee());
        activity.setTimeStart(String.valueOf(new Time(timeStart.getTimeInMillis())));
        activity.setTimeEnd(String.valueOf(new Time(timeEnd.getTimeInMillis())));
        activity.setDate(date);
        activity.setLocation(location);
        activity.setNote(note);

        return activityService.save(activity);
    }
}
