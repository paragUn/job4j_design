package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.StringJoiner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target  = tempDir.resolve("unavailable.csv").toFile();
        var ls = System.lineSeparator();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner out = new StringJoiner(ls);
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(out::add);
        }
        assertThat("10:57:01;10:59:01" + ls
                + "11:01:02;11:02:02").isEqualTo(out.toString());
    }
}