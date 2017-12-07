package com.affinitas.test.editable.controller;

import com.affinitas.test.editable.model.Profile;
import com.affinitas.test.editable.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    private Profile profileMock1 = new Profile(1L, "Henrique", "Henrique Eichler");
    private Profile profileMock2 = new Profile(2L, "Anne", "Anne Carolliny");
    private List<Profile> profileListMock = new ArrayList<>(Arrays.asList(profileMock1, profileMock2));

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfileService profileService;

    @Test
    public void findAll_returns_list_of_profiles_as_json_array() throws Exception {

        // Prepare mock objects
        when(profileService.findAll()).thenReturn(profileListMock);

        // Do the request
        final ResultActions resultActions = mvc.perform(get("/profiles/"));

        // Verify the results
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(profileMock1.getId()))
                .andExpect(jsonPath("$[1].id").value(profileMock2.getId()));
    }

    @Test
    public void findById_returns_profile_as_json_object() throws Exception {

        // Prepare mock objects
        when(profileService.findById(anyLong())).thenReturn(profileMock1);

        // Do the request
        final ResultActions resultActions = mvc.perform(get("/profiles/1"));

        // Verify the results
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(profileMock1.getId()))
                .andExpect(jsonPath("displayName").value(profileMock1.getDisplayName()))
                .andExpect(jsonPath("realName").value(profileMock1.getRealName()));
    }

    @Test
    public void save_returns_profile_as_json_object() throws Exception {

        // Prepare mock objects
        when(profileService.save(any(Profile.class))).thenReturn(profileMock1);

        // Do the request
        final ResultActions resultActions = mvc.perform(post("/profiles/")
                .content("{\"displayName\":\"Jose\", \"realName\":\"Jose Maria\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        // Verify the results
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(profileMock1.getId()))
                .andExpect(jsonPath("displayName").value(profileMock1.getDisplayName()))
                .andExpect(jsonPath("realName").value(profileMock1.getRealName()));
    }

    @Test
    public void delete_returns_http_ok() throws Exception {

        // Do the request
        final ResultActions resultActions = mvc.perform(delete("/profiles/", profileMock1)
                .content("{\"id\":1, \"displayName\":\"Jose\", \"realName\":\"Jose Maria\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        // Verify the results
        resultActions
                .andExpect(status().isOk());
    }
}