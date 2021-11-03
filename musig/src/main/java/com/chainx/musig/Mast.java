package com.chainx.musig;

import android.text.TextUtils;

public class Mast {
    static {
        System.loadLibrary("musig_dll");
    }

    public static native String generate_threshold_pubkey(String pubkeys, byte threshold);

    public static native String generate_control_block(String pubkeys, byte threshold, String sigAggPubkey);

    public static String generateThresholdPubkey(String[] pubkeys, byte threshold) {
        return generate_threshold_pubkey(TextUtils.join("", pubkeys).toString(), threshold);
    }

    public static String generateControlBlock(String[] pubkeys, byte threshold, String sigAggPubkey) {
        return generate_control_block(TextUtils.join("", pubkeys).toString(), threshold, sigAggPubkey);
    }

}
