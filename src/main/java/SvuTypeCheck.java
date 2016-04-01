import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Check if variable serialVersionUID is final static long
 */
public class SvuTypeCheck extends Check {
    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.VARIABLE_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        setSeverity("warning");
        boolean resultType = false;
        boolean resultModifier = true;

        for (DetailAST child = ast.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getType() == TokenTypes.MODIFIERS) {
                resultModifier = CheckUtils.containsLiteral(child, TokenTypes.LITERAL_STATIC)
                        && CheckUtils.containsLiteral(child, TokenTypes.FINAL);
            }

            if (CheckUtils.containsLiteral(child, TokenTypes.LITERAL_LONG)){
                resultType = true;
            }

            if (child.getType() == TokenTypes.IDENT) {
                if (child.getText().equals("serialVersionUID")) {
                    if (!(resultType && resultModifier)) {
                        String message = "Variable serialVersionUID should be final static long";
                        log(ast.getLineNo(), message);
                    }
                }
            }
        }

    }
}
