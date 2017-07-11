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

import com.healthcare.model.entity.Training;
import com.healthcare.repository.TrainingRepository;
import com.healthcare.service.TrainingService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TrainingServiceRedisTest {
    @MockBean
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingService trainingService;

    String title = "title";
    Calendar startTime = Calendar.getInstance();
    Calendar endTime = Calendar.getInstance();
    int type = 1;
    String trainer = "trainer";
    String location = "location";
    String note = "note";

    @Before
    public void setup() {
        startTime.set(Calendar.YEAR, 2017);
        startTime.set(Calendar.MONTH, 1);
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        endTime.set(Calendar.YEAR, 2017);
        endTime.set(Calendar.MONTH, 12);
        endTime.set(Calendar.DAY_OF_MONTH, 31);
    }


	private Long id = 1L;

	@After
	public void rollback() {
		trainingService.deleteById(id);
	}

    @Test
    public void shouldSaveATrainingToRedisAndRetrievedItFromRedis() {
        Training training = createNewTraining();
        training.setId(id);
        Mockito.when(trainingRepository.save(training)).thenReturn(training);
        trainingService.save(training);
        Training trainingSaved = trainingService.findById(id);
        Assert.assertNotNull(trainingSaved);
    }

    @Test
    public void shouldUpdateATrainingToRedis() {
        String newTitle = "title2";
        String newLocation = "location2";

        Training training = createNewTraining();
        training.setId(id);
        Mockito.when(trainingRepository.save(training)).thenReturn(training);
        trainingService.save(training);
        Training trainingSaved = trainingService.findById(training.getId());
        trainingSaved.setTitle(newTitle);
        trainingSaved.setLocation(newLocation);
        Mockito.when(trainingRepository.save(trainingSaved)).thenReturn(trainingSaved);
        trainingService.save(trainingSaved);
        Training trainingMofified = trainingService.findById(training.getId());
        Assert.assertEquals(trainingMofified.getTitle(), newTitle);
        Assert.assertEquals(trainingMofified.getLocation(), newLocation);
    }

    @Test
    public void shouldDeleteATraining() {
        Training training = createNewTraining();
        training.setId(id);
        Mockito.when(trainingRepository.save(training)).thenReturn(training);
        trainingService.save(training);
        Mockito.doNothing().when(trainingRepository).delete(id);
        Assert.assertNotNull(trainingService.deleteById(training.getId()));
    }

    private Training createNewTraining() {
        Training training = new Training();
        training.setTitle(title);
        training.setStartTime(new Timestamp(startTime.getTimeInMillis()));
        training.setEndTime(new Timestamp(endTime.getTimeInMillis()));
        training.setType(type);

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
