package com.healthcare.integration.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.EntityFactory;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.repository.VisitRepository;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.UserService;
import com.healthcare.service.VisitService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VisitServiceRedisTest extends EntityFactory {
	@MockBean
	private VisitRepository visitRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	public CompanyService companyService;

	@Autowired
	public AgencyTypeService agencyTypeService;

	@Autowired
	private VisitService visitService;

	@Autowired
	private RedisTemplate<String, Visit> redisTemplate;

	private Company company;
	private AgencyType agencyType;
	private Agency agency;
	private User user;

	@Before
	public void setup() {
		init();
		company = createNewCompany();
		companyService.save(company);
		agencyType = createNewAgencyType();
		agencyTypeService.save(agencyType);
		agency = createNewAgency(agencyType, company);
		agencyService.save(agency);
		user = createNewUser();
		userService.save(user);
		redisTemplate.delete(Visit.class.getSimpleName());
	}


	private Long id = 10L;

	@After
	public void rollback() {
		visitService.deleteById(id);
		userService.deleteById(user.getId());
		agencyService.deleteById(agency.getId());
		agencyTypeService.deleteById(agencyType.getId());
		companyService.deleteById(company.getId());
	}


	@Test
	public void shouldSaveAVisitToRedisAndRetrievedItFromRedis() {
		Visit visit = createNewVisit(user, agency);
		visit.setId(id);
		Mockito.when(visitRepository.save(visit)).thenReturn(visit);
		visitService.save(visit);
		Visit visitSaved = visitService.findById(id);
		Assert.assertNotNull(visitSaved);
	}

	@Test
	public void shouldUpdateAVisitToRedis() {
		String newUserSignature = "Dr. Abc Junior";
		String newNotes = "seems so quiet";

		Visit visit = createNewVisit(user, agency);
		visit.setId(id);
		Mockito.when(visitRepository.save(visit)).thenReturn(visit);
		visitService.save(visit);
		Visit visitSaved = visitService.findById(visit.getId());
		visitSaved.setUserSignature(newUserSignature);
		visitSaved.setNotes(newNotes);
		Mockito.when(visitRepository.save(visitSaved)).thenReturn(visitSaved);
		visitService.save(visitSaved);
		Visit visitMofified = visitService.findById(visit.getId());
		Assert.assertEquals(visitMofified.getUserSignature(), newUserSignature);
		Assert.assertEquals(visitMofified.getNotes(), newNotes);
	}

	@Test
	public void shouldDeleteAVisit() {
		Visit visit = createNewVisit(user, agency);
		visit.setId(id);
		Mockito.when(visitRepository.save(visit)).thenReturn(visit);
		visitService.save(visit);
		Mockito.doNothing().when(visitRepository).delete(id);
		Assert.assertNotNull(visitService.deleteById(visit.getId()));
	}

	@Test
	public void souldFindAll() {
		Visit visit = createNewVisit(user, agency);
		visit.setId(55L);
		Mockito.when(visitRepository.save(visit)).thenReturn(visit);
		visit = visitService.save(visit);

		Visit visit2 = createNewVisit(user, agency);
		visit2.setId(56L);
		Mockito.when(visitRepository.save(visit2)).thenReturn(visit2);
		visit2 = visitService.save(visit2);

		Visit visit3 = createNewVisit(user, agency);
		visit3.setId(57L);
		Mockito.when(visitRepository.save(visit3)).thenReturn(visit3);
		visit3 =visitService.save(visit3);

		List<Visit> list= visitService.findAll();
		assertNotNull(list);
		assertEquals(3, list.size());

		//Clean Up redis
		visitService.deleteById(55L);
		visitService.deleteById(56L);
		visitService.deleteById(57L);
	}

}
