package com.affinitas.test.editable.service;

import com.affinitas.test.editable.exception.ProfileNotFound;
import com.affinitas.test.editable.model.Profile;
import com.affinitas.test.editable.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public ProfileService(final ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public Profile findById(final Long id) throws ProfileNotFound {
        final Profile profile = profileRepository.findById(id);
        if (profile != null) {
            return profile;
        } else {
            throw new ProfileNotFound();
        }
    }

    public Profile save(final Profile profile) {
        return profileRepository.save(profile);
    }

    public void delete(final Profile profile) {
        profileRepository.delete(profile);
    }
}