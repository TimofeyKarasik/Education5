package edu.innotech.repo;

import org.springframework.data.repository.CrudRepository;
import edu.innotech.model.TppProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TppProductRepo extends CrudRepository<TppProduct, Long> {
    List<TppProduct> findByNumber(String number);
}