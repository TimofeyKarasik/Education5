package edu.innotech.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import edu.innotech.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {
    @Query(value="select a.* from account a where account_pool_id=:poolId and bussy=false  order by id limit 10", nativeQuery = true)
    List<Account> findFirstPageFreeAcc(Long poolId);
    @Query(value="select a.* from account a where account_pool_id=:poolId and bussy=false and id > :lastId  order by id limit 10", nativeQuery = true)
    List<Account> findNextPageFreeAcc(Long poolId, Long lastId);

    @Query(value="select a.* from account a where id=:accountId for update nowait", nativeQuery = true)
    Optional<Account> findByIdLock(Long accountId);



}