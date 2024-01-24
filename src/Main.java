/* Create an AST, then invoke our interpreter. */

import fun.lexer.Lexer;
import fun.node.Start;
import fun.parser.Parser;
import types.constraint.Constraint;
import visitors.ConstraintVisitor;
import visitors.PrettyVisitorWithLabels;

import java.io.File;
import java.io.FileReader;
import java.io.PushbackReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                Lexer lexer = new Lexer(new PushbackReader(
                        new FileReader(args[0]), 1024));
                Parser parser = new Parser(lexer);
                Start ast = parser.parse();
                PrettyVisitorWithLabels print = new PrettyVisitorWithLabels();

                System.out.println("Given program:");
                System.out.println(print.getString(ast));

                ConstraintVisitor constraintVisitor = new ConstraintVisitor();
                List<Constraint> constraints = constraintVisitor.getConstraints(ast, print.getTerms());

                ZeroCFA zeroCFA = new ZeroCFA();
                System.out.println("Result of analysis:");
                zeroCFA.worklist(constraints);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        } else {
            File dir = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getFile());
            System.out.println(dir);
            System.err.println("Usage:\n ControlFlowAnalysis <inputFile>");
            System.exit(1);
        }
    }


}