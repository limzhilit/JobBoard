package ie.atu.jobseeker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secret;

  // Use SecretKey for better type safety in 0.12.x
  private javax.crypto.SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  /*
  What it does (Step-by-Step)
    Starts the Parser: Jwts.parser() initializes the engine used to read JWT strings.
    Verifies Signature: .verifyWith(getSigningKey()) uses your private secret key to recalculate the digital signature. If even one character in the token was changed by a hacker, this step will throw an exception.
    Parses Claims: .parseSignedClaims(token) breaks the string into its original components (Header, Payload, Signature).
    Extracts Payload: .getPayload() returns the Claims object, which contains the user's data (email, roles, expiration date).
   */

  // Validate token, return Claims, or null if invalid
  public Claims validateToken(String token) {
    try {
      return Jwts.parser()
          .verifyWith(getSigningKey())
          .build()
          .parseSignedClaims(token)
          .getPayload();
    } catch (ExpiredJwtException e) {
      return e.getClaims(); // expired → still return claims for refresh
    } catch (JwtException | IllegalArgumentException e) {
      return null; // invalid token
    }
  }

  // Extract userId safely
  public Long extractUserId(String token) {
    Claims claims = validateToken(token);
    if (claims == null) return null;

    String sub = claims.getSubject();
    if (sub == null || sub.isEmpty()) return null;

    try {
      return Long.parseLong(sub);
    } catch (NumberFormatException e) {
      return null; // prevent crash
    }
  }

}
