package exampleCode.bioinfo.align;

import java.util.BitSet;
import java.io.*;

/**
 * @author jaeger
 */
public class ScoreMatrix {
    int[][] matrix;
    int size;
    BitSet defined;

    public ScoreMatrix(int size) {
        this.size = size;
        matrix = new int[256][256];
        defined = new BitSet();
    }

    /**
     * set the score for two characters
     * @param c1
     * @param c2
     * @param score
     */
    public void setScore(char c1, char c2, int score) {
        matrix[c1][c2] = matrix[c2][c1] = score;
        defined.set((int) c1);
        defined.set((int) c2);
    }

    /**
     * get the score for two characters
     * @param c1
     * @param c2
     * @return
     * @throws Exception
     */
    public int getScore(char c1, char c2) throws Exception {
        if (defined.get((int) c1) == false)
            throw new Exception("No score defined for c1=" + c1);
        if (defined.get((int) c2) == false)
            throw new Exception("No score defined for c2=" + c2);
        return matrix[c1][c2];
    }

    /** Read the matrix
     *@param r the Reader
     */
    public void read(Reader r) throws IOException {
        StreamTokenizer st = new StreamTokenizer(r);
        st.resetSyntax();
        st.wordChars(22, 126);
        st.whitespaceChars(' ', ' ');
        st.whitespaceChars('\t', '\t');
        st.whitespaceChars('\n', '\n');
        st.commentChar('#');

        st.nextToken();
        if (st.sval != null)
            size = (int) (Double.parseDouble(st.sval));
        else
            throw new IOException("input line " + st.lineno() +
                    ": size expected");

        defined.clear();

        char[] pos2ch = new char[256];

        for (int i = 1; i <= size; i++) {
            if (st.nextToken() != StreamTokenizer.TT_WORD) {
                throw new IOException("input line " + st.lineno() +
                        ": Symbole expected");
            }
            char ch = st.sval.charAt(0);
            pos2ch[i] = ch;
        }

        for (int i = 1; i <= size; i++) {
            if (st.nextToken() != StreamTokenizer.TT_WORD) {
                throw new IOException("input line " + st.lineno() +
                        ": label expected");
            }
            char c1 = st.sval.charAt(0);
            for (int j = 1; j <= size; j++) {
                char c2 = pos2ch[j];
                st.nextToken();
                int v;
                if (st.sval != null)
                    v = Integer.parseInt(st.sval);
                else
                    throw new IOException("input line " + st.lineno() +
                            ": number expected");
                setScore(c1, c2, v);
            }
        }
    }

    /**
     * write the matrix
     * @param w
     * @throws IOException
     */
    public void write(Writer w) throws IOException {
        w.write(size + "\n");
        for (int i = 0; i < 256; i++) {
            if (defined.get(i))
                w.write("  " + ((char) i));
        }
        w.write("\n");
        for (int i = 0; i < 256; i++) {
            if (defined.get(i)) {
                w.write((char) i);
                for (int j = 0; j < 256; j++) {
                    if (defined.get(j)) {
                        w.write(" " + matrix[i][j]);
                    }
                }
                w.write("\n");
            }
        }
    }

    public String toString ()
    {
        StringWriter sw=new StringWriter();
        try {
            write(sw);
        } catch (IOException e) {
            System.err.println("Caught: "+e);
        }
        return sw.toString();
    }

}
