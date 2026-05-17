package ie.atu.jobseeker.util;

public class TextUtils {
  public static String normalize(String text) {
    if (text == null) return "";

    return text
        .toLowerCase()
        .replaceAll("[^a-z0-9 ]", " ")
        .replaceAll("\\s+", " ")
        .trim();
  }
}
