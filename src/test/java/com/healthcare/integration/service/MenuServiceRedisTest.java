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

import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Menu;
import com.healthcare.model.entity.Role;
import com.healthcare.repository.MenuRepository;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.MenuService;
import com.healthcare.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuServiceRedisTest {
	@Autowired
	private MenuService menuService;

	@MockBean
	private MenuRepository menuRepository;

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

	private Role role;
	private Company company;
	private Agency agency;
	private AgencyType agencyType;
	private Menu menu;
	private Long id = 7L;
	
	
	@Before
	public void setup() {
		company = TestEntityFactory.createNewCompany();
		companyService.save(company);
    	agencyType = TestEntityFactory.createNewAgencyType();
    	agencyTpeService.save(agencyType);
    	agency = TestEntityFactory.createNewAgency(company,agencyType);
        agencyService.save(agency);
    	role = TestEntityFactory.createNewRole(agency);
		roleService.save(role);
	}

	@After
	public void rollback() {
		if(menu!=null){
			menuService.deleteById(menu.getId());
		}
		roleService.deleteById(role.getId());
        agencyService.deleteById(agency.getId());
        agencyTpeService.deleteById(agencyType.getId());
        companyService.deleteById(company.getId());
	}

	@Test
	public void testSaveMenu() {
		menu = createNewMenu();
		menu.setId(id);
		Mockito.when(menuRepository.save(menu)).thenReturn(menu);
		menuService.save(menu);
		Menu savedMenu = menuService.findById(menu.getId());
		Assert.assertNotNull(savedMenu);
	}

	@Test
	public void testUpdateMenu() {
		String newImgUrl = "/img/new.jpg";

		menu = createNewMenu();
		menu.setId(id);
		Mockito.when(menuRepository.save(menu)).thenReturn(menu);
		menuService.save(menu);
		Menu menuSaved = menuService.findById(menu.getId());
		menuSaved.setImgUrl(newImgUrl);
		Mockito.when(menuRepository.save(menuSaved)).thenReturn(menuSaved);
		menuService.save(menuSaved);
		Menu menuMofified = menuService.findById(menu.getId());
		Assert.assertEquals(menuMofified.getImgUrl(), newImgUrl);
	}

	@Test
	public void testDeleteMenu() {
		Menu menu = createNewMenu();
		menu.setId(id);
		Mockito.when(menuRepository.save(menu)).thenReturn(menu);
		menuService.save(menu);
		Mockito.doNothing().when(menuRepository).delete(menu.getId());
		Assert.assertNotNull(menuService.deleteById(menu.getId()));
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

}
