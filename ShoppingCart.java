//Course Number & Section: CIS5200-W01
//Assignment Designation: Project 12 – 29.4 pg. 1275
//Name: Akshay Artham

package project12; //package name

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShoppingCart extends JFrame
{
    //start of class ShoppingCart
    private static JPanel lPanel; //list
    private static JPanel scPanel; //shooping cart
    private static JPanel bPanel; //buttons
    private static JList listItems;
  
    private static JButton addB; //button
    private static JButton removeB; //button
    private static JButton clearB; //button
    private static JButton checkOutB; //button
  
    private static String[] listAr=new String[100]; //Array
    private static List cartItem=new List();
  
    //value for salestax
    final double salesTax=0.06;
  
    //creation of a constructor 
    public ShoppingCart() throws FileNotFoundException
    {
        //application title
        setTitle("Shopping Cart Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,3));
        setLocationRelativeTo(null);
        buildListPanel();
        buildButtonPanel();
        buildCartPanel();
        
        add(lPanel);
        add(bPanel);
        add(scPanel);
        pack();
        setVisible(true);
    }
  
    //addition of buttons; add,remove,clear and checkout 
    private void buildButtonPanel()
    {
        bPanel=new JPanel();
        bPanel.setLayout(new GridLayout(4,1));
  
        addB=new JButton("Add To Cart");
        addB.addActionListener(new AddButtonListener());
        removeB=new JButton("Remove From Cart");
  
        removeB.addActionListener(new RemoveButtonListener());
  
        clearB=new JButton("Clear Cart");
        clearB.addActionListener(new clearButtonListener());

        checkOutB = new JButton("Check Out");
        checkOutB.addActionListener(new CheckoutButtonListener());
  
        bPanel.add(addB);
        bPanel.add(removeB);
        bPanel.add(clearB);
        bPanel.add(checkOutB);
    }
  
    public class AddButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent arg)
        {
            String v=(String) listItems.getSelectedValue();
            cartItem.add(v);   
        }
    }
  
    public class RemoveButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            String stri=cartItem.getSelectedItem();
            cartItem.remove(stri);
        }
    }
  
    public class clearButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            cartItem.removeAll();
        }
    }
  
    private void buildCartPanel() 
    {
        scPanel=new JPanel();
        scPanel.setLayout(new BorderLayout());
        scPanel.setBorder(
        BorderFactory.createEtchedBorder());
  
        JLabel cartLabl= new JLabel("Cart");
        cartLabl.setFont(new Font("Times New Roman", Font.BOLD, 18));
        scPanel.add(cartLabl, BorderLayout.NORTH);
        scPanel.add(cartItem, BorderLayout.CENTER);
    }
  
    public class CheckoutButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            String l;
            double totalC=0; //cost
            double costofI=0; //item
            File file=new File("C:\\Users\\MAHIMA SHUKLA\\Desktop\\HOMEWORK\\project12\\BookPrices.txt");
            Scanner fileReader=null;
            try 
            {
                fileReader=new Scanner(file);
            }
            catch(FileNotFoundException e1) 
            {
                e1.printStackTrace();
            }
  
            while(fileReader.hasNext())
            {
                l=fileReader.nextLine();
                String[] cost=l.split(",");
                
                String title=cost[0];
                costofI= Double.parseDouble(cost[1]);
  
                for(int a=0; a<cartItem.getItemCount(); a++) 
                {
                    if(title.equals(cartItem.getItem(a)))totalC += costofI;
                }
            }
  
            //calculate tax amount for total cost
            double tax=salesTax * totalC;
            DecimalFormat myFormatter=new DecimalFormat("###.##");
            //display the total cost in message box
  
            JOptionPane.showMessageDialog(null, "Total Cost is:"+myFormatter.format(tax+totalC));
        }
    }
  
    //method creates the list panel with one list
    private void buildListPanel() throws FileNotFoundException 
    {
        lPanel=new JPanel();
        lPanel.setLayout(new BorderLayout());
        lPanel.setBorder(BorderFactory.createEtchedBorder());
  
        JLabel lab=new JLabel("Select A Book Title");
  
        lab.setFont(new Font("Times New Roman", Font.BOLD, 18));
  
        String line;
        int in=0; //index
        //getting the book titles from txt file
        File file=new File("C:\\Users\\MAHIMA SHUKLA\\Desktop\\HOMEWORK\\project12\\BookPrices.txt");
        Scanner fileReader=new Scanner(file);
  
        while(fileReader.hasNext())
        {
            line=fileReader.nextLine();
            String[] titles=line.split(",");
            listAr[in]=titles[0];
            in++;
        }
        listItems = new JList(listAr);
        lPanel.add(lab,BorderLayout.NORTH);
        lPanel.add(listItems,BorderLayout.CENTER);
    }
    public static void main(String[] args) throws FileNotFoundException 
    {
        new ShoppingCart();
    }
} //end of class ShoppingCart