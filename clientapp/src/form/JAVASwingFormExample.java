package form;

import socket.scobject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import model.Cacular;


public class JAVASwingFormExample extends JFrame implements ActionListener {
    // Khai bao 4 button
    int num1;
    int num2;
    String kt;
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 8;
    private JButton btnCong;

    private JButton btnTru;
    private JButton btnNhan;
    private JButton btnChia;

    // Khai bao 3 textfield chua 2 so
    private JTextField tfNum1;
    private JTextField tfNum2;

    // Khai bao1 textfield hien thi ket qua
    private JTextField tfResult;

    // Khai bao container
    private Container cont;

    // Khai bao cac panel de gom nhom cac component
    private JPanel panel1, panel2, panel0;
    public JTextField tcn;
    public JTextField terr;

    // Ham khoi tao
    public JAVASwingFormExample(String title) {
        super(title);

        cont = this.getContentPane();
        tcn=new JTextField("Connect....");

        panel0=new JPanel();
        panel0.setLayout(new GridLayout());
        panel0.add(tcn);
        cont.add(panel0,"North");


        // Tao cac thanh phan tren giao dien khoi tao
        JLabel lbNum1 = new JLabel("Enter Number 1: ");
        tfNum1 = new JTextField();
        JLabel lbNum2 = new JLabel("Enter Number 2: ");
        tfNum2 = new JTextField();
        JLabel lbResult = new JLabel("Result: ");
        tfResult = new JTextField();

        // Vo hieu qua nhap cua tfResult
        tfResult.setEditable(false);


        // Dat cac component vao cac panel thich hop
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 2)); // Layout gom 3 hang 2 cot
        panel1.add(lbNum1);
        panel1.add(tfNum1);
        panel1.add(lbNum2);
        panel1.add(tfNum2);
        panel1.add(lbResult);
        panel1.add(tfResult);

        // Tao 4 nut cho 4 phep toan
        btnCong = new JButton("+");
        btnTru = new JButton("-");
        btnNhan = new JButton("*");
        btnChia = new JButton("/");

        // Panel2 chua 5 4 phep toan
        panel2 = new JPanel();
        panel2.add(btnCong);
        panel2.add(btnTru);
        panel2.add(btnNhan);
        panel2.add(btnChia);

        // Dat panel 1 vao vung chinh giua cua container
        cont.add(panel1);

        // Dat panel 2 vao vung ben duoi
        cont.add(panel2, "South");

        // Gan Listen cho cac button tuong ung
        btnCong.addActionListener(this);
        btnTru.addActionListener(this);
        btnNhan.addActionListener(this);
        btnChia.addActionListener(this);

        // Thiet lap kich thuoc va hien thi
        this.pack();
        this.setVisible(true);
    }

    public void connect( Cacular ca) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            System.out.println("Connected: " + socket);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            tcn.setText("Connected successfully");
            // socket senrs object
            scobject sco = new scobject();
            sco.SocketHandler(socket);
            sco.sendObject(ca);
            Cacular rs= (Cacular) sco.receiveObject();
            if(rs.getErr()==null)
            {
                System.out.println(String.valueOf(rs.getRs()));
                tfResult.setText(String.valueOf(rs.getRs()));
            }
            else {
                System.out.println(rs.getErr());
                tfResult.setText(String.valueOf(rs.getErr()));
            }




        } catch (IOException ie) {
            System.out.println("Can't connect to server");
            tcn.setText("Can't connect to server");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    // Ham thuc hien phep cong
    public void data(){
      num1 = Integer.valueOf(tfNum1.getText());
      num2 = Integer.valueOf(tfNum2.getText());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Khi click btnCong, btnTru, btnNhan, btnChia
        data();
        if (e.getActionCommand() == "+") {
            kt=e.getActionCommand();
        } else
            if (e.getActionCommand() == "-") {
                kt=e.getActionCommand();
            } else
                if (e.getActionCommand() == "*") {
                    kt=e.getActionCommand();
                } else
                    if (e.getActionCommand() == "/") {
                        kt=e.getActionCommand();
                    }
            Cacular ca=new Cacular(num1,num2,kt.charAt(0));
            try {
                connect(ca);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }



