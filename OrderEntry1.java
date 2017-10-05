import javax.swing.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane.*;

import java.awt.*;
import java.awt.event.*;



public class OrderEntry extends JFrame {
	
		  private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7 ;
		  private JLabel actLbl, qty, dropBoxText, priceText, lmt, tIF, checkText, share;
		  private JTextField qtyText, priceInputText;
		  private JComboBox stocks;
		  private String side, side2, side3;
		  private Boolean check1;
		  private JCheckBox check;
		  private JButton placeOrder, clear, cancel;
		  private JRadioButton buyButton, sellButton, sellShortButton, mkt, priceInput, day, gTC;
		  private ButtonGroup radioButtonGroup1, radioButtonGroup2, radioButtonGroup3;
		  private final int WINDOW_WIDTH=350;
		  private final int WINDOW_HEIGHT=400;
		  private String[] stockNames= {" ","IBM", "GE", "MSFT", "AAPL", "YHOO",
				  "FTSE 100", "GOOG", "DOW J", "NASDAQ", "DAX",
				  "NIKKEI 225", "TMUS", "T", "S", "VZ",
				  "SBUX", "NYSE", "NKE", "S&P", "BAC"};
		  
	  
public OrderEntry(){
	
	      setTitle("Order Entry");
	      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setLayout(new FlowLayout());
	      buildPanel1();
	      buildPanel2();
	      buildPanel3();
	      buildPanel4();
	      buildPanel5();
	      buildPanel6();
	      buildPanel7();
	      add(panel1);
	      add(panel2);
	      add(panel3);
	      add(panel4);
	      add(panel5);
	      add(panel6);
	      add(panel7);
	      setVisible(true);
	   }

	  private void buildPanel1(){
		  
		 actLbl = new JLabel("Action: ");
		 buyButton= new JRadioButton("Buy");
		 sellButton= new JRadioButton("Sell");
		 sellShortButton= new JRadioButton("Short Sell");
		 
		 radioButtonGroup1= new ButtonGroup();
		 radioButtonGroup1.add(buyButton);
		 radioButtonGroup1.add(sellButton);
		 radioButtonGroup1.add(sellShortButton);
		 
		 buyButton.addActionListener(new RadioButtonListener());
		 sellButton.addActionListener(new RadioButtonListener());
		 sellShortButton.addActionListener(new RadioButtonListener());
		 panel1= new JPanel();
		 panel1.add(actLbl);
		 panel1.add(buyButton);
		 panel1.add(sellButton);
		 panel1.add(sellShortButton);
		 }
	  private class RadioButtonListener implements ActionListener{
		  
