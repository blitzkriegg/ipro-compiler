/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iprocompiler.Swing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import iprocompiler.Control.*;

/**
 *
 * @author Michael
 */
public class MainSwingFrame extends javax.swing.JFrame {
    private File file = null;
    private JFileChooser fileChooser;
    private BufferedImage bi=null;
    private ImageIcon icon=null;
    static int i = 0;
    /**
     * Creates new form MainSwingFrame
     */
    public MainSwingFrame() {
        bi = new BufferedImage(640,480, BufferedImage.TYPE_INT_RGB);
        bi.getGraphics().setColor(Color.WHITE);
        bi.getGraphics().fillRect(0, 0, 640, 480);
        icon = new ImageIcon(bi);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabPanel = new javax.swing.JTabbedPane();
        Source = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SourceEditor = new javax.swing.JTextArea();
        Output = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        OutputCanvas = new javax.swing.JTextArea();
        GraphicsOutputPanel = new javax.swing.JPanel();
        GraphicsOutput = new javax.swing.JLabel(icon);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuItem_Open = new javax.swing.JMenuItem();
        MenuItem_Save = new javax.swing.JMenuItem();
        MenuItem_SaveAs = new javax.swing.JMenuItem();
        MenuItem_Exit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MenuItem_Compile = new javax.swing.JMenuItem();
        MenuItem_Run = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuItem_About = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SourceEditor.setColumns(20);
        SourceEditor.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        SourceEditor.setRows(5);
        SourceEditor.setTabSize(5);
        jScrollPane1.setViewportView(SourceEditor);

        javax.swing.GroupLayout SourceLayout = new javax.swing.GroupLayout(Source);
        Source.setLayout(SourceLayout);
        SourceLayout.setHorizontalGroup(
            SourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        SourceLayout.setVerticalGroup(
            SourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
        );

        TabPanel.addTab("Source", Source);

        OutputCanvas.setColumns(20);
        OutputCanvas.setRows(5);
        jScrollPane2.setViewportView(OutputCanvas);

        javax.swing.GroupLayout OutputLayout = new javax.swing.GroupLayout(Output);
        Output.setLayout(OutputLayout);
        OutputLayout.setHorizontalGroup(
            OutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        OutputLayout.setVerticalGroup(
            OutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
        );

        TabPanel.addTab("Output", Output);

        GraphicsOutput.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout GraphicsOutputPanelLayout = new javax.swing.GroupLayout(GraphicsOutputPanel);
        GraphicsOutputPanel.setLayout(GraphicsOutputPanelLayout);
        GraphicsOutputPanelLayout.setHorizontalGroup(
            GraphicsOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GraphicsOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        GraphicsOutputPanelLayout.setVerticalGroup(
            GraphicsOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GraphicsOutputPanelLayout.createSequentialGroup()
                .addComponent(GraphicsOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );

        TabPanel.addTab("Graphics Output", GraphicsOutputPanel);

        jMenu1.setText("File");

        MenuItem_Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        MenuItem_Open.setText("Open");
        MenuItem_Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_OpenActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItem_Open);

        MenuItem_Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MenuItem_Save.setText("Save");
        MenuItem_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_SaveActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItem_Save);

        MenuItem_SaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        MenuItem_SaveAs.setText("Save As");
        MenuItem_SaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_SaveAsActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItem_SaveAs);

        MenuItem_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        MenuItem_Exit.setText("Exit");
        MenuItem_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_ExitActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItem_Exit);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Run");

        MenuItem_Compile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        MenuItem_Compile.setText("Compile");
        MenuItem_Compile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_CompileActionPerformed(evt);
            }
        });
        jMenu3.add(MenuItem_Compile);

        MenuItem_Run.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.CTRL_MASK));
        MenuItem_Run.setText("Run");
        MenuItem_Run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_RunActionPerformed(evt);
            }
        });
        jMenu3.add(MenuItem_Run);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Help");

        MenuItem_About.setText("About");
        MenuItem_About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItem_AboutActionPerformed(evt);
            }
        });
        jMenu2.add(MenuItem_About);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabPanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItem_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_ExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_MenuItem_ExitActionPerformed

    private void MenuItem_AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_AboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItem_AboutActionPerformed

    private void MenuItem_OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_OpenActionPerformed
        // TODO add your handling code here:
        String line;
        fileChooser = new JFileChooser();
         if(file != null)
                    fileChooser.setCurrentDirectory(new File(file.getParent()));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new ExtensionFileFilter(
                            new String[] {".txt"},
                            "Text Document (*.txt)"));
        fileChooser.addChoosableFileFilter(new ExtensionFileFilter(
                            new String[] {".ipro"},
                            "iPro Source File (*.ipro)"));
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
        file = fileChooser.getSelectedFile();
        if(!file.getName().endsWith("."+((ExtensionFileFilter)fileChooser.getFileFilter()).getExtensions().get(0)))
                            file = new File(file.getAbsolutePath()+"."+((ExtensionFileFilter)fileChooser.getFileFilter()).getExtensions().get(0));
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            SourceEditor.setText("");
            line = input.readLine();
            while (line != null) {
                SourceEditor.append(line + "\n");
		line = input.readLine();
	    }
                
            input.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch(Exception ex) {
          System.out.println("problem accessing file"+file.getAbsolutePath());
        }
        }
        else {
        System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_MenuItem_OpenActionPerformed

    private void MenuItem_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_SaveActionPerformed
        // TODO add your handling code here:
		// create and display dialog box to get file name
        boolean flag = false;
        if(file==null || !file.exists()){
            fileChooser = new JFileChooser();
                if(file != null)
                    fileChooser.setCurrentDirectory(new File(file.getParent()));
                
                    fileChooser.setAcceptAllFileFilterUsed(false);
                       fileChooser.setFileFilter(new ExtensionFileFilter(
                            new String[] {".txt"},
                            "Text Document (*.txt)"));
                        fileChooser.addChoosableFileFilter(new ExtensionFileFilter(
                            new String[] {".ipro"},
                            "iPro Source File (*.ipro)"));
                        
                         // Make sure the user didn't cancel the file chooser
                    if (fileChooser.showSaveDialog(SourceEditor) == JFileChooser.APPROVE_OPTION) {
                        // Get the file the user selected
                        file = fileChooser.getSelectedFile();
                        if(!file.getName().endsWith("."+((ExtensionFileFilter)fileChooser.getFileFilter()).getExtensions().get(0)))
                            file = new File(file.getAbsolutePath()+"."+((ExtensionFileFilter)fileChooser.getFileFilter()).getExtensions().get(0));
                        flag = true;
                        saveFile();
                    }
        }
        if(flag == false){
            if(!file.getName().endsWith("."+((ExtensionFileFilter)fileChooser.getFileFilter()).getExtensions().get(0)))
                            file = new File(file.getAbsolutePath()+"."+((ExtensionFileFilter)fileChooser.getFileFilter()).getExtensions().get(0));
            saveFile();
        }
    }//GEN-LAST:event_MenuItem_SaveActionPerformed

    private boolean saveFile(){
        boolean flag = false;
        try {
            System.out.println(file.getAbsolutePath());
            // Now write to the file
            PrintWriter output = new PrintWriter(new FileWriter(file));
            output.println(SourceEditor.getText());
            output.close();
            flag = true;
	} catch (IOException e) {
            System.out.println(e.getMessage());
	} finally{
            return flag;
        }
    }
    
    private void MenuItem_SaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_SaveAsActionPerformed
        // TODO add your handling code here:
        fileChooser = new JFileChooser();
        if(file != null)
            fileChooser.setCurrentDirectory(new File(file.getParent()));
                    fileChooser.setAcceptAllFileFilterUsed(false);
                       fileChooser.setFileFilter(new ExtensionFileFilter(
                            new String[] {".txt"},
                            "Text Document (*.txt)"));
                        fileChooser.addChoosableFileFilter(new ExtensionFileFilter(
                            new String[] {".ipro"},
                            "iPro Source File (*.ipro)"));
            // Make sure the user didn't cancel the file chooser
            if (fileChooser.showSaveDialog(SourceEditor) == JFileChooser.APPROVE_OPTION) {
		// Get the file the user selected
		file = fileChooser.getSelectedFile();
                            
                if(!file.getName().endsWith("."+((ExtensionFileFilter)fileChooser.getFileFilter()).getExtensions().get(0)))
                    file = new File(file.getAbsolutePath()+"."+((ExtensionFileFilter)fileChooser.getFileFilter()).getExtensions().get(0));
            
            try {
                System.out.println(file.getAbsolutePath());
                // Now write to the file
                PrintWriter output = new PrintWriter(new FileWriter(file));
                output.println(SourceEditor.getText());
                output.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }finally{
                fileChooser.removeAll();
            }
            }
    }//GEN-LAST:event_MenuItem_SaveAsActionPerformed
    private boolean Runable = false;
    private void MenuItem_CompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_CompileActionPerformed
        // TODO add your handling code here:
        
        IProCompiler ipro = new IProCompiler();        
        
        IproModel model = new IproModel();      
        model = ipro.syntaxCheck(getString());
        
        OutputCanvas.setText("");
        int i;
        for (i=0;i<model.ErrorMessage.size();i++){
            OutputCanvas.append(model.ErrorMessage.get(i).getMessageLine());
        }
        if (i==0){
            Runable = true;
        }
       
        
    }//GEN-LAST:event_MenuItem_CompileActionPerformed

    private void MenuItem_RunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItem_RunActionPerformed
