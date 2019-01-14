/*
 * Created on Feb 28, 2005
 *
 */
package shef.ui.text.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.bushe.swing.action.ShouldBeEnabledDelegate;
import storybook.i18n.I18N;

/**
 * Action which clears inline text styles
 *
 * @author Bob Tantlinger
 *
 */
public class ClearStylesAction extends HTMLTextEditAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ClearStylesAction() {
		super(I18N.getMsg("shef.clear_styles"));
		putValue(MNEMONIC_KEY, (int) I18N.getMnemonic("shef.clear_styles"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl Y"));

		addShouldBeEnabledDelegate((Action a) -> getEditMode() != SOURCE);
	}

	@Override
	protected void wysiwygEditPerformed(ActionEvent e, JEditorPane editor) {
		HTMLDocument document = (HTMLDocument) editor.getDocument();
		HTMLEditorKit kit = (HTMLEditorKit) editor.getEditorKit();

		MutableAttributeSet attrs = new SimpleAttributeSet();
		attrs.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);

		int selStart = editor.getSelectionStart();
		int selEnd = editor.getSelectionEnd();

		if (selEnd > selStart) {
			document.setCharacterAttributes(selStart, selEnd - selStart, attrs, true);
		}

		kit.getInputAttributes().removeAttributes(kit.getInputAttributes());
		kit.getInputAttributes().addAttributes(attrs);

	}

	@Override
	protected void sourceEditPerformed(ActionEvent e, JEditorPane editor) {

	}
}
