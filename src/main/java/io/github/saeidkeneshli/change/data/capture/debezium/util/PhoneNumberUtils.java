package io.github.saeidkeneshli.change.data.capture.debezium.util;

public class PhoneNumberUtils {

    //9123456789
    public static String getTenDigits(String mobileNumber) {
        mobileNumber = normalizeInput(mobileNumber);
        int len = mobileNumber.length();

        if (len == 12 && mobileNumber.startsWith("989")) {
            return mobileNumber.substring(2);
        }
        if (len == 11 && mobileNumber.startsWith("09")) {
            return mobileNumber.substring(1);
        }
        if (len == 10 && mobileNumber.startsWith("9")) {
            return mobileNumber;
        }
        throw new RuntimeException("mobile number is not correct: " + mobileNumber);
    }

    //09123456789
    public static String getElevenDigits(String mobileNumber) {
        mobileNumber = normalizeInput(mobileNumber);
        int len = mobileNumber.length();

        if (len == 12 && mobileNumber.startsWith("989")) {
            return "0" + mobileNumber.substring(2);
        }
        if (len == 11 && mobileNumber.startsWith("09")) {
            return mobileNumber;
        }
        if (len == 10 && mobileNumber.startsWith("9")) {
            return "0" + mobileNumber;
        }
        throw new RuntimeException("mobile number is not correct: " + mobileNumber);
    }

    //989123456789
    public static String getTwelveDigits(String mobileNumber) {
        mobileNumber = normalizeInput(mobileNumber);
        int len = mobileNumber.length();

        if (len == 12 && mobileNumber.startsWith("989")) {
            return mobileNumber;
        }
        if (len == 11 && mobileNumber.startsWith("09")) {
            return "98" + mobileNumber.substring(1);
        }
        if (len == 10 && mobileNumber.startsWith("9")) {
            return "98" + mobileNumber;
        }
        throw new RuntimeException("mobile number is not correct: " + mobileNumber);
    }

    private static String normalizeInput(String mobileNumber) {
        if (mobileNumber == null) {
            throw new RuntimeException("mobile number is empty");
        }
        return mobileNumber.trim();
    }
}