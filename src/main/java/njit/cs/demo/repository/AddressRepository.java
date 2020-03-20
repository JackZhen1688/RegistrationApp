package njit.cs.demo.repository;


import java.util.Optional;
import org.springframework.stereotype.Repository;
import njit.cs.demo.domain.Address;


@Repository
public interface AddressRepository extends BaseRepository<Address, Long>{

	public Optional<Address> findById(Long id);
	
}