//        if(type == 1){                                put condition if type of code is text output
//            TabPanel.setSelectedIndex(1);
//        }else{                                        else if graphical
//            TabPanel.setSelectedIndex(2);
//        }
        
        IProCompiler ipro = new IProCompiler();                
        IproModel model = new IproModel();      
      
        OutputCanvas.setText("");
        if (Runable){      
            model = ipro.StaticSymanticCheck(getString());

            for (int i=0;i<model.ErrorMessage.size();i++){
                OutputCanvas.append(model.ErrorMessage.get(i).getMessageLine());
                 Runable = false;
            }
            
            
            
            
           
        }else{
            OutputCanvas.append("Please Fix your Error / Compile First before you Run Project. ");
        }
        setPixelColor(i, i, Color.RED);
            i++;
            GraphicsOutput.repaint(1);
        
        if (Runable == true){
            ipro.MakeCodeLine(getString());
        
            for (int i=0;i<ipro.codeline.length;i++) {

                switch(ipro.RegularExpressionCheck(ipro.codeline[i])){    

                    case 0:
                       
                        OutputCanvas.append(Integer.toString(ipro.put (i, model))+'\n');
                        break;
                    case 1:
                        
                        break;
                    case 2:
                        break;

                    case 3:
                        model = ipro.set(i, model);
                        break;
                    case 4:
                        OutputCanvas.append(Integer.toString(ipro.calcuraion(i,model))+'\n');
                        break;

                    case 5:
                        break;

                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        if (ipro.codeline[i].contains("MACRO")){

                        }
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
                        i=ipro.jmp(i, model);
                        break;
                    case 14:
                        break;
                    case 15: 
                        break;

                    default:;

                }
            }
       
            
            Runable = false;
        }
        
           
        
    }//GEN-LAST:event_MenuItem_RunActionPerformed
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainSwingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainSwingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainSwingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainSwingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainSwingFrame().setVisible(true);
            }
        });
    }

    private String getString(){
        return SourceEditor.getText();
    }
    
    public void setPixelColor(int x, int y, Color color)
    {
        if(x >=0 && x <640 && y>=0 && y < 480){
            bi.setRGB(x, y, color.getRGB());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GraphicsOutput;
    private javax.swing.JPanel GraphicsOutputPanel;
    private javax.swing.JMenuItem MenuItem_About;
    private javax.swing.JMenuItem MenuItem_Compile;
    private javax.swing.JMenuItem MenuItem_Exit;
    private javax.swing.JMenuItem MenuItem_Open;
    private javax.swing.JMenuItem MenuItem_Run;
    private javax.swing.JMenuItem MenuItem_Save;
    private javax.swing.JMenuItem MenuItem_SaveAs;
    private javax.swing.JPanel Output;
    private javax.swing.JTextArea OutputCanvas;
    private javax.swing.JPanel Source;
    private javax.swing.JTextArea SourceEditor;
    private javax.swing.JTabbedPane TabPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    
    
static class ExtensionFileFilter extends FileFilter {

    private List<String> extensions;
    private String description;

    public ExtensionFileFilter(String[] exts, String desc) {
        if (exts != null) {
            extensions = new java.util.ArrayList<String>();

            for (String ext : exts) {

                // Clean array of extensions to remove "."
                // and transform to lowercase.
                extensions.add(
                    ext.replace(".", "").trim().toLowerCase()
                );
            }
        } // No else need; null extensions handled below.

        // Using inline if syntax, use input from desc or use
        // a default value.
        // Wrap with an if statement to default as well as
        // avoid NullPointerException when using trim().
        description = (desc != null) ? desc.trim() : "Custom File List";
    }

    public List<String> getExtensions(){
        return extensions;
    }
    // Handles which files are allowed by filter.
    @Override
    public boolean accept(File f) {
    
        // Allow directories to be seen.
        if (f.isDirectory()) return true;

        // exit if no extensions exist.
        if (extensions == null) return false;
		
        // Allows files with extensions specified to be seen.
        for (String ext : extensions) {
            if (f.getName().toLowerCase().endsWith("." + ext))
                return true;
        }

        // Otherwise file is not shown.
        return false;
    }

    // 'Files of Type' description
    @Override
    public String getDescription() {
        return description;
    }
}
}
