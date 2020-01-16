import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class URLReaderExample extends JFrame implements ActionListener{
	JPanel pa, pa_S, pa_N, pa_W, pa_E, pa_C;
	JLabel lbEx;
	static JLabel lbOut;
	static JTextField txInput;
	JButton btnOK, btnReset;
	
	URLReaderExample(){
		pa = new JPanel(new BorderLayout());
		pa_S = new JPanel();
		pa_N = new JPanel();
		pa_W = new JPanel();
		pa_E = new JPanel();
		pa_C = new JPanel();
		
		this.add(pa);
		pa.add(pa_C, BorderLayout.CENTER);
		pa.add(pa_S, BorderLayout.LINE_END);
		pa.add(pa_N, BorderLayout.LINE_START);
		pa.add(pa_W, BorderLayout.PAGE_START);
		pa.add(pa_E, BorderLayout.PAGE_END);
		
		lbEx = new JLabel("아래에 URL 주소를 입력하세요~~ ^^");
		pa_C.setPreferredSize(new Dimension(400,150));
		lbEx.setPreferredSize(new Dimension(400,30));
		lbEx.setHorizontalAlignment(lbEx.CENTER);
		pa_C.add(lbEx);
		
		setLocationRelativeTo(null);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		txInput = new JTextField();
		txInput.setPreferredSize(new Dimension(400,30));
		pa_C.add(txInput);
		
		lbOut = new JLabel();
		lbOut.setPreferredSize(new Dimension(400,30));
		lbOut.setBackground(new Color(255,150,150));
		lbOut.setOpaque(true);
		pa_C.add(lbOut);
		
		btnOK = new JButton("확인");
		btnOK.setPreferredSize(new Dimension(190,40));
		btnOK.setBackground(new Color(150,150,255));
		pa_C.add(btnOK);
		btnOK.addActionListener(this);
		
		btnReset = new JButton("초기화");
		btnReset.setPreferredSize(new Dimension(190,40));
		btnReset.setBackground(new Color(150,255,150));
		pa_C.add(btnReset);
		btnReset.addActionListener(this);
		
	}
	
	
	public static void main(String[] args) throws Exception {
	
		new URLReaderExample();
		

	}

	private static void getName(String s) throws IOException { // ppomppuEx
		// TODO Auto-generated method stub
		try {
			URL java = new URL(s);
			BufferedReader in = new BufferedReader(new InputStreamReader(java.openStream()));
			String inLine;
			String Sresult = "";
			while ((inLine = in.readLine()) != null){
				Sresult = Sresult.concat(inLine+"\n");
			}
			in.close();
			
			//System.out.println(Sresult);
			
			String[] tempSplit;
			String[] tempSplit2;
			tempSplit = Sresult.split("<font class=view_name>");
			
/*			for(int i=0; i<tempSplit.length; i++){
				System.out.println(tempSplit[i]);
			}*/
			tempSplit2 = tempSplit[1].split("</font></a></b></span>");
			
			lbOut.setText(" 작성자의 이름을 가져왔습니다 >> " + tempSplit2[0]);
			
		} catch (MalformedURLException e) {
			System.out.println(e);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnOK){
			try {
				getName(txInput.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource() == btnReset){
			txInput.setText("");
		}
	}
}
