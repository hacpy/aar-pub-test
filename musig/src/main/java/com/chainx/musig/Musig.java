package com.chainx.musig;

import android.text.TextUtils;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class Musig {

    public static Pointer getMusig(String priv) {
        return clib.get_musig(priv);
    }

    public static String getMyPubkey(String priv) {
        return clib.get_my_pubkey(priv);
    }

    public static String getMyPrivkey(String phrase) {
        return clib.get_my_privkey(phrase);
    }

    public static String getMyReveal(Pointer musig) {
        return clib.get_my_reveal(musig);
    }

    public static String getMyCosign(Pointer musig, String[] reveals, String[] pubkeys) {
        musig = clib.cosign_stage(musig, TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString());
        return clib.get_my_cosign(musig);
    }

    public static String encodeRevealStage(Pointer musig) {
        return clib.encode_reveal_stage(musig);
    }

    public static Pointer decodeRevealStage(String musig) {
        return clib.decode_reveal_stage(musig);
    }

    public static String encodeCosignStage(Pointer musig) {
        return clib.encode_cosign_stage(musig);
    }

    public static Pointer decodeCosignStage(String musig) {
        return clib.decode_cosign_stage(musig);
    }

    public static String getAggSignature(String[] reveals, String[] cosigns, String[] pubkeys) {
        return clib.get_signature(TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString(), TextUtils.join("", cosigns).toString());
    }

    public static String getAggPublicKey(String[] pubkeys) {
        return clib.get_agg_pubkey(TextUtils.join("", pubkeys).toString());
    }

    final static CLibrary clib = (CLibrary) Native.load(
            "musig_dll",
            CLibrary.class);
}
