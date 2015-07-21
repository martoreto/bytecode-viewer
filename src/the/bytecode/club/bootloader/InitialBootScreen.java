package the.bytecode.club.bootloader;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;

import the.bytecode.club.bytecodeviewer.Resources;

/**
 * @author Konloch
 * @author Bibl (don't ban me pls)
 * @created 19 Jul 2015 04:12:21
 */
public class InitialBootScreen extends JFrame {
	private static final long serialVersionUID = -1098467609722393444L;
	
	private JProgressBar progressBar = new JProgressBar();

	public InitialBootScreen() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImages(Resources.iconList);

		int i = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		if(i >= 840)
			setSize(new Dimension(600, 800));
		else if(i >= 640)
			setSize(new Dimension(500, 600));
		else if(i >= 440)
			setSize(new Dimension(400, 400));
		else
			setSize(Toolkit.getDefaultToolkit().getScreenSize());

		setTitle("Bytecode Viewer Boot Screen - Starting Up");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 24;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditorKit(new HTMLEditorKit());

		editorPane.setText(convertStreamToString(InitialBootScreen.class.getClassLoader().getResourceAsStream("resources/intro.html")));

		scrollPane.setViewportView(editorPane);

		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 24;
		getContentPane().add(progressBar, gbc_progressBar);
		this.setLocationRelativeTo(null);
	}

	static String convertStreamToString(java.io.InputStream is) throws IOException {
		@SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		String string =  s.hasNext() ? s.next() : "";
		is.close();
		s.close();
		return string;
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
}