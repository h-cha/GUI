package ex14;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ScheduleBookGUI1 extends JFrame {
	JTextField dateField, addressField, timeField, memoField, dayField;
	DefaultListModel model,model1, model2, model3, model4, model5, model6, model7, model8, model9, model10, model11, model12,
			model13, model14, model15, model16, model17, model18, model19, model20, model21, model22, model23, model24,
			model25, model26, model27, model28, model29, model30, model31;
	JList list;
	JButton addButton, removeButton, updateButton;
	JPanel pane;
	JPanel calender;
	ScheduleBook book;
	ButtonGroup bg1;
	static int monthNum = 0;

	public static void main(String[] args) {
		JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",3);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(800, 500);
		w.setVisible(true);
	}
	
	public static void sub(){
		JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(800, 500);
		w.setVisible(true);
		System.out.println("sub");
	}

	public ScheduleBookGUI1(String title,int monthNum) {
		super(title);
		book = new ScheduleBook();
		pane = (JPanel) getContentPane();
		calender = (JPanel) getContentPane();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("ファイル");
		menuBar.add(fileMenu);
		JMenuItem item;
		item = new JMenuItem(new ExitAction());
		fileMenu.add(item);

		JMenu menu1 = new JMenu("2020年");
		menuBar.add(menu1);
		JMenuItem month;


		bg1 = new ButtonGroup();
		for (int i = 0; i < 12; i++) {
			item = new JRadioButtonMenuItem(new MonthAction(i + 1));
			menu1.add(item);
			bg1.add(item);
		}
		
		createMonth(monthNum);

		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));

		dateField = new JTextField(20);
		dateField.setBorder(new TitledBorder("日付(日にちを数字のみで入力）"));
		fields.add(dateField);
		addressField = new JTextField(20);
		addressField.setBorder(new TitledBorder("場所"));
		fields.add(addressField);
		timeField = new JTextField(20);
		timeField.setBorder(new TitledBorder("時間"));
		fields.add(timeField);
		memoField = new JTextField(20);
		memoField.setBorder(new TitledBorder("メモ"));
		fields.add(memoField);

		addButton = new JButton(new AddAction());
		fields.add(addButton);

		pane.add(fields, BorderLayout.EAST);
	}

	public void createMonth(int num) {
		JPanel pane = (JPanel) getContentPane(); // コンテントペインを得る

		JButton buttonNorth = new JButton(num + "月"); // ボタン生成
		pane.add(buttonNorth, BorderLayout.NORTH); // 配置位置を指定してボタン追加

		JPanel panelCenter = new JPanel(new BorderLayout());
		pane.add(panelCenter, BorderLayout.CENTER);

		// 何曜日始まりか

		if (num == 6) {
			panelCenter.add(new JButton("月"));
			panelCenter.add(new JButton("火"));
			panelCenter.add(new JButton("水"));
			panelCenter.add(new JButton("木"));
			panelCenter.add(new JButton("金"));
			panelCenter.add(new JButton("土"));
			panelCenter.add(new JButton("日"));
		}
		if (num == 9 || num == 12) {
			panelCenter.add(new JButton("火"));
			panelCenter.add(new JButton("水"));
			panelCenter.add(new JButton("木"));
			panelCenter.add(new JButton("金"));
			panelCenter.add(new JButton("土"));
			panelCenter.add(new JButton("日"));
			panelCenter.add(new JButton("月"));
		}
		if (num == 1 || num == 4 || num == 7) {
			panelCenter.add(new JButton("水"));
			panelCenter.add(new JButton("木"));
			panelCenter.add(new JButton("金"));
			panelCenter.add(new JButton("土"));
			panelCenter.add(new JButton("日"));
			panelCenter.add(new JButton("月"));
			panelCenter.add(new JButton("火"));
		}
		if (num == 10) {
			panelCenter.add(new JButton("木"));
			panelCenter.add(new JButton("金"));
			panelCenter.add(new JButton("土"));
			panelCenter.add(new JButton("日"));
			panelCenter.add(new JButton("月"));
			panelCenter.add(new JButton("火"));
			panelCenter.add(new JButton("水"));
		}
		if (num == 5) {
			panelCenter.add(new JButton("金"));
			panelCenter.add(new JButton("土"));
			panelCenter.add(new JButton("日"));
			panelCenter.add(new JButton("月"));
			panelCenter.add(new JButton("火"));
			panelCenter.add(new JButton("水"));
			panelCenter.add(new JButton("木"));
		}
		if (num == 2 || num == 8) {
			panelCenter.add(new JButton("土"));
			panelCenter.add(new JButton("日"));
			panelCenter.add(new JButton("月"));
			panelCenter.add(new JButton("火"));
			panelCenter.add(new JButton("水"));
			panelCenter.add(new JButton("木"));
			panelCenter.add(new JButton("金"));
		}
		if (num == 3 || num == 11) {
			panelCenter.add(new JButton("日"));
			panelCenter.add(new JButton("月"));
			panelCenter.add(new JButton("火"));
			panelCenter.add(new JButton("水"));
			panelCenter.add(new JButton("木"));
			panelCenter.add(new JButton("金"));
			panelCenter.add(new JButton("土"));
		}

		panelCenter.setLayout(new GridLayout(6, 7)); // レイアウト方法を指定

		// カレンダーのリスト作成
		model1 = new DefaultListModel();
		list = new JList(model1);
		JScrollPane sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("1"));
		panelCenter.add(sc);

		model2 = new DefaultListModel();
		list = new JList(model2);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("2"));
		panelCenter.add(sc);

		model3 = new DefaultListModel();
		list = new JList(model3);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("3"));
		panelCenter.add(sc);

		model4 = new DefaultListModel();
		list = new JList(model4);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("4"));
		panelCenter.add(sc);

		model5 = new DefaultListModel();
		list = new JList(model5);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("5"));
		panelCenter.add(sc);

		model6 = new DefaultListModel();
		list = new JList(model6);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("6"));
		panelCenter.add(sc);

		model7 = new DefaultListModel();
		list = new JList(model7);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("7"));
		panelCenter.add(sc);

		model8 = new DefaultListModel();
		list = new JList(model8);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("8"));
		panelCenter.add(sc);

		model9 = new DefaultListModel();
		list = new JList(model9);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("9"));
		panelCenter.add(sc);

		model10 = new DefaultListModel();
		list = new JList(model10);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("10"));
		panelCenter.add(sc);

		model11 = new DefaultListModel();
		list = new JList(model11);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("11"));
		panelCenter.add(sc);

		model12 = new DefaultListModel();
		list = new JList(model12);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("12"));
		panelCenter.add(sc);

		model13 = new DefaultListModel();
		list = new JList(model13);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("13"));
		panelCenter.add(sc);

		model14 = new DefaultListModel();
		list = new JList(model14);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("14"));
		panelCenter.add(sc);

		model15 = new DefaultListModel();
		list = new JList(model15);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("15"));
		panelCenter.add(sc);

		model16 = new DefaultListModel();
		list = new JList(model16);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("16"));
		panelCenter.add(sc);

		model17 = new DefaultListModel();
		list = new JList(model17);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("17"));
		panelCenter.add(sc);

		model18 = new DefaultListModel();
		list = new JList(model18);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("18"));
		panelCenter.add(sc);

		model19 = new DefaultListModel();
		list = new JList(model19);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("19"));
		panelCenter.add(sc);

		model20 = new DefaultListModel();
		list = new JList(model20);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("20"));
		panelCenter.add(sc);

		model21 = new DefaultListModel();
		list = new JList(model21);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("21"));
		panelCenter.add(sc);

		model22 = new DefaultListModel();
		list = new JList(model22);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("22"));
		panelCenter.add(sc);

		model23 = new DefaultListModel();
		list = new JList(model23);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("23"));
		panelCenter.add(sc);

		model24 = new DefaultListModel();
		list = new JList(model24);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("24"));
		panelCenter.add(sc);

		model25 = new DefaultListModel();
		list = new JList(model25);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("25"));
		panelCenter.add(sc);

		model26 = new DefaultListModel();
		list = new JList(model26);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("26"));
		panelCenter.add(sc);

		model27 = new DefaultListModel();
		list = new JList(model27);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("27"));
		panelCenter.add(sc);

		model28 = new DefaultListModel();
		list = new JList(model28);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("28"));
		panelCenter.add(sc);

		model29 = new DefaultListModel();
		list = new JList(model29);
		sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("29"));
		panelCenter.add(sc);

		if(num == 4 || num == 6 || num == 9 || num == 11) {
			model30 = new DefaultListModel();
			list = new JList(model30);
			sc = new JScrollPane(list);
			sc.setBorder(new TitledBorder("30"));
			panelCenter.add(sc);
			for(int i = 0; i < 5; i ++) {
				model = new DefaultListModel();
				list = new JList(model);
				sc = new JScrollPane(list);
				panelCenter.add(sc);
			}
		}
		if(num == 1 || num == 3 || num == 5 || num == 7 || num == 8 || num == 10 || num == 12) {
			model30 = new DefaultListModel();
			list = new JList(model30);
			sc = new JScrollPane(list);
			sc.setBorder(new TitledBorder("30"));
			panelCenter.add(sc);

			model31 = new DefaultListModel();
			list = new JList(model31);
			sc = new JScrollPane(list);
			sc.setBorder(new TitledBorder("31"));
			panelCenter.add(sc);
			for(int i = 0; i < 4; i ++) {
				model = new DefaultListModel();
				list = new JList(model);
				sc = new JScrollPane(list);
				panelCenter.add(sc);
			}
		}
		if(num == 2) {
			for(int i = 0; i < 6; i ++) {
				model = new DefaultListModel();
				list = new JList(model);
				sc = new JScrollPane(list);
				panelCenter.add(sc);
			}
		}
	}

	class MonthAction extends AbstractAction {
		MonthAction(int num) {
			putValue(Action.NAME, num + "月");
			putValue(Action.SHORT_DESCRIPTION, num + "月");
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("1月")) {
				System.out.println("1月を選択しました");
				monthNum = 1;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
				
			} else if (e.getActionCommand().equals("2月")) {
				System.out.println("2月を選択しました");
				monthNum = 2;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			} else if (e.getActionCommand().equals("3月")) {
				System.out.println("3月を選択しました");
				monthNum = 3;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}else if (e.getActionCommand().equals("4月")) {
				System.out.println("4月を選択しました");
				monthNum = 4;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}else if (e.getActionCommand().equals("5月")) {
				System.out.println("5月を選択しました");
				monthNum = 5;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}else if (e.getActionCommand().equals("6月")) {
				System.out.println("6月を選択しました");;
				monthNum = 6;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}else if (e.getActionCommand().equals("7月")) {
				System.out.println("7月を選択しました");
				monthNum = 7;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}else if (e.getActionCommand().equals("8月")) {
				System.out.println("8月を選択しました");
				monthNum = 8;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}else if (e.getActionCommand().equals("9月")) {
				System.out.println("9月を選択しました");
				monthNum = 9;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}else if (e.getActionCommand().equals("10月")) {
				System.out.println("10月を選択しました");
				monthNum = 10;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}else if (e.getActionCommand().equals("11月")) {
				System.out.println("11月を選択しました");
				monthNum = 11;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}else if (e.getActionCommand().equals("12月")) {
				System.out.println("12月を選択しました");
				monthNum = 12;
				JFrame w = new ScheduleBookGUI1("ScheduleBookGUI",monthNum);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(800, 500);
				w.setVisible(true);
			}
		}
	}

	class ExitAction extends AbstractAction {
		ExitAction() {
			putValue(Action.NAME, "終了");
			putValue(Action.SHORT_DESCRIPTION, "終了");
		}

		public void actionPerformed(ActionEvent e) {
			Object[] msg = { "終了しますか？" };
			int ans = JOptionPane.showConfirmDialog(pane, msg, "はい・いいえ・取消し",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (ans == 0) {
				System.exit(0);
			}
		}
	}

	class AddAction extends AbstractAction {
		AddAction() {
			putValue(Action.NAME, "追加");
			putValue(Action.SHORT_DESCRIPTION, "追加");
		}

		public void actionPerformed(ActionEvent e) {
			String date = dateField.getText();
			String address = addressField.getText();
			String time = timeField.getText();
			String memo = memoField.getText();
			Schedule addSchedule = new Schedule(date, address, time, memo);
			DefaultListModel model = (DefaultListModel) list.getModel();
			boolean add = true; // true だと追加
			if (date.equals("") || address.equals("") || time.equals("") || memo.equals("")) {
				add = false;
				System.out.println("すべての項目に入力してください");
			}
			int num = 0;
			try {
				num = Integer.parseInt(date);
			}catch(NumberFormatException e1) {
				System.out.println("数字のみ入力してください");
			}

			if (add == true) {
				switch (num) {
				case 1:
					model1.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 2:
					model2.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 3:
					model3.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 4:
					model4.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 5:
					model5.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 6:
					model6.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 7:
					model7.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 8:
					model8.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 9:
					model9.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 10:
					model10.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 11:
					model11.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 12:
					model12.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 13:
					model13.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 14:
					model14.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 15:
					model15.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 16:
					model16.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 17:
					model17.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 18:
					model18.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 19:
					model19.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 20:
					model20.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 21:
					model21.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 22:
					model22.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 23:
					model23.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 24:
					model24.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 25:
					model25.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 26:
					model26.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 27:
					model27.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 28:
					model28.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 29:
					model29.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 30:
					model30.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				case 31:
					model31.addElement(time + "/" + memo + "/" + address);
					book.add(addSchedule);
					break;
				default:
					System.out.println("日にちを入力してください");
				}
			}
			dateField.setText("");
			addressField.setText("");
			timeField.setText("");
			memoField.setText("");
		}
	}
}