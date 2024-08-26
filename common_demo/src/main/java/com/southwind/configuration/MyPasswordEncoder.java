package com.southwind.configuration;

import com.southwind.util.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return MD5Util.getSaltverifyMD5(charSequence.toString(), s);
    }
}
