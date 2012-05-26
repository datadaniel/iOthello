package org.iothello.gui;

import org.iothello.service.Server3Interface;
import org.iothello.service.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import javax.swing.text.DefaultCaret;

import org.iothello.gui.dialogs.HighscoreViewer;

import org.iothello.logic.players.NetComputerLocal;
import org.iothello.logic.players.NetPlayerExternal;
import org.iothello.logic.players.NetPlayerLocal;
import org.iothello.logic.Othello;
import org.iothello.logic.players.Player;

/**
 * 
 * @author Johan Dahlberg <info@johandahlberg.com>
 */
public class LobbyLogin extends JFrame {
    private Server3Interface si;
    private List<User> users = new ArrayList();
    private boolean result;
    private boolean boundToRegistry = false;
    private boolean loggedin = false;
    private User me;
    private Player player1;
    private Player player2;

    /** Creates new form LobbyLogin */
    public LobbyLogin() throws RemoteException, NotBoundException {

        initComponents();
        jtaChat.append("Testanandare:\nname\tpassword\nname2\tpassword\n");
        this.addWindowListener(new WindowListener() {

            

            @Override
            public void windowClosing(WindowEvent e) {
                if (loggedin) {
                    try {
                        si.disconnect(jtxName.getText());
                    } catch (RemoteException ex) {
                        Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //  throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //    throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowOpened(WindowEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        //skapar en random portnummer f�r att f�renkla n�r man k�r tv� klienter p� localhost.
        jtxMinPort.setText(String.valueOf(new Random().nextInt(30000) + 1500));
      
        this.setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtxName = new javax.swing.JTextField();
        jtxPassword = new javax.swing.JTextField();
        jtxMinPort = new javax.swing.JTextField();
        jbtLogin = new javax.swing.JButton();
        jbtCreateUser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlsUsers = new javax.swing.JList();
        jtxMessage = new javax.swing.JTextField();
        jbtChallenge = new javax.swing.JButton();
        jbtHighscore = new javax.swing.JButton();
        jlbName = new javax.swing.JLabel();
        jtxServerIP = new javax.swing.JTextField();
        jlbPassword = new javax.swing.JLabel();
        jlbServerIP = new javax.swing.JLabel();
        jlbMinPort = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jtxName.setText("name");
        jtxName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxNameFocusGained(evt);
            }
        });

        jtxPassword.setText("password");
        jtxPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxPasswordFocusGained(evt);
            }
        });

        jtxMinPort.setText("6544");
        jtxMinPort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxMinPortFocusGained(evt);
            }
        });

        jbtLogin.setText("Login");
        jbtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLoginActionPerformed(evt);
            }
        });

        jbtCreateUser.setText("Create user");
        jbtCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCreateUserActionPerformed(evt);
            }
        });

        jtaChat.setColumns(20);
        jtaChat.setRows(5);
        jScrollPane1.setViewportView(jtaChat);
        DefaultCaret caret = (DefaultCaret)jtaChat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jlsUsers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsUsersValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jlsUsers);

        jtxMessage.setText("Type message here..");
        jtxMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxMessageActionPerformed(evt);
            }
        });
        jtxMessage.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
               jtxMessage.setText("");
            }
        });

        jbtChallenge.setText("Challenge");
        jbtChallenge.setEnabled(false);
        jbtChallenge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtChallengeActionPerformed(evt);
            }
        });

        jbtHighscore.setText("Highscore");
        jbtHighscore.setEnabled(false);
        jbtHighscore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtHighscoreActionPerformed(evt);
            }
        });

        jlbName.setText("Name");

        jtxServerIP.setText("localhost");
        jtxServerIP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxServerIPFocusGained(evt);
            }
        });

        jlbPassword.setText("Password");

        jlbServerIP.setText("SERVER IP");

        jlbMinPort.setText("Min port");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxName, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbPassword)
                                    .addComponent(jtxPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxMinPort, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbMinPort))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbServerIP)
                                    .addComponent(jtxServerIP, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtCreateUser, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtChallenge)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtHighscore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jtxMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbName)
                    .addComponent(jlbPassword)
                    .addComponent(jlbMinPort)
                    .addComponent(jlbServerIP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtLogin)
                    .addComponent(jbtCreateUser)
                    .addComponent(jtxPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxServerIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxMinPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtChallenge)
                            .addComponent(jbtHighscore)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
//Binder till RMI-register och loggar in
private void jbtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLoginActionPerformed

    if (!boundToRegistry) {
        bindToRMI();
    }
    //inloggning
    login();

}//GEN-LAST:event_jbtLoginActionPerformed

private void jbtCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCreateUserActionPerformed

    //om inte kopplad till rmi, g�r det.
    if (!boundToRegistry) {
        bindToRMI();
    }
    try {
        result = si.createUser(jtxName.getText(), jtxPassword.getText());
           } catch (RemoteException ex) {
        Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
    }

    if (result) {
        login();
    } else {
        JOptionPane.showMessageDialog(null, "Could not create user.");
    }
}//GEN-LAST:event_jbtCreateUserActionPerformed

