package com.example.bikesharingapi.utils;

import net.glxn.qrgen.javase.QRCode;

public class QRcodeGenerator {
    public static byte[] GetQRcodeBytestream(String id, String server) {
        return QRCode.from(server + id).stream().toByteArray();
    }
}
