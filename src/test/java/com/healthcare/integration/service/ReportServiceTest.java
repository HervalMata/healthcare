package com.healthcare.integration.service;

import java.sql.Timestamp;
import java.util.Calendar;

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
import com.healthcare.model.entity.Report;
import com.healthcare.model.entity.Role;
import com.healthcare.service.AdminService;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.ReportService;
import com.healthcare.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReportServiceTest {
	@Autowired
	private ReportService reportService;

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

	Calendar createdAt = Calendar.getInstance();
	Integer displayOrder = 1;
	long baseId = 1;
	String dataColumns = "Data columns";
	Calendar endDate = Calendar.getInstance();
	String format = "Report format";
	String reportTitle = "Report title";
	Calendar startDate = Calendar.getInstance();

	Admin admin;
	Role role;
	Company company;
	private Agency agency;
	private AgencyType agencyType;
	private Report report;
	
	
	@Before
	public void setup() {
		company = companyService.save(TestEntityFactory.createNewCompany());
		agencyType = agencyTpeService.save(TestEntityFactory.createNewAgencyType());
		agency = agencyService.save(TestEntityFactory.createNewAgency(company, agencyType));
		role = roleService.save(TestEntityFactory.createNewRole(agency));
		admin = adminService.save(TestEntityFactory.createNewAdmin(role));
    	report = null;
	}

	@After
	public void rollback() {
		if(report!=null){
			reportService.deleteById(report.getId());
		}
		adminService.deleteById(admin.getId());
		roleService.deleteById(role.getId());
        agencyService.deleteById(agency.getId());
        agencyTpeService.deleteById(agencyType.getId());
        companyService.deleteById(company.getId());
	}

	@Test
	public void testSaveReport() {
		report = createNewReport();
		reportService.save(report);
		Assert.assertNotNull(report.getId());
	}

	@Test
	public void testGetReport() {
		report = createNewReport();
		reportService.save(report);
		Assert.assertNotNull(reportService.findById(report.getId()));
	}

	@Test
	public void testUpdateReport() {
		String newReportTitle = "New Report Title";

		report = createNewReport();
		reportService.save(report);
		Assert.assertEquals(report.getReportTitle(), reportTitle);
		Report reportSaved = reportService.findById(report.getId());
		reportSaved.setReportTitle(newReportTitle);
		reportService.save(reportSaved);
		Report reportMofified = reportService.findById(report.getId());
		Assert.assertEquals(reportMofified.getReportTitle(), newReportTitle);
	}

	@Test
	public void testDeleteReport() {
		Report report = createNewReport();
		reportService.save(report);
		Assert.assertNotNull(report.getId());
		reportService.deleteById(report.getId());
		Assert.assertNull(reportService.findById(report.getId()));
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
	
}
