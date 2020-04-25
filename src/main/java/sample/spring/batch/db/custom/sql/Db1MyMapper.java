package sample.spring.batch.db.custom.sql;

import sample.spring.batch.db.generator.dto.Db1;

public interface Db1MyMapper {

	Db1 selectSample(Db1 record);
}
