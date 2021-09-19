package com.star.ris.common;

public class RisConstants {

    public static final String SEPARATOR_COMA = ",";

    public static final int TOTAL_MONTH_ONE_YEAR = 12;

    public static final Double BANK_INTEREST_ONE_YEAR = 0.06;

    public static final  String DECIMAL_FORMAT = "%.2f";


    public static final  String REGEX_NUMBER = "^[0-9]+$";

    public static final String REGEX_DECIMAL = "^[0-9]*([.][0-9]*)?$";

    public static class Status{
        private Status(){}
        public  static  final String SUCCESS_CODE = "00";
        public  static  final String SUCCESS_MESSAGE = "SUCCESS";
        public  static  final String ERROR_CODE = "ER0";
        public  static  final String ERROR_MESSAGE = "ERROR";
        public  static  final String ACTIVE= "1";
        public  static  final String INACTIVE= "0";
        public  static  final String DONE= "0";
    }

    public static class Error{
        private Error(){}
        public  static  final String ERROR_NUMBER = "Field %s harus angka";
        public  static  final String ERROR_DECIMAL = "Field %s harus angka atau decimal";
        public  static  final String ERROR_EMPTY = "Field %s tidak boleh kosong";
        public  static  final String ERROR_ACCOUNT_NOT_EXIST= "Akun %s tidak terdaftar";
        public  static  final String ERROR_DATA_DEPOSIT_NOT_FOUND= "Data deposit tidak ditemukan";
        public  static  final String ERROR_DATA_DEPOSIT_NOT_FOUND_BY_ID= "Data deposit user %s tidak ditemukan";
        public  static  final String ERROR_DATA_LESS_THAN_ONE = "Field %s tidak boleh kurang dari 1";
    }

    public static class Field{
        private Field(){}
        public  static  final String TENOR = "TENOR";
        public  static  final String FIRST_DEPOSIT_AMOUNT = "First Deposit Amount";
        public  static  final String MONTHLY_DEPOSIT_AMOUNT = "Monthly Deposit Amount";
        public  static  final String PURPOSE = "Purpose";
        public  static  final String REQUEST_BY = "Nama Staf";
        public  static  final String ACCOUNT_ID = "Account Bank";

    }

}
