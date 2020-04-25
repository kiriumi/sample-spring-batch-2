package sample.spring.batch.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import sample.spring.batch.db.custom.sql.Db1MyMapper;
import sample.spring.batch.db.generator.dao.Db1Mapper;
import sample.spring.batch.db.generator.dao.Db2Mapper;
import sample.spring.batch.db.generator.dto.Db1;
import sample.spring.batch.db.generator.dto.Db1Example;
import sample.spring.batch.db.generator.dto.Db2;

public class SampleTasklet implements Tasklet {

	@Autowired
	Db1Mapper db1Mapper;

	@Autowired
	Db2Mapper db2Mapper;

	@Autowired
	Db1MyMapper db1MyMapper;

	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

		Db1Example example = new Db1Example();
		List<Db1> inputs = db1Mapper.selectByExample(example);
		Db1 input = inputs.get(0);

		Db1 inputs2 = db1MyMapper.selectSample(input);

		Db2 output = new Db2();
		output.setId(input.getId());
		output.setName(input.getName());
		db2Mapper.insert(output);

		return RepeatStatus.FINISHED;
	}

}
