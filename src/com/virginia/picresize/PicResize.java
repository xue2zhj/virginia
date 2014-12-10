package com.virginia.picresize;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class PicResize extends JPanel implements ActionListener {
	protected JTextField picPathTxt;
	protected JTextArea logInfoTxt;
	protected JTextField iconTextTxt;
	protected JTextField iconPathTxt;

	protected String iconText = "--MIA-MIA--";
	protected String iconPath = "D:\\HDDev\\HD.Client.Common\\src\\icon\\model-convert\\frameIcon.png";

	public PicResize() {
		initUI();
	}

	public void initUI() {
		this.setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel(new GridBagLayout());
		this.add(contentPanel);

		picPathTxt = new JTextField();
		contentPanel.add(picPathTxt, new GBC(0, 0).setFill(1).setWeight(100, 0)
				.setInsets(20, 20, 20, 20).setIpad(20, 10));

		JButton chooseBtn = new JButton("选择路径");
		chooseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					picPathTxt.setText(fileChooser.getSelectedFile()
							.getAbsolutePath());// 这个就是你选择的文件夹的路径
				}
			}
		});
		contentPanel.add(chooseBtn, new GBC(1, 0).setInsets(20, 20, 20, 20)
				.setIpad(20, 10));
		
		
		iconTextTxt = new JTextField();
		iconTextTxt.setText(iconText);
		contentPanel.add(iconTextTxt, new GBC(0, 1).setInsets(20, 20, 20, 20)
				.setIpad(20, 10));
		
		iconPathTxt = new JTextField();
		iconPathTxt.setText(iconPath);
		JButton chooseIconBtn = new JButton("选择水印图片");
		chooseIconBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					iconPathTxt.setText(fileChooser.getSelectedFile()
							.getAbsolutePath());// 这个就是你选择的文件夹的路径
				}
			}
		});
		contentPanel.add(iconPathTxt, new GBC(0, 2).setInsets(20, 20, 20, 20)
				.setIpad(20, 10));
		contentPanel.add(chooseIconBtn, new GBC(1, 2).setInsets(20, 20, 20, 20)
				.setIpad(20, 10));
		

		JButton resizeBtn = new JButton("Resize");
		resizeBtn.setActionCommand("RESIZE_PIC");
		resizeBtn.addActionListener(this);

		contentPanel.add(resizeBtn, new GBC(0, 3).setInsets(20, 20, 20, 20)
				.setIpad(20, 10));

		logInfoTxt = new JTextArea();
		contentPanel.add(new JScrollPane(logInfoTxt), new GBC(0, 4, 2, 1)
				.setInsets(20, 20, 20, 20).setFill(1).setWeight(100, 100));

		// JPanel panel = new JPanel();
		// this.add(panel, BorderLayout.SOUTH);
		//
		// JButton resizeBtn = new JButton("Resize");
		// resizeBtn.setActionCommand("RESIZE_PIC");
		// resizeBtn.addActionListener(this);
		// panel.add(resizeBtn);

	}

	public void setIconText(String iconText) {
		this.iconText = iconText;
	}

	public void batchZoom(final String path, final String iconText, final String iconPath) {

		new SwingWorker<Object, String>() {

			@Override
			protected Object doInBackground() throws Exception {
				File filePath = new File(path);
				if (!filePath.isDirectory()) {
					logInfoTxt.setText("非文件路径！\r\n" + logInfoTxt.getText());
					System.out.println("非文件路径！");
					return null;
				}

				String filePathDir = filePath.getAbsolutePath();

				// 遍历下面所有的文件
				File[] fs = filePath.listFiles();
				for (int i = 0; i < fs.length; i++) {
					// logInfoTxt.setText("convert " +
					// fs[i].getAbsolutePath()+logInfoTxt.getText());
					System.out.println("convert " + fs[i].getAbsolutePath());

					String renameStr = filePathDir + "\\" + i + ".JPG";

					try {
						PicTools.zoom(fs[i].getAbsolutePath(), renameStr, 640,
								480, true);

						if(iconText!=null && !iconText.trim().equals("")){
							ImageMarkLogoUtil.markImageByText(iconText, renameStr,
									renameStr, -30);
						}
						
						if(iconPath!=null && !iconPath.trim().equals("")){
							ImageMarkLogoByIcon.markImageByIcon(iconPath, renameStr,renameStr, -30);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					publish(fs[i].getAbsolutePath());
					Thread.sleep(100);
				}
				return null;
			}

			@Override
			protected void done() {
				logInfoTxt.setText("convert ok\r\n" + logInfoTxt.getText());
			}

			@Override
			protected void process(List<String> chunks) {
				logInfoTxt.setText("convert " + chunks.get(chunks.size() - 1)
						+ "\r\n" + logInfoTxt.getText());
			}

		}.execute();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("RESIZE_PIC")) {
			logInfoTxt.setText("start convert...\r\n");
			batchZoom(picPathTxt.getText(),iconTextTxt.getText(), iconPathTxt.getText());
		}

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		PicResize picResize = new PicResize();

		frame.add(picResize);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
