package com.affinitas.test.editable.controller;

import com.affinitas.test.editable.exception.ProfileNotFound;
import com.affinitas.test.editable.model.Profile;
import com.affinitas.test.editable.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
@CrossOrigin(origins = "http://192.168.1.3:4200")
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(final ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Profile> findAll() {
        return profileService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = {"Accept"})
    @ResponseStatus(value = HttpStatus.OK)
    public Profile findById(@PathVariable("id") final Long id) throws ProfileNotFound {
        return profileService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Profile save(@RequestBody final Profile profile) {
        return profileService.save(profile);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@RequestBody final Profile profile) {
        profileService.delete(profile);
    }
}