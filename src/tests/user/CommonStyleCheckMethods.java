package tests.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertTrue;

public abstract class CommonStyleCheckMethods {
    public static void isThisColorGrey(String cssColor) {
        int[] actualColorAsInt = splitRGBStringToListOfInts(cssColor);
        if (actualColorAsInt[0] < 50) {
            assertTrue("Color is NOT grey!! " + "RGB:[" + actualColorAsInt[0] + "],[" + actualColorAsInt[1] + "],[" + actualColorAsInt[2] + "]", false);
        } else if (actualColorAsInt[0] != actualColorAsInt[1] || actualColorAsInt[1] != actualColorAsInt[2]) {
            assertTrue("Color is NOT grey!! " + "RGB:[" + actualColorAsInt[0] + "],[" + actualColorAsInt[1] + "],[" + actualColorAsInt[2] + "]", false);
        }
    }

    private static int[] splitRGBStringToListOfInts(String colorFromBrowser) {

        Pattern coma = Pattern.compile(",");
        Pattern pattern = Pattern.compile("\\d+");
        String[] splited_colors = coma.split(colorFromBrowser);
        int[] intColor = new int[3];
        for (int i = 0; i < 3; i++) {
            Matcher matcher = pattern.matcher(splited_colors[i]);
            if (matcher.find()) {
                intColor[i] = Integer.parseInt(matcher.group(0));
            }
        }
        return intColor;
    }

    public static void isThisColorRed(String cssColor) {
        int[] actualColorAsInt = splitRGBStringToListOfInts(cssColor);
        if (actualColorAsInt[0] < 80) {
            assertTrue("Color is NOT red!! " + "RGB:[" + actualColorAsInt[0] + "],[" + actualColorAsInt[1] + "],[" + actualColorAsInt[2] + "]", false);
        } else if (actualColorAsInt[1] != 0 || actualColorAsInt[2] != 0) {
            assertTrue("Color is NOT red!! " + "RGB:[" + actualColorAsInt[0] + "],[" + actualColorAsInt[1] + "],[" + actualColorAsInt[2] + "]", false);
        }
    }

    public static void isThisFontBold(String cssFontWeight) {
        int fontWeight = Integer.parseInt(cssFontWeight);
        if (fontWeight < 700) {
            assertTrue("It is NOT bold! Font weight = " + fontWeight, false);
        }
    }

    public static void checkThatBoldIsNotApplied(String cssFontWeight) {
        int fontWeight = Integer.parseInt(cssFontWeight);
        if (fontWeight != 400) {
            assertTrue("It is NOT regular font! Font weight = " + fontWeight, false);
        }
    }

    public static void checkThatFontIsBigger(String bigFont, String notBigFont) {
        if (cutPxFromFontSize(bigFont) <= cutPxFromFontSize(notBigFont)) {
            assertTrue("Font is NOT bigger than another font", false);
        }
    }



    private static double cutPxFromFontSize(String fontSize) {
        Pattern pattern = Pattern.compile("px");
        String[] actualFontSize = pattern.split(fontSize);
        return Double.parseDouble(actualFontSize[0]);
    }
}
