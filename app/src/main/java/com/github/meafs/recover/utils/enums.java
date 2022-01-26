package com.github.meafs.recover.utils;

public class enums {
    public enum BottomNavigationEnum {
        USER("users"),
        PATIENT("patients"),
        CONTACT("contact");
        private final String text;

        BottomNavigationEnum(String text) {
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }
    }
}
