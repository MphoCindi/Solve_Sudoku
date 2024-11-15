package Sudoku.src.Sudoku_GUI;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

//This class is to make sure that it takes a single number and also it does not take ant letters
class limit extends javax.swing.text.PlainDocument {
    private int limit;

    limit(int limit) {
        super();
        this.limit = limit;
    }

    void setLimit(int limit) {
        this.limit = limit;
    }

    int getLimit() {
        return limit;
    }


    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit && str.matches("[0-9]")) {
            super.insertString(offset, str, attr);
        }
    }
}
