package data;

public class User {
    private String id;
    private String password;
    private String name;
    private int age;
    private String ageDiscountType;   // 유아, 청소년, 경로우대, 일반
    private boolean membership;
    private int telecom;              // 1, 2, 3 / 없으면 0
    private String membershipGrade;   // VIP, GOLD, COMMON / 없으면 "없음"
    private boolean soldier;
    private boolean disabled;

    public User(String id, String password, String name, int age, String ageDiscountType,
                boolean membership, int telecom, String membershipGrade,
                boolean soldier, boolean disabled) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.ageDiscountType = ageDiscountType;
        this.membership = membership;
        this.telecom = telecom;
        this.membershipGrade = membershipGrade;
        this.soldier = soldier;
        this.disabled = disabled;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAgeDiscountType() {
        return ageDiscountType;
    }

    public boolean isMembership() {
        return membership;
    }

    public int getTelecom() {
        return telecom;
    }

    public String getMembershipGrade() {
        return membershipGrade;
    }

    public boolean isSoldier() {
        return soldier;
    }

    public boolean isDisabled() {
        return disabled;
    }
}