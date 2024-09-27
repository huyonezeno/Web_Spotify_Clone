package Security;

public class Token {
    private String tokenValue;

    public Token(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String toString() {
        return "Token{" +
                "tokenValue='" + tokenValue + '\'' +
                '}';
    }
}
