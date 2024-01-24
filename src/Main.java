/* Create an AST, then invoke our interpreter. */

import fun.lexer.Lexer;
import fun.node.Start;
import fun.parser.Parser;
import types.constraint.Constraint;
import visitors.ConstraintVisitor;
import visitors.PrettyVisitorWithLabels;

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
                ConstraintVisitor constraintVisitor = new ConstraintVisitor();
                System.out.println("Given program:");
                System.out.println(print.getString(ast));
                List<Constraint> constraints = constraintVisitor.getConstraints(ast, print.getTerms());

                ZeroCFA zeroCFA = new ZeroCFA();
                System.out.println("Result of analysis:");
                zeroCFA.worklist(constraints);


            } catch (Exception e) {
                //System.err.println(e);
                e.printStackTrace(System.err);
            }
        } else {
            System.err.println("usage: java <inputFile>");
            System.exit(1);
        }
    }


}