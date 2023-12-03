package org.khasanof;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/3/2023 10:37 AM
 */
public class SocketTest {

    public static void main(String[] args) throws IOException {
        int c;

        Socket socket = new Socket("echo.getpostman.com", 80);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        String text = "Hello World";
        byte[] bytes = text.getBytes();
        outputStream.write(bytes);

        while ((c = inputStream.read()) != -1) {
            System.out.print((char) c);
        }
        socket.close();
    }

}
