package com.example.bikesharingapi.utils;

import net.glxn.qrgen.javase.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class QRcodeGenerator {
    public static ByteArrayOutputStream GetQRcodeBytestream(String id) {
        File file = QRCode.from(id).file();
        try {
            System.out.println(file.createNewFile());
            System.out.println("created");
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return QRCode.from(id).stream();
    }
}
