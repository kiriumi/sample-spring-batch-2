package sample.spring.batch.logic.db;

import static org.junit.Assert.*;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.spring.batch.util.SpringBatchTestSupport;

@ContextConfiguration(locations = { "/module-context_db.xml", "/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class DbJobTest extends SpringBatchTestSupport {

	@Autowired
	@Qualifier("job1")
	private Job job;

	@Test
	public void test() throws Exception {

		// テーブルに事前データ投入
		IDataSet before = new FlatXmlDataSetBuilder().build(new File(DBUNIT_PATH + "before.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), before);

		// ジョブ実行
		JobLauncherTestUtils jobLauncehr = getJobLauncherTestUtils();
		jobLauncehr.setJob(job);
		BatchStatus status = jobLauncehr.launchJob().getStatus();

		// ジョブの実行結果確認
		assertEquals(BatchStatus.COMPLETED, status);

		// 期待値のテーブル情報を取得
		IDataSet expected = new FlatXmlDataSetBuilder().build(new File(DBUNIT_PATH + "expected.xml"));
		ITable expectedTable = expected.getTable("db2");

		// 実際の値のテーブル情報を取得
		IDataSet actual = getConnection().createDataSet();
		ITable actualTable = actual.getTable("db2");

		// 比較
		Assertion.assertEquals(expectedTable, actualTable);

		// クリーン
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), before);
	}

}
