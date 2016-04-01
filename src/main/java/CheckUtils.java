import com.puppycrawl.tools.checkstyle.api.DetailAST;

/**
 * Utils for checkers
 */
public final class CheckUtils {
    private CheckUtils() {
    }

    public static boolean containsLiteral(DetailAST ast, int literal) {
        for (DetailAST child = ast.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getType() == literal || containsLiteral(child, literal)) {
                return true;
            }
        }
        return false;
    }
}
