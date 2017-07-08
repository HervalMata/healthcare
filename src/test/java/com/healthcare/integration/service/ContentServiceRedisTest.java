package com.healthcare.integration.service;

import java.util.Base64;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.Content;
import com.healthcare.repository.ContentRepository;
import com.healthcare.service.ContentService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ContentServiceRedisTest {
    @MockBean
    private ContentRepository contentRepository;

    @Autowired
    private ContentService contentService;

    @Before
    public void setup() {
    }
    
    // Remove data added during test from redis once test case executed successfully
    public void cleanup(Long id){
    	  contentService.deleteById(id);
    }
    

    @Test
    public void testSaveContentToRedisAndRetrievedItFromRedis() {
    	//given
		final Content content = getContent();
	    content.setId(100L);
	    
	    //Mock
        Mockito.when(contentRepository.save(content)).thenReturn(content);
        contentService.save(content);
        
        //Execute
        Content contentSaved = contentService.findById(100L);
        
        //Assert
        Assert.assertNotNull(contentSaved);
        cleanup(100L);
    }

    @Test
    public void testUpdateContentToRedis() {
    	String pageDescUpdated = "page description updated";
    	
    	//given
		final Content content = getContent();
	    content.setId(100L);
        
	    //Mock
	    Mockito.when(contentRepository.save(content)).thenReturn(content);
        contentService.save(content);
        
        Content savedContentInRedis = contentService.findById(content.getId());
        savedContentInRedis.setPageDescription(pageDescUpdated);
        
        Mockito.when(contentRepository.save(savedContentInRedis)).thenReturn(savedContentInRedis);
        contentService.save(savedContentInRedis);

        Content modifiedContentFromRedis = contentService.findById(content.getId());
        
        //Assert
        Assert.assertEquals(modifiedContentFromRedis.getPageDescription(), pageDescUpdated);
        cleanup(100L);
    }

    @Test
    public void testDeleteContentFromRedis() {
    	//given
		final Content content = getContent();
	    content.setId(100L);
        
        Mockito.doNothing().when(contentRepository).delete(100L);
        Assert.assertNotNull(contentService.deleteById(content.getId()));
    }
    

    
    private Content getContent() {
		final Content content = new Content();
        content.setId(1L);
        content.setAccessKey("New Acess Key");
        content.setBaseId(1L);
        content.setContent(Base64.getEncoder().encode("SampleString".getBytes()));
        content.setPageDescription("page desc");
        content.setPageKeyword("page keyword");
        content.setTitle("title");
        content.setPageTitle("page title");
        content.setStatus(1);
        return content;
	}

	
}
