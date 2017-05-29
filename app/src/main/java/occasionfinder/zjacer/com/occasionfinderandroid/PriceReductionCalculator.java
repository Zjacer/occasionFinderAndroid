package occasionfinder.zjacer.com.occasionfinderandroid;

public class PriceReductionCalculator {

    public static String calculateReduction(String originalPrice, String reducedPrice) {

        if(originalPrice == null || reducedPrice == null) {
            return "";
        }

        int original = Integer.parseInt(originalPrice.replaceAll("\\D+", ""));
        int reduced = Integer.parseInt(reducedPrice.replaceAll("\\D+", ""));
        return Integer.toString((original - reduced)/100) + " zł";
    }
}
