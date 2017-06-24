package com.healthcare.integration.service;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.healthcare.DbUnitIntegrationTestConfiguration;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.CareGiver;
import com.healthcare.model.entity.Company;
import com.healthcare.service.CareGiverService;

@RunWith(SpringRunner.class)
@TestExecutionListeners(
        value = {DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@DatabaseSetup(value = "/dataset/service/CareGiverServiceImplIntegrationTest.xml")
@ContextConfiguration(classes = {DbUnitIntegrationTestConfiguration.class})
@Transactional
@SpringBootTest
public class CareGiverServiceImplTest {

    @Autowired
    private CareGiverService careGiverService;

    @Autowired
    private EntityManager em;

 
    @Test
	public void testCreateCareGiver() {
		// given
		final Company company = getCompany();
		final Agency agency = getAgency();
		final CareGiver careGiver = getCareGiver(company,agency);

		// when
		CareGiver result = careGiverService.save(careGiver);
		// then
		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
	}

   @Test
    @ExpectedDatabase(
            value = "/dataset/service/CareGiverServiceImplIntegrationTest.testUpdate.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testUpdateCareGiver() {
        // given
	   final Company company = getCompany();
       final Agency agency = getAgency();
       final CareGiver careGiver = getCareGiver(company, agency);
       careGiver.setFirstName("first name updated");
       careGiver.setId(100L);
       
       // when
        CareGiver result = careGiverService.save(careGiver);
        // then
        assertThat(result, notNullValue());

        em.flush();
    }

    @Test
    public void testFindById() {
        // given
        final Long careGiverId = 100L;
        // when
        CareGiver result = careGiverService.findById(careGiverId);
        // then
        assertThat(result, notNullValue());
    }

    @Test
    public void testFindAll() {
        // when
        List<CareGiver> result = careGiverService.findAll();
        // then
        assertTrue(result.size()>0);
    }
    
    @Test
    @ExpectedDatabase(
            value = "/dataset/service/CareGiverServiceImplIntegrationTest.testDelete.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testDeleteById() {
        // given
        final Long careGiverId = 100L;
        // when
        careGiverService.deleteById(careGiverId);
        // then
        em.flush();
    }
    
    
    private CareGiver getCareGiver(final Company company,final Agency agency) {
		final CareGiver careGiver = new CareGiver();
        careGiver.setFirstName("First Name");
        careGiver.setLastName("Last Name");
        careGiver.setCareGiverType(1L);
        careGiver.setUsername("testUser");
        careGiver.setPassword("password");
        careGiver.setPhone("1234567890");
        careGiver.setCertificate("Medical Certificate");
        careGiver.setCertificateStart(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        careGiver.setCertificateEnd(Timestamp.valueOf("2007-10-23 10:10:10.0"));
        careGiver.setAgency(agency);
        careGiver.setCompany(company);
		return careGiver;
	}

	private Agency getAgency() {
		final Long agencyId = 100L;
        final Agency agency = new Agency();
        agency.setId(agencyId);
		return agency;
	}

	private Company getCompany() {
		final Long companyId = 100L;
        final Company company = new Company();
        company.setId(companyId);
		return company;
	}
}