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

import com.healthcare.model.entity.Training;
import com.healthcare.service.TrainingService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TrainingServiceTest {
    @Autowired
    private TrainingService trainingService;


    String title = "title";
    Calendar startTime = Calendar.getInstance();
    Calendar endTime = Calendar.getInstance();
    int type = 1;
    String trainer = "trainer";
    String location = "location";
    String note = "note";

	private Long id;

	@Before
	public void init(){
		id=0L;
	}
	
	@After
	public void rollback() {
		if(id!=0L)
			trainingService.deleteById(id);
	}
	
    @Test
    public void testSaveTraining() {
        Training training = createNewTraining();
        trainingService.save(training);
        Assert.assertNotNull(training.getId());
        id=training.getId();
    }

    @Test
    public void testGetTraining() {
        Training training = createNewTraining();
        trainingService.save(training);
        Assert.assertNotNull(trainingService.findById(training.getId()));
        id=training.getId();
    }

    @Test
    public void testUpdateTraining() {
        String newTrainer = "Trainer 2";
        String newLocation = "Location 2";

        Training training = createNewTraining();
        trainingService.save(training);
        Assert.assertEquals(training.getTrainer(), trainer);
        Assert.assertEquals(training.getLocation(), location);
        Training trainingSaved = trainingService.findById(training.getId());
        trainingSaved.setTrainer(newTrainer);
        trainingSaved.setLocation(newLocation);
        trainingService.save(trainingSaved);
        Training trainingMofified = trainingService.findById(training.getId());
        Assert.assertEquals(trainingMofified.getTrainer(), newTrainer);
        Assert.assertEquals(trainingMofified.getLocation(), newLocation);
        id=trainingMofified.getId();
    }

    @Test
    public void testDeleteTraining() {
        Training training = createNewTraining();
        trainingService.save(training);
        Assert.assertNotNull(training.getId());
        trainingService.deleteById(training.getId());
        Assert.assertNull(trainingService.findById(training.getId()));
    }

    private Training createNewTraining() {
        Training training = new Training();
        training.setTitle(title);
        training.setStartTime(new Timestamp(startTime.getTimeInMillis()));
        training.setEndTime(new Timestamp(endTime.getTimeInMillis()));
        training.setType(type);
        training.setTrainer(trainer);
        training.setLocation(location);
        training.setNote(note);

        return training;
    }
}
