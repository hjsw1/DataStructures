package charpter2;

public class PolynomialArrayImpl {
	public static final int MAX_DEGREE = 100;
	private int[] poly = new int[MAX_DEGREE];
	private int highPower = 0;// 保存最高次项的次数

	public PolynomialArrayImpl() {
	}
	
	public PolynomialArrayImpl(String expression) {
		// 2x^3+23x^1200+(-9x^32800)
		parse(expression);
	}

	private void parse(String expression) {
		String[] terms = expression.split("[+]");
		int coef = 0, exp = 0;
		for (String term : terms) {
			term = removeBracket(term);
			String[] parts = term.split("x\\^");
			coef = Integer.parseInt(parts[0]);
			exp = parts.length == 2 ? Integer.parseInt(parts[1]) : 0;
			poly[exp] = coef;
			if (exp > highPower) {
				highPower = exp;
			}
		}
	}

	private String removeBracket(String term) {
		if (term.charAt(0) == '(') {
			return term.substring(1, term.length() - 1);
		} else {
			return term;
		}
	}
	
	public void clear(){
		for(int i = 0; i < MAX_DEGREE;i++){
			poly[i] = 0;
		}
		highPower = 0;
	}
	
	public PolynomialArrayImpl add(PolynomialArrayImpl rhs){
		PolynomialArrayImpl sum = new PolynomialArrayImpl();
		sum.highPower = highPower > rhs.highPower ? highPower:rhs.highPower;
		for(int i = 0 ; i <= sum.highPower ;i++){
			sum.poly[i] = poly[i] + rhs.poly[i];
		}
		return sum;
	}
	
	public PolynomialArrayImpl mul(PolynomialArrayImpl rhs){
		PolynomialArrayImpl product = new PolynomialArrayImpl();
		product.highPower = highPower+rhs.highPower;
		if(product.highPower > MAX_DEGREE){
			throw new RuntimeException("溢出");
		}
		for(int i = 0 ;i <= highPower;i++){
			for(int j = 0; j <= rhs.highPower; j++){
				product.poly[i+j] = poly[i]*rhs.poly[j];
			}
		}
		return product;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= highPower; i++) {
			if (poly[i] != 0) {
				sb.append(poly[i] + (i == 0 ? "" : ("x^" + i + " ")));
				if (i != highPower) {
					sb.append(" + ");
				}
			}
		}
		return sb.toString();
	}

	
	public static void main(String[] args) {
		PolynomialArrayImpl p = new PolynomialArrayImpl("2+2x^3+23x^12+(-9x^23)");
		PolynomialArrayImpl p2 = new PolynomialArrayImpl("3x^8+(-5x^3)+3x^1+(-1)");
//		System.out.println(p);
//		PolynomialOfArray sum = p.add(p2);
//		System.out.println(sum);
		PolynomialArrayImpl product = p.mul(p2);
		System.out.println(product);
	}
}
