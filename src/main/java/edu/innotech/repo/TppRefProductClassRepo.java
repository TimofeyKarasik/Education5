package edu.innotech.repo;

import org.springframework.data.repository.CrudRepository;
import edu.innotech.model.TppRefProductClass;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TppRefProductClassRepo extends CrudRepository<TppRefProductClass, Long> {
    Optional<TppRefProductClass> findByValue(String value);
}
