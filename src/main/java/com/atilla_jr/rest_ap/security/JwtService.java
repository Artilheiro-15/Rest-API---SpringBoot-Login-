package com.atilla_jr.rest_ap.security;

import com.atilla_jr.rest_ap.utils.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private String SECRET_KEY = SecurityConstants.SECRET_KEY;

  //
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    try {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
    } catch (Exception e) {
      return null;
    }
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
    Map<String, Object> extraClaims,
    UserDetails userDetails
  ) {
    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (
      (username.equals(userDetails.getUsername())) && !isTokenExpired(token)
    );
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  // private Claims extractAllClaims(String token) {
  //   return Jwts
  //     .parserBuilder()
  //     .setSigningKey(getSignInKey())
  //     .build()
  //     .parseClaimsJws(token)
  //     .getBody();
  // }

  // public boolean isTokenValid(String token, UserDetails userDetails)
  //   throws TokenInvalidException, TokenExpiredException {
  //   try {
  //     final String username = extractUsername(token);
  //     Claims claims = extractAllClaims(token);
  //     if (
  //       !(username.equals(userDetails.getUsername())) ||
  //       isTokenExpired(claims.getExpiration())
  //     ) {
  //       throw new TokenInvalidException("Token inválido.");
  //     }
  //     return true;
  //   } catch (InvalidTokenException e) {
  //     throw new TokenInvalidException("Token inválido.");
  //   } catch (ExpiredJwtException e) {
  //     throw new TokenExpiredException(
  //       "O token expirou. Faça login novamente para obter um novo token válido."
  //     );
  //   } catch (JwtException e) {
  //     throw new TokenInvalidException("Token inválido.");
  //   }
  // }
  // public boolean isTokenValid(String token, UserDetails userDetails) {
  //   final String username = extractUsername(token);
  //   return (
  //     (username.equals(userDetails.getUsername())) && !isTokenExpired(token)
  //   );
  // }

  // private boolean isTokenExpired(Date expiration) throws TokenExpiredException {
  //   if (expiration.before(new Date())) {
  //     throw new TokenExpiredException("O token expirou.");
  //   }
  //   return expiration.before(new Date());
  // }

  private Claims extractAllClaims(String token) {
    // try {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
    // } catch (ExpiredJwtException e) {
    //   throw new TokenExpiredException("O token expirouuuu.");
    // } catch (JwtException e) {
    //   throw new InvalidTokenException("O token é inválido.");
    // }
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
