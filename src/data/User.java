package data;

public class User {
    private String id;
    private String pw;
    private boolean membership;
    private String telecomCompany;
    private String membershipGrade;

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

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public boolean isMembership() {
        return membership;
    }

    public String getTelecomCompany() {
        return telecomCompany;
    }

    public String getMembershipGrade() {
        return membershipGrade;
    }

    public int getMembershipDiscount() {
        if (!membership) {
            return 0;
        }

        if (membershipGrade.equalsIgnoreCase("VIP")) {
            return 9000;
        } else if (membershipGrade.equalsIgnoreCase("GOLD")) {
            return 5000;
        } else if (membershipGrade.equalsIgnoreCase("SILVER")) {
            return 2000;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "아이디: " + id
                + " / 멤버십 여부: " + (membership ? "예" : "아니오")
                + " / 통신사: " + telecomCompany
                + " / 등급: " + membershipGrade;
    }
}