package edu.innotech.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import edu.innotech.model.Agreement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementRepo extends CrudRepository<Agreement, Long> {
    List<Agreement> findByNumber(String number);
    @Query(value="select id from agreement a where product_id=:instanceId", nativeQuery = true)
    List<Long> getAgreementIdsByProduct(Long instanceId);
}