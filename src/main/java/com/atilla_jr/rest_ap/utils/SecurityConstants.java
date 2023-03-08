package com.atilla_jr.rest_ap.utils;

public class SecurityConstants {

  public static final String SECRET_KEY =
    "3VN0DH4Z9PVBWR9L7D0BCIAAGTVE56BUN0OJ8Q3T6UHQAWD5KC4VUHOAIRQKJ33I";

  public static final long EXPIRATION_TIME = 900_000; // 15 mins
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/auth/login";
}
