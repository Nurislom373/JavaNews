package org.khasanof;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/3/2023 8:43 AM
 */
public class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost);

        InetAddress devopsuz = InetAddress.getByName("www.devops.uz");
        System.out.println("devopsuz = " + devopsuz);

        InetAddress[] all = InetAddress.getAllByName("www.nba.com");
        System.out.println("all = " + Arrays.toString(all));
    }

}
