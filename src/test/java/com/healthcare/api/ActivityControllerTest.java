package com.healthcare.api;

import com.healthcare.model.entity.Activity;
import com.healthcare.service.ActivityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.only;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        ActivityController controller = new ActivityController(activityService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCreate() throws Exception {
        // given
        final String activityName = "Activity name";
        final Long activityId = 1L;
        final Activity activity = new Activity();
        activity.setName(activityName);
        final Activity expected = new Activity();
        expected.setId(activityId);

        given(activityService.save(any(Activity.class)))
                .willReturn(expected);
        // when
        mockMvc.perform(
                    post("/api/activity")
                        .param("name", activityName))
                .andExpect(status().isOk())
                .andExpect(content().string(activityId.toString()));
        // then
        verify(activityService, only()).save(activity);
    }

    @Test
    public void testGet() throws Exception {
        // given
        final Long activityId = 1L;
        final Activity activity = new Activity();
        activity.setId(activityId);
        final String expectedContent = "{" +
                    "\"createdAt\":null," +
                    "\"updatedAt\":null," +
                    "\"id\":" + activityId + "," +
                    "\"name\":null," +
                    "\"status\":null," +
                    "\"instructorEmployeeId\":null," +
                    "\"timeStart\":null," +
                    "\"timeEnd\":null," +
                    "\"date\":null," +
                    "\"location\":null," +
                    "\"note\":null" +
                "}";

        given(activityService.get(anyLong()))
                .willReturn(activity);
        // when
        mockMvc.perform(
                    get("/api/activity/" + activityId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));
        // then
        verify(activityService, only()).get(activityId);
    }

    @Test
    public void testGetWithIncorrectIdReturnBadRequestStatus() throws Exception {
        // given
        final String activityId = "abc";
        // given
        mockMvc.perform(
                    get("/api/activity/" + activityId))
                .andExpect(status().isBadRequest());
        // then
        verifyZeroInteractions(activityService);
    }

    @Test
    public void testSave() throws Exception {
        // given
        final String activityName = "Activity name";
        final Long activityId = 1L;
        final Activity activity = new Activity();
        activity.setId(activityId);
        activity.setName(activityName);

        given(activityService.save(any(Activity.class)))
                .willReturn(activity);
        // when
        mockMvc.perform(
                    post("/api/activity/" + activityId)
                        .param("id", activityId.toString())
                        .param("name", activityName))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyString()));
        // then
        verify(activityService, only()).save(activity);
    }

    @Test
    public void testSaveWithIncorrectIdReturnBadRequestStatus() throws Exception {
        // given
        final String activityName = "Activity name";
        final String activityId = "abc";
        // when
        mockMvc.perform(
                post("/api/activity/" + activityId)
                        .param("id", activityId)
                        .param("name", activityName))
                .andExpect(status().isBadRequest());
        // then
        verifyZeroInteractions(activityService);
    }

    @Test
    public void testDelete() throws Exception {
        // given
        final Long activityId = 1L;
        // when
        mockMvc.perform(
                    delete("/api/activity/" + activityId))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyString()));
        // then
        verify(activityService, only()).delete(activityId);
    }

    @Test
    public void testDeleteWithIncorrectIdReturnBadRequestStatus() throws Exception {
        // given
        final String activityId = "abc";
        // when
        mockMvc.perform(
                delete("/api/activity/" + activityId))
                .andExpect(status().isBadRequest());
        // then
        verifyZeroInteractions(activityService);
    }
}