		  public void actionPerformed(ActionEvent e){			 
			  if (e.getSource()==buyButton){
				  JOptionPane.showMessageDialog(null, "Buy Selected");	
				  side="Buy";
			  }
			  else if (e.getSource()==sellButton){
				  JOptionPane.showMessageDialog(null, "Sell Selected");  
				  side="Sell";
			  }
			  else if(e.getSource()==sellShortButton){
				  JOptionPane.showMessageDialog(null, "Short Sell Selected");  
				  side="Short Sell";
			  }
			  else
				  JOptionPane.showMessageDialog(null,"Invalid Source");
			 
		  }

	  }
	private void buildPanel2(){
		 qty= new JLabel("Quantity: ");
		 qtyText= new JTextField(7);
		 share= new JLabel(" Share");
		 qtyText.addActionListener(new QuantityTextListener());
		 panel2=new JPanel();
		 panel2.add(qty);
		 panel2.add(qtyText);
		 panel2.add(share);
	}
		private class QuantityTextListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				String qtyNum=qtyText.getText();
				int i=Integer.parseInt(qtyNum);
				if(i>=1 && i<=10000 ){
					JOptionPane.showMessageDialog(null, "You entered a good share quantity");
				}
				else if (i<1 || i>10000){
					JOptionPane.showMessageDialog(null, "Incorrect Input, Please enter an integer 1-10,000");
				}
			}
		}
	private void buildPanel3(){
		 dropBoxText= new JLabel("Label: ");
		 stocks= new JComboBox(stockNames);
		 stocks.addActionListener(new ComboBoxListener());
		 panel3= new JPanel();
		 panel3.add(dropBoxText);
		 panel3.add(stocks);
	}
	private class ComboBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String selection=(String) stocks.getSelectedItem();
			JOptionPane.showMessageDialog(null,"You selected "+selection);
		}
	}
	private void buildPanel4(){
		
		 priceText= new JLabel("Price: ");
		 mkt= new JRadioButton("MKT");
		 priceInputText= new JTextField(7);
		 priceInput=new JRadioButton(" ");
		 lmt= new JLabel("LMT");
		 
		 radioButtonGroup2= new ButtonGroup();
		 radioButtonGroup2.add(mkt);
		 radioButtonGroup2.add(priceInput);
		 
		 priceInput.equals(priceInputText);
		 
		 mkt.addActionListener(new TextFieldReadOnly());
		 priceInput.addActionListener(new TextFieldReadOnly());
		 
		 panel4= new JPanel();
		 
		 panel4.add(priceText);
		 panel4.add(mkt);
		 panel4.add(priceInput);
		 panel4.add(priceInputText);
		 panel4.add(lmt);
		 
	  }
	private class TextFieldReadOnly implements ActionListener{
		public void actionPerformed(ActionEvent e){
			double tPrice;
			if(e.getSource()==mkt){
				priceInputText.setEditable(false);
				JOptionPane.showMessageDialog(null, "You selected market price.");
				side2="Market Price";
			
			}
			else if(e.getSource()==priceInput){
				priceInputText.setEditable(true);
				JOptionPane.showMessageDialog(null, "You selected to enter the price.");
				side2=priceInputText.getText();
				
			}
		}
	}
	private void buildPanel5(){
		tIF= new JLabel("Time in Force: ");
		day= new JRadioButton("DAY");
		gTC= new JRadioButton("GTC");
		
		radioButtonGroup3= new ButtonGroup();
		radioButtonGroup3.add(day);
		radioButtonGroup3.add(gTC);
		
		day.addActionListener(new TimeListener());
		gTC.addActionListener(new TimeListener());
		
		panel5= new JPanel();
		panel5.add(tIF);
		panel5.add(day);
		panel5.add(gTC);
	}
	private class TimeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==day){
				JOptionPane.showMessageDialog(null, "You selected Day");
				side3="Day";
			}
			else if(e.getSource()==gTC){
				JOptionPane.showMessageDialog(null, "You seleceted GTC");
				side3="GTC";
			}
		}
	}
	
	private void buildPanel6(){
		check=new JCheckBox("Skip Order Preview");
		check.addActionListener(new CheckBoxListener());
		panel6= new JPanel();
		panel6.add(check);
		
	}
	private class CheckBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource()==check){
				check1=true;
				JOptionPane.showMessageDialog(null, "You selected to skip preview.");
				
			}
			else
				check1=false;
				
			
		}
	}
	private void buildPanel7(){
		placeOrder= new JButton("Place Order");
		placeOrder.setBackground(Color.GREEN);
		clear= new JButton("Clear");
		cancel= new JButton("Cancel");
		cancel.setBackground(Color.RED);
		placeOrder.addActionListener(new DecisionListener());
		clear.addActionListener(new DecisionListener());
		cancel.addActionListener(new DecisionListener());
		panel7= new JPanel();
		panel7.add(placeOrder);
		panel7.add(clear);
		panel7.add(cancel);
	}
	
	private class DecisionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
				
					
				
			   if(e.getSource()==placeOrder){
				if(check1=false){
				Object[] options = {"Send","Cancel"};
				int n = JOptionPane.showOptionDialog(null,
						"You chose to: "+side+" \nQuantity: "+qtyText.getText()+"\nLabel: "+stocks.getSelectedItem()+"\nPrice: "+side2+"\nTime in Force: "+side3,
						"Preview Order",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				if(n==JOptionPane.YES_OPTION){
					JOptionPane.showMessageDialog(null,"Order will be sent");
					FileWriter fw= null;
					File file = null;
					try {
					file = new File("OrderEntry12.txt");
					if(!file.exists()){
						file.createNewFile();
					}
					fw= new FileWriter(file);
					fw.write("You chose to: "+radioButtonGroup1.getSelection()+" \nQuantity: "+qtyText.getText()+"\nLabel: "+stocks.getSelectedItem()+"\nPrice: "+radioButtonGroup2.getSelection()+"\nTime in Force: "+radioButtonGroup3.getSelection());
					fw.flush();
					fw.close();
					JOptionPane.showMessageDialog(null,"File written successfully");
					}
					catch (IOException d){
						d.printStackTrace();
					}
				}
					
				else if(n==JOptionPane.NO_OPTION){
					JOptionPane.showMessageDialog(null, "Order Canceled");
				}
				
				}
				else if(check1=true){
					Object[] o = {"Send","Cancel"};
					int l = JOptionPane.showOptionDialog(null,
							"Send or Cancel Order",
							"Order",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							o,
							o[1]);
					if(l==JOptionPane.YES_OPTION){
						JOptionPane.showMessageDialog(null,"Order will be sent");
						FileWriter fw= null;
						File file = null;
						try {
						file = new File("OrderEntry12.txt");
						if(!file.exists()){
							file.createNewFile();
						}
						fw= new FileWriter(file);
						fw.write("You chose to: "+radioButtonGroup1.getSelection()+" \nQuantity: "+qtyText.getText()+"\nLabel: "+stocks.getSelectedItem()+"\nPrice: "+radioButtonGroup2.getSelection()+"\nTime in Force: "+radioButtonGroup3.getSelection());
						fw.flush();
						fw.close();
						JOptionPane.showMessageDialog(null,"File written successfully");
						}
						catch (IOException d){
							d.printStackTrace();
						}
				}
					else if(l==JOptionPane.NO_OPTION){
						JOptionPane.showMessageDialog(null, "Order Canceled");
					}
				
			}
			   
			}
			   else if(e.getSource()==clear){
				   radioButtonGroup1.clearSelection();
				   qtyText.setText(" ");
				   stocks.setSelectedItem(" ");
				   radioButtonGroup2.clearSelection();
				   radioButtonGroup3.clearSelection();
				   priceInputText.setText(" ");
				   check.setEnabled(false);
				   
			   }
			   else if (e.getSource()==cancel){
				   System.exit(0);
			   }
		}
	}

	  
	  public static void main (String[] args) throws IOException{
		  new OrderEntry();
		  new FlowLayout();
	  }
}

