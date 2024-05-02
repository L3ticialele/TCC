/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.cefetmg.space.view;

import br.cefetmg.space.model.dao.UsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author letic
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
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

        jLabel2 = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();
        campoUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        botaoLogin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        linkCadastrar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Inserir_um_título-removebg-preview.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(120, 10, 80, 80);

        campoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(campoSenha);
        campoSenha.setBounds(70, 170, 160, 20);
        getContentPane().add(campoUser);
        campoUser.setBounds(70, 120, 160, 22);

        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Usuário/E-mail:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(70, 100, 90, 16);

        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Senha:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(70, 150, 50, 16);

        botaoLogin.setBackground(new java.awt.Color(0, 0, 153));
        botaoLogin.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        botaoLogin.setForeground(new java.awt.Color(255, 255, 255));
        botaoLogin.setText("LOGIN");
        botaoLogin.setActionCommand("login");
        botaoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLoginActionPerformed(evt);
            }
        });
        getContentPane().add(botaoLogin);
        botaoLogin.setBounds(80, 210, 140, 50);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText(" Não possui uma conta?");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 270, 130, 20);

        linkCadastrar.setForeground(new java.awt.Color(0, 0, 204));
        linkCadastrar.setText("Cadastre-se");
        linkCadastrar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                linkCadastrarMouseMoved(evt);
            }
        });
        linkCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkCadastrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                linkCadastrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                linkCadastrarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                linkCadastrarMouseReleased(evt);
            }
        });
        getContentPane().add(linkCadastrar);
        linkCadastrar.setBounds(190, 270, 80, 20);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/novo.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 310, 336);

        setSize(new java.awt.Dimension(321, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSenhaActionPerformed

    private void botaoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLoginActionPerformed
        // TODO add your handling code here:
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String user = campoUser.getText();
        String senha = new String(campoSenha.getPassword());
        try {
            if(usuarioDAO.procurarPorUserName(user) == null && usuarioDAO.procurarPorEmail(user) == null){
                 JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            }else{
                if(usuarioDAO.procurarPorEmail(user) != null && usuarioDAO.procurarPorEmail(user).getSenha().equals(senha)|| usuarioDAO.procurarPorUserName(user) != null && usuarioDAO.procurarPorUserName(user).getSenha().equals(senha) ){
                    JOptionPane.showMessageDialog(null, "Logado!");
            }else{
                JOptionPane.showMessageDialog(null,"Usuário ou senha incorretos.");
                 }
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoLoginActionPerformed

    private void linkCadastrarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkCadastrarMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_linkCadastrarMouseMoved

    private void linkCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkCadastrarMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Olá.");
    }//GEN-LAST:event_linkCadastrarMouseClicked

    private void linkCadastrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkCadastrarMouseEntered
        // TODO add your handling code here
            linkCadastrar.setForeground(new Color(242, 242, 242)); 
    }//GEN-LAST:event_linkCadastrarMouseEntered

    private void linkCadastrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkCadastrarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_linkCadastrarMouseExited

    private void linkCadastrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkCadastrarMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_linkCadastrarMouseReleased

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoLogin;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JTextField campoUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel linkCadastrar;
    // End of variables declaration//GEN-END:variables
}
