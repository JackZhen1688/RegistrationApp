package njit.cs.demo.repository;


import java.util.Optional;

import org.springframework.stereotype.Repository;
import njit.cs.demo.domain.EmgContact;



@Repository
public interface EmgRepository extends BaseRepository<EmgContact, Long>{

	public Optional<EmgContact> findById(Long id);
	
}
