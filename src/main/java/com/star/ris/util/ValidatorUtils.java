package com.star.ris.util;

import com.star.ris.common.RisConstants;
import org.apache.logging.log4j.util.Strings;

public class ValidatorUtils {

    public static String checkNumber(String value, String field) {

        if(Strings.isEmpty(value)){
            return String.format(RisConstants.Error.ERROR_EMPTY,field);
        }
        if (!value.matches(RisConstants.REGEX_NUMBER)) {
            return String.format(RisConstants.Error.ERROR_NUMBER,field);
        }

        return Strings.EMPTY;
    }

    public static String checkDecimal(String value, String field) {
        if(Strings.isEmpty(value)){
            return String.format(RisConstants.Error.ERROR_EMPTY,field);
        }
        if (!value.matches(RisConstants.REGEX_DECIMAL)) {
            return String.format(RisConstants.Error.ERROR_DECIMAL,field);
        }
        return Strings.EMPTY;
    }

    public static String checkString(String value, String field) {
        if(Strings.isEmpty(value)){
            return String.format(RisConstants.Error.ERROR_EMPTY,field);
        }
        return Strings.EMPTY;
    }
}
