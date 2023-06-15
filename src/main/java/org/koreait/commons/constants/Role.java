package org.koreait.commons.constants;

public enum Role {
    ALL {
        @Override
        public String get() {
            return "전체";
        }
    }, // 전체 사용자
    USER {
        @Override
        public String get() {
            return "일반";
        }
    }, // 일반 사용자
    ADMIN {
        @Override
        public String get() {
            return "관리자";
        }
    }; // 관리자
    public abstract String get();

}
