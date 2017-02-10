import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import java.awt.Color;

public class SecretMessagesGUI extends JPanel {
	private JTextField txtIn;
	private JTextField txtKey;
	private JTextField txtOut;
	private JSlider slider;
	
	public String Encode(String message, int k){
		String out ="";
		char key = (char)k;
		
		
		
		for (int x = 0; x < message.length(); x++) {
			char in = message.charAt(x);

			if (in >= 'A' && in <= 'Z') {
				in += key;

				if (in > 'Z')
					in -= 26;

				if (in < 'A')
					in += 26;

			}

			if (in >= 'a' && in <= 'z') {
				in += key;

				if (in > 'z')
					in -= 26;

				if (in < 'a')
					in += 26;
			}

			out += in;
		}
				
		return out;
	}
	
	public SecretMessagesGUI() {
		setBackground(new Color(173, 216, 230));
		setLayout(null);
		
		txtIn = new JTextField();
		txtIn.setFont(new Font("Dialog", Font.BOLD, 18));
		txtIn.setBounds(12, 12, 426, 105);
		add(txtIn);
		txtIn.setColumns(10);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(180, 131, 38, 15);
		add(lblClave);
		
		txtKey = new JTextField();
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("0");
		txtKey.setBounds(226, 129, 38, 19);
		add(txtKey);
		txtKey.setColumns(3);
		
		JButton btnCodificaDecodifica = new JButton("Codifica / Decodifica");
		btnCodificaDecodifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//obtener el texto de campo de texto in
				String message = txtIn.getText();
				
				//obtener la key del campo 
				int key = Integer.parseInt(txtKey.getText());
				
				//almacenar el texto codificado 
				String output = Encode(message,key);
				
				//mostrar el texto en campo out
				txtOut.setText(output);
				
			}
		});
		btnCodificaDecodifica.setBounds(283, 126, 155, 25);
		add(btnCodificaDecodifica);
		
		txtOut = new JTextField();
		txtOut.setFont(new Font("Dialog", Font.BOLD, 18));
		txtOut.setBounds(12, 182, 426, 105);
		add(txtOut);
		txtOut.setColumns(10);
		
		setPreferredSize(new Dimension(450, 320));
		
		slider = new JSlider();
		slider.setBackground(new Color(176, 224, 230));
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				//setear el valor del slider al campo key
				txtKey.setText(""+slider.getValue());
								
				//obtener el texto de campo de texto in
				String message = txtIn.getText();
				
				//obtener la key del campo 
				int key = Integer.parseInt(txtKey.getText());
				
				//almacenar el texto codificado 
				String output = Encode(message,key);
				
				//mostrar el texto en campo out
				txtOut.setText(output);
				
			}
		});
		slider.setMajorTickSpacing(13);
		slider.setValue(0);
		slider.setMinimum(-13);
		slider.setMaximum(13);
		slider.setBounds(12, 118, 148, 55);
		add(slider);
	}

	public static void main(String[] args) {
		//definir el JFrame
		JFrame frame = new JFrame("Eliezer's Secret Message App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//agregar el panel encoder al JFrame
		frame.getContentPane().add(new SecretMessagesGUI());
		
		//preparar y mostrar el frame
		
		frame.pack();
		frame.setVisible(true);

	}
}
