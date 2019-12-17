package com.example.bikesharingapi.utils;

import net.glxn.qrgen.javase.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class QRcodeGenerator {
    public static String GetQRcodeBytestream(UUID id) {
        return QRCode.from(id.toString()).stream().toString();
    }
}
