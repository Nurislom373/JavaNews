package org.khasanof.basicValues.types.blobExample;

import org.hibernate.engine.jdbc.BlobProxy;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/23/2023
 * <br/>
 * Time: 7:42 PM
 * <br/>
 * Package: org.khasanof.basicValues.types.blobExample
 */
public class ProductTest {

    public static void main(String[] args) {
        byte[] image = new byte[]{1, 2, 3};

        Product product = new Product();
        product.setId(1);
        product.setName("Laptop");

        // Byteni Blob parse qilish example
        product.setImage(BlobProxy.generateProxy(image));


        // Blob oqib olish example
        try (InputStream inputStream = product.getImage().getBinaryStream()) {
            // new byte[]{1, 2, 3} = inputStream.readAllBytes();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
