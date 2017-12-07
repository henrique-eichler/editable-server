package com.affinitas.test.editable.repository;

import com.affinitas.test.editable.model.Profile;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TestEntityManager entityManager;

    @After
    public void tearDown() throws Exception {
        profileRepository.deleteAll();
    }

    @Test
    public void save_return_one_profile_with_id() {
        final Profile profileToSave = new Profile("Jose", "Jose Maria");

        final Profile profileSaved = profileRepository.save(profileToSave);

        assertThat(profileSaved).isNotNull();
        assertThat(profileSaved.getId()).isNotZero();
        assertThat(profileSaved.getDisplayName()).isEqualTo(profileToSave.getDisplayName());
        assertThat(profileSaved.getRealName()).isEqualTo(profileToSave.getRealName());
    }

    @Test
    public void findById_return_one_profile() {
        final Profile profileToSave = new Profile("Jose", "Jose Maria");

        final Profile profileSaved = profileRepository.save(profileToSave);

        final Profile profile = profileRepository.findById(profileSaved.getId());

        assertThat(profile).isNotNull();
        assertThat(profile.getId()).isEqualTo(profileSaved.getId());
        assertThat(profile.getDisplayName()).isEqualTo(profileToSave.getDisplayName());
        assertThat(profile.getRealName()).isEqualTo(profileToSave.getRealName());
    }

    @Test
    public void findAll_return_list_of_profiles() {
        final Profile profileToSave1 = new Profile("Jose", "Jose Maria");
        final Profile profileToSave2 = new Profile("Maria", "Maria Pereira");

        final Profile profileSaved1 = profileRepository.save(profileToSave1);
        final Profile profileSaved2 = profileRepository.save(profileToSave2);

        final List<Profile> profileList = profileRepository.findAll();

        assertThat(profileList).isNotNull();
        assertThat(profileList.size()).isEqualTo(2);

        final Profile profile1 = profileList.get(0);
        assertThat(profile1.getId()).isEqualTo(profileSaved1.getId());
        assertThat(profile1.getDisplayName()).isEqualTo(profileToSave1.getDisplayName());
        assertThat(profile1.getRealName()).isEqualTo(profileToSave1.getRealName());

        final Profile profile2 = profileList.get(1);
        assertThat(profile2.getId()).isEqualTo(profileSaved2.getId());
        assertThat(profile2.getDisplayName()).isEqualTo(profileToSave2.getDisplayName());
        assertThat(profile2.getRealName()).isEqualTo(profileToSave2.getRealName());
    }

    // @Test
    // public void findByShortUrl_shouldFetchUrlMatch() {
    //     linkRepository.save(new Link(SHORT_URL, FULL_URL));
    //     Link foundLink = linkRepository.findByShortUrl(SHORT_URL);
    //     assertThat(foundLink).isNotNull();
    //     assertThat(foundLink.getFullUrl()).isEqualTo(FULL_URL);
    // }
    //
    // @Test
    // public void updateClickCount_shouldIncreaseClickCountForUrl() {
    //     linkRepository.save(new Link(SHORT_URL, FULL_URL, 1));
    //     entityManager.flush();
    //
    //     linkRepository.incrementClickCountByOne(SHORT_URL);
    //
    //     Link updated = this.entityManager.persistFlushFind(linkRepository.findOne(SHORT_URL));
    //     assertThat(updated.getClickCount()).isEqualTo(2);
    // }
}