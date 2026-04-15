package data;

public class User {
    private String id;
    private String pw;
    private boolean isMembership; // 멤버십 여부 저장 공간 추가!

    public User(String id, String pw, boolean isMembership) {
        this.id = id;
        this.pw = pw;
        this.isMembership = isMembership;
    }

    // 정보들을 가져오는 Getter 메서드들
    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    // 이 부분이 추가되어 isMembership 빨간 줄이 해결됩니다!
    public boolean isMembership() {
        return isMembership;
    }

    // MovieConsoleUi에서 사용하는 연령 할인 타입 (기본값 설정)
    public String getAgeDiscountType() {
        return "일반";
    }
}