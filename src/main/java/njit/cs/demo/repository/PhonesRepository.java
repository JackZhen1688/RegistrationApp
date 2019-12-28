package njit.cs.demo.repository;

import org.springframework.stereotype.Repository;
import njit.cs.demo.domain.Phones;


@Repository
public interface PhonesRepository extends BaseRepository<Phones, Long> {

}
