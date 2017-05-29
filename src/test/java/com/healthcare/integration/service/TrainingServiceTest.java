package com.healthcare.integration.service;

import com.healthcare.model.entity.Training;
import com.healthcare.service.TrainingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TrainingServiceTest {
    @Autowired
    private TrainingService trainingService;


    String title = "title";
    Timestamp startTime = Timestamp.valueOf("2017-01-01 00:00:00");
    Timestamp endTime = Timestamp.valueOf("2017-01-01 00:00:00");
    int type = 1;
    String trainer = "trainer";
    String location = "location";
    String note = "note";
    Timestamp createdAt = Timestamp.valueOf("2017-01-01 00:00:00");
    Timestamp updatedAt = Timestamp.valueOf("2017-01-01 00:00:00");

    @Test
    public void testSaveTraining() {
        Training training = createNewTraining();
        trainingService.save(training);
        Assert.assertNotNull(training.getId());
    }

    @Test
    public void testGetTraining() {
        Training training = createNewTraining();
        trainingService.save(training);
        Assert.assertNotNull(trainingService.findById(training.getId()));
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
        training.setStartTime(startTime);
        training.setEndTime(endTime);
        training.setType(type);
        training.setTrainer(trainer);
        training.setLocation(location);
        training.setNote(note);
        training.setCreatedAt(createdAt);
        training.setUpdatedAt(updatedAt);
        return training;
    }
}
