package njit.cs.demo.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import njit.cs.demo.domain.PhoneType;


@Repository
public interface PhoneTypeRepository extends BaseRepository<PhoneType, Long>{

	public List<PhoneType> findAll();
	
}
