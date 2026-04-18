// 패키지 선언
package data;

public class User {
    // user 객체가 가지는 인스턴스 변수
    private String id;
    private String pw;
    private boolean membership;
    private String telecomCompany;
    private String membershipGrade;
    // user constructor - user 객체 정보 초기화 (아이디, 비번, 멤버십 유무 및 종류)
    public User(String id, String pw, boolean membership, String telecomCompany, String membershipGrade) {
        this.id = id;
        this.pw = pw;
        this.membership = membership;
        if (membership) {
            this.telecomCompany = telecomCompany;
            this.membershipGrade = membershipGrade;
        } else {
            this.telecomCompany = "없음";
            this.membershipGrade = "없음";
        }
    }
    // user 인스턴스 변수에 대한 getter
    public String getId() { return id;}
    public String getPw() { return pw; }
    public boolean isMembership() { return membership; }   
    public String getTelecomCompany() { return telecomCompany; }   
    public String getMembershipGrade() { return membershipGrade; }
    // 멤버십 할인에 대한 금액 반환 매서드
    public int getMembershipDiscount() {
        if (!membership) { return 0; } // 멤버십이 없다면 반환 금액이 0
        // 멤버십이 있다면 각 등급에 따라 9000, 5000, 4000 할인 해줌
        if (membershipGrade.equalsIgnoreCase("VIP")) { return 9000;
        } else if (membershipGrade.equalsIgnoreCase("GOLD")) { return 5000;
        } else if (membershipGrade.equalsIgnoreCase("SILVER")) { return 2000;
        }
        return 0;
    }
    //User 정보를 폼에 맞춰 문자열 형태로 반환 
    @Override
    public String toString() {
        return "아이디: " + id
                + " / 멤버십 여부: " + (membership ? "예" : "아니오")
                + " / 통신사: " + telecomCompany
                + " / 등급: " + membershipGrade;
    }
}