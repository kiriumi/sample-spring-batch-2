package sample.spring.batch.example;

import junit.framework.TestCase;
import sample.spring.batch.example.ExampleItemWriter;

public class ExampleItemWriterTests extends TestCase {

	private ExampleItemWriter writer = new ExampleItemWriter();
	
	public void testWrite() throws Exception {
		writer.write(null); // nothing bad happens
	}

}
