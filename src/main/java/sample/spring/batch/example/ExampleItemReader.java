package sample.spring.batch.example;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("reader")
public class ExampleItemReader implements ItemReader<String> {

    private final String[] input = { "Hello world!", null };

    private int index = 0;

    /**
     * Reads next record from input
     */
    @Override
    public String read() throws Exception {

        System.out.println("Readerの実行");

        if (index < input.length) {
            return input[index++];
        } else {
            return null;
        }

    }

}
