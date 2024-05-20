package edu.innotech.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import edu.innotech.model.TppRefProductRegisterType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TppRefProductRegisterTypeRepo extends CrudRepository<TppRefProductRegisterType, Long> {
    @Query("SELECT a FROM TppRefProductRegisterType a WHERE a.value = :accountType")
    List<TppRefProductRegisterType> findByAccountType(@Param("accountType") String accountType);

    @Query("SELECT b FROM TppRefProductClass a, TppRefProductRegisterType b WHERE a.value = b.productClassCode and a.value = :productCode and b.accountType = :accountType")
    List<TppRefProductRegisterType> findByProductCode(@Param("productCode") String productCode, @Param("accountType") String accountType);

}