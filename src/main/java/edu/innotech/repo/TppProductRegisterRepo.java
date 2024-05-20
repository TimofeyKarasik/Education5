package edu.innotech.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import edu.innotech.model.TppProductRegister;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TppProductRegisterRepo extends CrudRepository<TppProductRegister, Long> {
    @Query(value="select id from tpp_product_register tpr where tpr.product_id=:instanceId", nativeQuery = true)
    List<Long> getRegisterIdsByProduct(Long instanceId);


    Optional<TppProductRegister> findFirstByProductIdAndType(Long productId, String type);
}