package com.example.bikesharingapi.utils;

import net.glxn.qrgen.javase.QRCode;
import java.util.UUID;

public class QRcodeGenerator {
    public static byte[] GetQRcodeBytestream(String id) {
        return QRCode.from(id).stream().toByteArray();
    }
}
