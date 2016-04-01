import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.sun.javafx.fxml.expression.Expression;

import java.util.ArrayList;

/**
 * Check for method suspicious names (in diffent capitalization) that should be
 * overrided
 */
public class ConfusingMethodNamesCheck extends Check {

    private ArrayList<DetailAST> methodNames = new ArrayList<>();

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.OBJBLOCK};
    }

    @Override
    public void visitToken(DetailAST ast) {
        setSeverity("warning");

        for (DetailAST child = ast.getFirstChild(); child != null; child = child.getNextSibling()){
            if (child.getType() == TokenTypes.METHOD_DEF) {
                for (DetailAST i = child.getFirstChild(); i != null; i = i.getNextSibling()) {
                    if (i.getType() == TokenTypes.IDENT) {
                        methodNames.add(i);
                    }
                }
            }
        }

        for (int i = 0; i < methodNames.size() - 1; i++) {
            for (int j = i + 1; j < methodNames.size(); j++) {
                String si = methodNames.get(i).getText();
                String sj = methodNames.get(j).getText();
                if (!(si.equals(sj)) && (si.toLowerCase().equals(sj.toLowerCase()))) {
                    String message = "Suspicious method names " + si + " at " + methodNames.get(i).getLineNo()
                            + " and " + sj + " at " + methodNames.get(j).getLineNo() + ". Probably override one.";
                    log(methodNames.get(i), message);
                }
            }
        }
    }
}
