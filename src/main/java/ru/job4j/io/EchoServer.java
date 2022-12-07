package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    System.out.println(str);
                    if (str != null && !str.isEmpty()) {
                        if (str.contains("/?msg=Hello")) {
                            str = "Hello";
                        } else if (str.contains("/?msg=Exit")) {
                            server.close();
                        } else {
                            str = "What";
                        }
                        out.write(str.getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}