private void jtxMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxMessageActionPerformed
    try {
        si.sendPublicMessage(jtxName.getText() + ": " + jtxMessage.getText());
    } catch (RemoteException ex) {
        Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
    }
    jtxMessage.setText("");
}//GEN-LAST:event_jtxMessageActionPerformed
//kollar ifall en anv�ndare �r vald och dessutom inte �r den egna, och s�tter challengeknappen till enabled.
private void jlsUsersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsUsersValueChanged
    if (jlsUsers.getSelectedIndex() > -1 && jlsUsers.getSelectedIndex() <= users.size() && !users.get(jlsUsers.getSelectedIndex()).getName().equals(me.getName())) {
        jbtChallenge.setEnabled(true);
    } else {
        jbtChallenge.setEnabled(false);
    }
}//GEN-LAST:event_jlsUsersValueChanged
//visar highscore
private void jbtHighscoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtHighscoreActionPerformed
    try {
        HighscoreViewer hv = new HighscoreViewer(this, false, si.getHighscore(), si.getRanking());
        hv.setVisible(true);
    } catch (RemoteException ex) {
        Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_jbtHighscoreActionPerformed

    private void jtxPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxPasswordFocusGained
        jtxPassword.setSelectionStart(0);
        jtxPassword.setSelectionEnd(jtxPassword.getText().length());
    }//GEN-LAST:event_jtxPasswordFocusGained

    private void jtxNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxNameFocusGained
        jtxName.setSelectionStart(0);
        jtxName.setSelectionEnd(jtxName.getText().length());
    }//GEN-LAST:event_jtxNameFocusGained

    private void jbtChallengeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtChallengeActionPerformed
         try {
             
             if(si.getPlayerStatus(me) != 0) {
                 JOptionPane.showMessageDialog(null, "You are already in a game!", "Sorry!", JOptionPane.WARNING_MESSAGE);
                 return;
             }
             
            if(si.getPlayerStatus(users.get(jlsUsers.getSelectedIndex())) == 0) si.challengeUser(me, users.get(jlsUsers.getSelectedIndex()));
            else JOptionPane.showMessageDialog(null, "User not free for play.", "Sorry!", JOptionPane.WARNING_MESSAGE);

        } catch (RemoteException ex) {
            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtChallengeActionPerformed

private void jtxServerIPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxServerIPFocusGained
    jtxServerIP.setSelectionStart(0);
    jtxServerIP.setSelectionEnd(jtxServerIP.getText().length());
}//GEN-LAST:event_jtxServerIPFocusGained

