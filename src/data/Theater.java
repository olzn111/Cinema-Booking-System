// 패키지 선언
package data;

public class Theater {
    // Theater 객체가 가는 정보, 인스턴스 변수
    private String type;
    private int basePrice;
    private int rows;
    private int cols;
    // Theater constructor - 영화 타입, 기본가격, 행, 렬
    public Theater(String type, int basePrice, int rows, int cols) {
        this.type = type;
        this.basePrice = basePrice;
        this.rows = rows;
        this.cols = cols;
    }
    // Theater 인스턴스 변수에 대한 getter
    public String getType() { return type; }
    public int getBasePrice() { return basePrice; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    // 영화관에 대한 정보를 폼에 맞춰 문자열 형태로 보여줌
    @Override
    public String toString() {
        return type + "관 / 기본가격: " + basePrice + "원 / 좌석 수: " + rows + "x" + cols;
    }
}