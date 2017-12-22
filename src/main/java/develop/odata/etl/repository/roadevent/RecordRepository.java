package develop.odata.etl.repository.roadevent;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import develop.odata.etl.domain.roadevent.Record;

@RepositoryRestResource(collectionResourceRel = "road", path = "road")
public interface RecordRepository extends MongoRepository<Record, String>, QueryByExampleExecutor<Record> {

	
	Slice<Record>  findByDesLikeAndHappentimeBetween (String des , Date d1,Date d2,Pageable pageable);
	
	Slice<Record>  findByRoadtypeLikeAndDesLikeAndHappentimeBetween (String roadtype,String des , Date d1,Date d2,Pageable pageable);
	List<Record>  findByRoadtypeLikeAndDesLikeAndHappentimeBetween (String roadtype,String des , Date d1,Date d2 );
	
}
