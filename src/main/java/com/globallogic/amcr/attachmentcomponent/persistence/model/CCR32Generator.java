package com.globallogic.amcr.attachmentcomponent.persistence.model;

import java.util.zip.CRC32C;
import java.util.zip.Checksum;

public class CCR32Generator {
    public static void main(String[] args) {
        String input = "something.png";
        byte[] data = input.getBytes();
        Checksum crc32c = new CRC32C();
        crc32c.update(data);
        long crc32Checksum = crc32c.getValue();
        System.out.println(crc32Checksum);
    }
}
