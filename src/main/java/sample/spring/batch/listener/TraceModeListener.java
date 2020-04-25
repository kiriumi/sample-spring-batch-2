package sample.spring.batch.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class TraceModeListener implements JobExecutionListener {

	@Override
	public void beforeJob(final JobExecution jobExecution) {

		String traceMode = jobExecution.getJobParameters().getString("traceMode");

		if (traceMode != null && traceMode.equals("1")) {
			changeLogLevel("sample.spring.batch.db", Level.TRACE);
		}
	}

	@Override
	public void afterJob(final JobExecution jobExecution) {
	}

	private void changeLogLevel(final String loggerName, final Level logLevel) {

		LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
		Configuration configuration = loggerContext.getConfiguration();
		LoggerConfig loggerConfig = configuration.getLoggerConfig(loggerName);

		loggerConfig.setLevel(Level.TRACE);
		loggerContext.updateLoggers();
	}

}
