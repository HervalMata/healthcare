package com.healthcare.integration.service;

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

import com.healthcare.model.entity.Admin;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Report;
import com.healthcare.model.entity.Role;
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

	
	String imgUrl = "/img/a.jpg";
	Calendar createdAt = Calendar.getInstance();
	Integer displayOrder = 1;
	long baseId = 1;
	String dataColumns = "Data columns";
	Calendar endDate = Calendar.getInstance();
	String format = "Report format";
	String reportTitle = "Report title";
	Calendar startDate = Calendar.getInstance();

	private Admin admin;
	private Role role;
	private Company company;
	private Agency agency;
	private AgencyType agencyType;
	private Report report;
	private Long id = 7L;
	
	
	@Before
	public void setup() {
		company = companyService.save(TestEntityFactory.createNewCompany());
    	agencyType = agencyTpeService.save(TestEntityFactory.createNewAgencyType());
    	agency = agencyService.save(TestEntityFactory.createNewAgency(company,agencyType));
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
		report.setId(id);
		Mockito.when(reportRepository.save(report)).thenReturn(report);
		reportService.save(report);
		Report savedReport = reportService.findById(report.getId());
		Assert.assertNotNull(savedReport);
	}

	@Test
	public void testUpdateReport() {
		String newReportTitle = "New Report Title";

		report = createNewReport();
		report.setId(id);
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
		report.setId(id);
		Mockito.when(reportRepository.save(report)).thenReturn(report);
		reportService.save(report);
		Mockito.doNothing().when(reportRepository).delete(report.getId());
		Assert.assertNotNull(reportService.deleteById(report.getId()));
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
