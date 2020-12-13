package com.example.SpringSecurityHW8.convertors;

public interface Convertor<TARGET, SOURCE>  {
    TARGET convert(SOURCE source);
}
