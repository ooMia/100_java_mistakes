package base.view;

import base.model.Schema;
import base.model.Validator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class ConsoleInput<IN extends Schema> implements AutoCloseable {
    private final Scanner scanner;
    private final View<IN> view;
    private final Validator<IN> validator;

    protected ConsoleInput(View<IN> view, Validator<IN> validator, Scanner scanner) {
        this.scanner = scanner;
        this.view = view;
        this.validator = validator;
    }

    public final IN tryInput() throws IllegalArgumentException {
        IN res;
        try {
            String input = scanner.nextLine();
            res = parseDto(input); // 입력 목적에 따라 문자열 파싱
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        validator.validate(res);
        return res;
    }

    public final IN getInput() {
        while (true) {
            printInputHeader(); // 입력을 받기 전에 출력할 문구
            try {
                return tryInput();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected abstract IN parseDto(String in) throws IllegalArgumentException;

    private void printInputHeader() {
        view.header();
    }
    @Override
    public void close() {
        // closing Scanner will close System.in stream
        // and this made it impossible to reopen the stream
        // make sure not to close the scanner
        // but close any other resources with override if necessary
    }
}
