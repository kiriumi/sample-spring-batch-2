package sample.spring.batch.db.custom.dao;

import sample.spring.batch.db.generator.dto.Db1;

public interface Db1MyMapper {

	Db1 selectSample(Db1 record);

	Db1 selectSampleById(Integer id);
}
