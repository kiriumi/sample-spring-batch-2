package sample.spring.batch.logic.restart;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;

import sample.spring.batch.file.dto.Member;

public class RestartItemReader extends FlatFileItemReader<Member> {

	private final String KEY_OCCUR_EXCEPTION = "occuredException";
	private static Boolean occurException = false;

	private int index = 0;

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) {

		ExecutionContext context = stepExecution.getExecutionContext();

		occurException = (Boolean) context.get(KEY_OCCUR_EXCEPTION);

		if (occurException == null || !occurException) {
			occurException = true;

		} else {

			occurException = false;
		}

		context.put(KEY_OCCUR_EXCEPTION, occurException);
	}

	@Override
	protected Member doRead() throws Exception {

		if (occurException && ++index == 3) {
			throw new Exception("意図的な例外");
		}

		return super.doRead();
	}

}
