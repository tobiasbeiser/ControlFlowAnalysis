/* Create an AST, then invoke our interpreter. */

import java.io.*;
import java.util.ArrayList;

import fun.lexer.Lexer;
import fun.node.Start;
import fun.parser.Parser;
import types.*;
import types.constraint.ConditionalConstraint;
import types.constraint.Constraint;
import types.constraint.NodeConstraint;
import types.constraint.TermConstraint;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                Lexer lexer = new Lexer(new PushbackReader(
                        new FileReader(args[0]), 1024));
                Parser parser = new Parser(lexer);
                Start ast = parser.parse();
                PrettyVisitor print = new PrettyVisitor();
                System.out.println(print.getString(ast));


                // r(x) ⊆ C(1),
                // {fn y -> y} ⊆ C(4),
                // r(y) ⊆ C(3),
                // {fn x -> x} ⊆ C(2) ⇒ C(4) ⊆ r(x),
                // {fn x -> x} ⊆ C(2) ⇒ C(1) ⊆ C(5),
                // {fn y -> y} ⊆ C(2) ⇒ C(4) ⊆ r(y),
                // {fn y -> y} ⊆ C(2) ⇒ C(3) ⊆ C(5)"


                Cache C1 = new Cache(1);
                Cache C2 = new Cache(2);
                Cache C3 = new Cache(3);
                Cache C4 = new Cache(4);
                Cache C5 = new Cache(5);
                Term fx = new Term("fn x -> x", 1);
                Term fy = new Term("fn y -> y", 3);
                Environment r_x = new Environment("x");
                Environment r_y = new Environment("y");


                ArrayList<Constraint> constraints = new ArrayList<>();

                constraints.add(new TermConstraint(new Terms(fx), C2)); //"{fn x -> x1} ⊆ C(2),
                constraints.add(new NodeConstraint(r_x, C1)); //r(x) ⊆ C(1),
                constraints.add(new TermConstraint(new Terms(fy), C4)); // {fn y -> y} ⊆ C(4),
                constraints.add(new NodeConstraint(r_y, C3)); //r(y) ⊆ C(3),
                constraints.add(new ConditionalConstraint(new Terms(fx), C2, C4, r_x)); //{fn x -> x} ⊆ C(2) ⇒ C(4) ⊆ r(x),
                constraints.add(new ConditionalConstraint(new Terms(fx), C2, C1, C5)); //{fn x -> x} ⊆ C(2) ⇒ C(1) ⊆ C(5),
                constraints.add(new ConditionalConstraint(new Terms(fy), C2, C4, r_y)); //{fn y -> y} ⊆ C(2) ⇒ C(4) ⊆ r(y),
                constraints.add(new ConditionalConstraint(new Terms(fy), C2, C3, C5)); //{fn y -> y} ⊆ C(2) ⇒ C(3) ⊆ C(5)"

                ZeroCFA zeroCFA = new ZeroCFA();
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