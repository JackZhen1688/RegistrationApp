package njit.cs.demo.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import njit.cs.demo.domain.PersonType;


@Repository
public interface PersonTypeRepository extends BaseRepository<PersonType, Long>{

	public List<PersonType> findAll();
	
}
