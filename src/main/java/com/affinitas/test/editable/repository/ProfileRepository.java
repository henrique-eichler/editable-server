package com.affinitas.test.editable.repository;

import com.affinitas.test.editable.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    List<Profile> findAll();

    Profile findById(final Long id);

    Profile save(final Profile profile);

    void delete(final Profile profile);

    // @Modifying
    // @Query(value = "UPDATE link l set l.click_count = l.click_count + 1 WHERE l.short_url = :shortUrl ", nativeQuery = true)
    // @Transactional
    // void incrementClickCountByOne(@Param("shortUrl") String shortUrl);
}