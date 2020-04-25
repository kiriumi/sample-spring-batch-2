package sample.spring.batch.example;

import junit.framework.TestCase;

public class ExampleItemWriterTests extends TestCase {

    private final ExampleItemWriter writer = new ExampleItemWriter();

    public void testWrite() throws Exception {
        writer.write(null); // nothing bad happens
    }

}
