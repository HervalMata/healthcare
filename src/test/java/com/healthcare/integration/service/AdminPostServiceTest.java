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
import com.healthcare.model.entity.AdminPost;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Role;
import com.healthcare.service.AdminPostService;
import com.healthcare.service.AdminService;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminPostServiceTest {
	@Autowired
	private AdminPostService adminPostService;

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

	Calendar postDate = Calendar.getInstance();
	String postText = "This is post text";

	private Admin admin;
	private Role role;
	private Company company;
	private Agency agency;
	private AgencyType agencyType;
	private AdminPost adminPost =null;
	
	@Before
	public void setup() {
		company = companyService.save(TestEntityFactory.createNewCompany());
    	agencyType = agencyTpeService.save(TestEntityFactory.createNewAgencyType());
    	agency = agencyService.save(TestEntityFactory.createNewAgency(company,agencyType));
    	role = roleService.save(TestEntityFactory.createNewRole(agency));
    	admin = adminService.save(TestEntityFactory.createNewAdmin(role));
    	adminPost = null;
	}
	
	@After
	public void rollback() {
		if(adminPost!=null){
			adminPostService.deleteById(adminPost.getId());
		}
		adminService.deleteById(admin.getId());
		roleService.deleteById(role.getId());
        agencyService.deleteById(agency.getId());
        agencyTpeService.deleteById(agencyType.getId());
        companyService.deleteById(company.getId());
	}

	@Test
	public void testSaveAdminPost() {
		adminPost = createNewAdminPost();
		adminPostService.save(adminPost);
		Assert.assertNotNull(adminPost.getId());
	}

	@Test
	public void testGetAdminPost() {
		adminPost = createNewAdminPost();
		adminPostService.save(adminPost);
		Assert.assertNotNull(adminPostService.findById(adminPost.getId()));
	}

	@Test
	public void testUpdateAdminPost() {
		String newPostText = "This is new post text";

		adminPost = createNewAdminPost();
		adminPostService.save(adminPost);
		Assert.assertEquals(adminPost.getPostText(), postText);
		AdminPost adminPostSaved = adminPostService.findById(adminPost.getId());
		adminPostSaved.setPostText(newPostText);
		adminPostService.save(adminPostSaved);
		AdminPost adminPostMofified = adminPostService.findById(adminPost.getId());
		Assert.assertEquals(adminPostMofified.getPostText(), newPostText);
	}

	@Test
	public void testDeleteAdminPost() {
		AdminPost adminPost = createNewAdminPost();
		adminPostService.save(adminPost);
		Assert.assertNotNull(adminPost.getId());
		adminPostService.deleteById(adminPost.getId());
		Assert.assertNull(adminPostService.findById(adminPost.getId()));
	}

	private AdminPost createNewAdminPost() {
		AdminPost adminPost = new AdminPost();
		adminPost.setPostDate(new Timestamp(postDate.getTimeInMillis()));
		adminPost.setPostText(postText);
		adminPost.setStatus(1);
		adminPost.setAdmin(admin);
		return adminPost;
	}
}
