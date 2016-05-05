package view;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public final class TextFieldApenasMaiusculas {

    private static final DocumentFilter filter = new UppercaseDocumentFilter();

    /**
     * @wbp.factory
     */
    public static JTextField createJTextField() {
	JTextField textField = new JTextField();
	((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
	textField.setColumns(10);
	return textField;
    }

    private static class UppercaseDocumentFilter extends DocumentFilter {
	public void insertString(DocumentFilter.FilterBypass fb, int offset,
		String text, AttributeSet attr) throws BadLocationException {

	    fb.insertString(offset, text.toUpperCase(), attr);
	}

	public void replace(DocumentFilter.FilterBypass fb, int offset,
		int length, String text, AttributeSet attrs)
		throws BadLocationException {

	    fb.replace(offset, length, text.toUpperCase(), attrs);
	}
    }
}