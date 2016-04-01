import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Check for postfix increment in return statement, that is obviously useless
 */
public class ReturnPostfixIncrementCheck extends Check {
    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.LITERAL_RETURN};
    }

    @Override
    public void visitToken(DetailAST ast) {
        setSeverity("warning");
        if (CheckUtils.containsLiteral(ast, TokenTypes.POST_INC)){
            String message = "Postfix increment in return statement is useless";
            log(ast.getLineNo(), message);
        }

    }
}