private void jtxMinPortFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxMinPortFocusGained
    jtxMinPort.setSelectionStart(0);
    jtxMinPort.setSelectionEnd(jtxMinPort.getText().length());
}//GEN-LAST:event_jtxMinPortFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtChallenge;
    private javax.swing.JButton jbtCreateUser;
    private javax.swing.JButton jbtHighscore;
    private javax.swing.JButton jbtLogin;
    private javax.swing.JLabel jlbMinPort;
    private javax.swing.JLabel jlbName;
    private javax.swing.JLabel jlbPassword;
    private javax.swing.JLabel jlbServerIP;
    private javax.swing.JList jlsUsers;
    private javax.swing.JTextArea jtaChat;
    private javax.swing.JTextField jtxMessage;
    private javax.swing.JTextField jtxMinPort;
    private javax.swing.JTextField jtxName;
    private javax.swing.JTextField jtxPassword;
    private javax.swing.JTextField jtxServerIP;
    // End of variables declaration//GEN-END:variables

    public void setStatus(int status) {
        try {
            si.setPlayerStatus(me, status);
        } catch (RemoteException e) {
        }
    }

    public void updateScore() {
        try {
            si.upDateScores(player1.getName(), player2.getName(), player1.getPoints(), player2.getPoints());
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void bindToRMI() {
        try {
            Registry registry = LocateRegistry.getRegistry(jtxServerIP.getText(), 9009);
            si = (Server3Interface) registry.lookup("ServerInterface");
            boundToRegistry = true;
        }   catch (ConnectException ex) {
            System.out.println(ex);
        }
        catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(null, "Server could not be found. Try another ip-adress.", "Warning!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            JOptionPane.showMessageDialog(null, "Server could not be found. Try another ip-adress.", "Warning!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
        }catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Server could not be found. Try another ip-adress.", "Warning!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private String dialogLanWan() {
        if (JOptionPane.showOptionDialog(null,"Play using localhost?",
                "LAN or WAN?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Yes", "No"}, "No") == JOptionPane.YES_OPTION) {
            return "localhost";
            } else {
            return getMyExternalIp();
        }
    }

    private void login() {
        try {
            String lanwan = dialogLanWan();
            me = si.login(jtxName.getText(), jtxPassword.getText(), lanwan, Integer.parseInt(jtxMinPort.getText()));

            String message;
            if (me != null) {
                message = "You are logged in!";

                jtxName.setEnabled(false);
                jtxPassword.setEnabled(false);
                jtxMinPort.setEnabled(false);
                jtxServerIP.setEnabled(false);
                jbtLogin.setEnabled(false);
                jbtCreateUser.setEnabled(false);

                Thread threadGetThings = new Thread(new getThingsFromServer());
                threadGetThings.start();
                
                Thread threadReportIn = new Thread() {
                    
                    public void run() {
                    while(true) {
                            try {
                                si.reportIn(me);
                                this.sleep(300);
                            } catch (InterruptedException e) {
                                System.out.println(e);
                            } catch (RemoteException e) {
                                System.out.println(e);
                            }
                        }
                    }
                };
                threadReportIn.start();
                
                jbtHighscore.setEnabled(true);
            } else {
                message = "Login failed.";
            }
            JOptionPane.showMessageDialog(null, message);
        } catch(ConnectException ex) {
            JOptionPane.showMessageDialog(null, "Timed out. Please try restarting client.", "Warning!", JOptionPane.WARNING_MESSAGE);
            System.out.println(ex);
        }
            catch (RemoteException ex) {
            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Server could not be found. Try another ip-adress.", "Warning!", JOptionPane.WARNING_MESSAGE);
            System.out.println(ex);
        }
    }

    private class getThingsFromServer implements Runnable {

        private int chatPos = 0;
        private List chatUpdate;

        @Override
        public void run() {
            while (true) {

                //updatera userlist
                updateUserList();

                //uppdatera chat
                updateChat();

                //kolla utmaningar
                checkChallenge();

                //vila lite
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        private void updateUserList() {
            try {
                int selection = jlsUsers.getSelectedIndex();
                users = si.getUserlist();
                jlsUsers.setListData(users.toArray());
                jlsUsers.setSelectedIndex(selection);
            } catch (RemoteException ex) {
                Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void updateChat() {
            try {
                chatUpdate = si.getPublicChatMessagesSince(chatPos);
                for (Object chat : chatUpdate) {
                    jtaChat.append((String) chat + "\n");
                }
                chatPos += chatUpdate.size();
            } catch (RemoteException ex) {
                Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        private void checkChallenge() {
            try {

                User challenger = si.challenged(me.getName());

                if (challenger != null) {

                    // Player har f�tt en utmaning
                    if (si.getPlayerStatus(me) == 1) {

                        // Visa accept challenge-dialog

                        if (JOptionPane.showOptionDialog(null,
                                challenger.getName() + " has challenged you! Do You accept?",
                                "Challenge!", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null,
                                new String[]{"Yes", "No"}, "No") == JOptionPane.YES_OPTION) {

                            // Player accepterade utmaningen
                            si.setPlayerStatus(me, 2);

                            si.answerChallenge(challenger, me, true);
                            try {
                                // S�tt spelare
                                player1 = new NetPlayerExternal(me.getPort());
                            } catch (UnknownHostException ex) {
                                Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            player1.setName(challenger.getName());
                            player1.setID(1);
                            try {
                                // S�tt spelare
                                
                                if (JOptionPane.showOptionDialog(null,"Local computer player?",
                                        "Computer?", JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE, null,
                                                                 new String[]{"Yes", "No"}, "No") == JOptionPane.YES_OPTION) {
                                    player2 = new NetComputerLocal(challenger.getIP(), challenger.getPort(), 2);
                                
                                } else {
                                    player2 = new NetPlayerLocal(challenger.getIP(), challenger.getPort());
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            player2.setName(me.getName());
                            player2.setID(2);

                            Othello.releaseLock();
                            //si.setPlayerStatus(me, 0);
                        } else {
                            // Player nekade utmaningen
                            si.setPlayerStatus(me, 0);
                        }
                    }

                    // Player har f�tt svar p� en utmaning
                    if (si.getPlayerStatus(me) == 777) {
                        si.setPlayerStatus(me, 2);

                        try {
                            // S�tt spelare
                            if (JOptionPane.showOptionDialog(null,"Local computer player?",
                                    "Computer?", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null,
                                                             new String[]{"Yes", "No"}, "No") == JOptionPane.YES_OPTION) {
                                player1 = new NetComputerLocal(challenger.getIP(), challenger.getPort(), 1);
                            
                            } else {
                                player1 = new NetPlayerLocal(challenger.getIP(), challenger.getPort());
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        player1.setName(me.getName());
                        player1.setID(1);

                        try {
                            // S�tt spelare
                            player2 = new NetPlayerExternal(me.getPort());
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        player2.setName(challenger.getName());
                        player2.setID(2);

                        // Starta ett game
                        Othello.releaseLock();
                        //si.setPlayerStatus(me, 0);
                    }

                    // Ta bort challenger
                    si.setChallenger(me, null);

                }
            } catch (RemoteException ex) {
                Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
//h�mtar klientens externa ip adress via en n�ttj�nst.
    private String getMyExternalIp() {

        BufferedReader in = null;
        try {
            URL whatismyip = new URL("http://automation.whatismyip.com/n09230945.asp");
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } catch (IOException ex) {
            Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(LobbyLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "IP not found.";
    }
}