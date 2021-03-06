package connectx;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * ConnectXGUI is the user interface and main driver for the Connect-X game.
 * It saves the current game parameters to a Board object and calls the Player
 * object to make moves (or waits for the human or network).  When a game is
 * finished, it reports the results.

 *
 * @author Dennis.Schweitzer
 */
public class ConnectXGUI extends javax.swing.JFrame implements MouseListener{
    private Board gameBoard;        // Object holding current game and params
    private int firstPlayer = 1;    // which player moves first
    private int currPlayer;         // player whose turn it is
    private boolean gameGoing = false;  // flag if a game is in progress
    private Player player1, player2;  // Player objects to make moves
    private int moveCnt;            // keep track of number of moves in a game

 /**  Constructor - initializes GUI components, creates a new board making
  *   it visible to start the program.
  *
  */
    public ConnectXGUI() {
        initComponents();
        quitBtn.setEnabled(false);  // only allow quit if game in progress
        addMouseListener(this);
        gameBoard = new Board(8,8,4,1);  // default game board
        gamePanel.add(gameBoard);
        gameBoard.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        firstMoveBtnGrp = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        redRadioBtn = new javax.swing.JRadioButton();
        blueRadioBtn = new javax.swing.JRadioButton();
        startBtn = new javax.swing.JButton();
        quitBtn = new javax.swing.JButton();
        gamePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        numCols = new javax.swing.JComboBox();
        numRows = new javax.swing.JComboBox();
        numInARow = new javax.swing.JComboBox();
        msgLabel = new javax.swing.JLabel();
        player1Combo = new javax.swing.JComboBox();
        player2Combo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        lookAheadCombo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        tournamentBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Connect-X");

        jLabel2.setText("Number of Columns: ");

        jLabel3.setText("Number in a Row:");

        jLabel4.setText("Player Moving First: ");

        firstMoveBtnGrp.add(redRadioBtn);
        redRadioBtn.setSelected(true);
        redRadioBtn.setText("Red");
        redRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redRadioBtnActionPerformed(evt);
            }
        });

        firstMoveBtnGrp.add(blueRadioBtn);
        blueRadioBtn.setText("Blue");
        blueRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blueRadioBtnActionPerformed(evt);
            }
        });

        startBtn.setText("Start Game");
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBtnActionPerformed(evt);
            }
        });

        quitBtn.setText("Quit Game");
        quitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBtnActionPerformed(evt);
            }
        });

        gamePanel.setBackground(new java.awt.Color(255, 255, 255));
        gamePanel.setPreferredSize(new java.awt.Dimension(500, 400));

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        jLabel5.setText("Number of Rows:");

        numCols.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6", "7", "8", "9", "10" }));
        numCols.setSelectedIndex(2);
        numCols.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numColsActionPerformed(evt);
            }
        });

        numRows.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6", "7", "8", "9", "10" }));
        numRows.setSelectedIndex(2);
        numRows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numRowsActionPerformed(evt);
            }
        });

        numInARow.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3", "4", "5", "6", "7", "8" }));
        numInARow.setSelectedIndex(1);
        numInARow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numInARowActionPerformed(evt);
            }
        });

        msgLabel.setText("--> Press Start Game to Begin <--");

        player1Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Human", "Strategy 1", "Strategy 2" }));

        player2Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Human", "Strategy 1", "Strategy 2" }));

        jLabel6.setText("Max Number of Look Ahead Moves:");

        lookAheadCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
        lookAheadCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookAheadComboActionPerformed(evt);
            }
        });

        jLabel7.setText("Name:");

        tournamentBtn.setText("Tournament");
        tournamentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tournamentBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numCols, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numRows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numInARow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(blueRadioBtn)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(redRadioBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(player1Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player2Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(nameField))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lookAheadCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(quitBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tournamentBtn)
                                .addGap(26, 26, 26)
                                .addComponent(msgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(numCols, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(numRows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(numInARow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(redRadioBtn)
                    .addComponent(player1Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(lookAheadCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blueRadioBtn)
                    .addComponent(player2Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startBtn)
                    .addComponent(quitBtn)
                    .addComponent(msgLabel)
                    .addComponent(tournamentBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * startBtnActionPerformed is when the user says to start a game
 * @param evt - button press event
 */
    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBtnActionPerformed
        
        gameBoard.clearBoard();    // clear the board of old moves
        currPlayer = firstPlayer;  // designated first player starts off
        if(player1Combo.getSelectedIndex()==1)
          player1 = new Player(1,1);  // set player1 to use strategy 1
        else if(player1Combo.getSelectedIndex() ==2)
           player1 = new Player(1,2);  // set player1 to use strategy 2
        if(player2Combo.getSelectedIndex()==1)
          player2 = new Player(2,1);  // set player2 to use strategy 1
        else if(player2Combo.getSelectedIndex() ==2)
           player2 = new Player(2,2);  // set player2 to use strategy 2
        gameGoing = true;             // enable/disable buttons for game going
        numCols.setEnabled(false);
        numRows.setEnabled(false);
        numInARow.setEnabled(false);
        player1Combo.setEnabled(false);
        player2Combo.setEnabled(false);
        lookAheadCombo.setEnabled(false);
        blueRadioBtn.setEnabled(false);
        redRadioBtn.setEnabled(false);
        tournamentBtn.setEnabled(false);
        quitBtn.setEnabled(true);
        getMove();                     // get a move
    }
    /**
     * getMove gets the next move based on whose turn it is, and what type of
     * player they are (human, Player, or network)
     */
    private void getMove(){
         if(currPlayer == 1){
            msgLabel.setText("--> Red player's move <--");
            if(player1Combo.getSelectedIndex() != 0){ // computer player
              int playerMove = player1.makeMove(gameBoard);
              processMove(playerMove);
             }
        }
         else {
            msgLabel.setText("--> Blue player's move <--");
            if(player2Combo.getSelectedIndex() != 0){ // computer player
              int playerMove = player2.makeMove(gameBoard);
              processMove(playerMove);

             }
        }
    }
    /**
     * processMove takes the move made by the player, checks for a win, and
     * updates the parameters
     *
     * @param move - what column player wants to move to
     */
    private void processMove(int move){
        int winner;
       if(!gameBoard.addMove(move,currPlayer)){ // make sure it was a legal move
          msgLabel.setText("--> Illegal move <--");
          gameGoing = false;
          if(currPlayer == 1)  // if not legal, the other player wins
              winner = 2;
          else
              winner = 1;
       } else {                // if it was a legal move, see if someone won
           winner =  gameBoard.checkWin();
           gameGoing = winner == -1;
       }
        if(gameGoing){          // if game is still going, update everything
          moveCnt++;
          gameBoard.paintImmediately(0,0,500,500);
          if(currPlayer == 1)   // change player to opponent
            currPlayer = 2;
          else
            currPlayer = 1;
          getMove();
        }
        else {              // if someone won, check who and update
         if(winner == 0)
            msgLabel.setText("--> Draw Game <--");
         else if(winner == 1)
            msgLabel.setText("--> Red Wins! <--");
        else if(winner == 2)
            msgLabel.setText("--> Blue Wins! <--");
        numCols.setEnabled(true);
        numRows.setEnabled(true);
        numInARow.setEnabled(true);
        player1Combo.setEnabled(true);
        player2Combo.setEnabled(true);
        lookAheadCombo.setEnabled(true);
        blueRadioBtn.setEnabled(true);
        redRadioBtn.setEnabled(true);
        tournamentBtn.setEnabled(true);
        quitBtn.setEnabled(false);
        }

    }//GEN-LAST:event_startBtnActionPerformed
/**
 * quitBtnActionPerformed handles the quit button being pushed
 *
 * @param evt - button push event
 */
    private void quitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBtnActionPerformed
        // reset everything
        gameGoing = false;
        numCols.setEnabled(true);
        numRows.setEnabled(true);
        numInARow.setEnabled(true);
        player1Combo.setEnabled(true);
        player2Combo.setEnabled(true);
        lookAheadCombo.setEnabled(true);
        blueRadioBtn.setEnabled(true);
        redRadioBtn.setEnabled(true);
        tournamentBtn.setEnabled(true);
        quitBtn.setEnabled(false);
        msgLabel.setText("--> Press Start Game to Begin <--");

    }//GEN-LAST:event_quitBtnActionPerformed
/**
 * Following methods all create a new board whenever user changes a parameter.
 * Note, when a game is in progress, these buttons are disabled, so something
 * cannot be changed in the middle of a game.
 *
 * @param evt - GUI event
 */
    private void numColsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numColsActionPerformed
        gameBoard.updateBoard(numRows.getSelectedIndex()+6,numCols.getSelectedIndex()+6,
                numInARow.getSelectedIndex()+3,lookAheadCombo.getSelectedIndex()+1);
    }//GEN-LAST:event_numColsActionPerformed

    private void numRowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numRowsActionPerformed
        gameBoard.updateBoard(numRows.getSelectedIndex()+6,numCols.getSelectedIndex()+6,
                numInARow.getSelectedIndex()+3,lookAheadCombo.getSelectedIndex()+1);
    }//GEN-LAST:event_numRowsActionPerformed

    private void numInARowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numInARowActionPerformed
        gameBoard.updateBoard(numRows.getSelectedIndex()+6,numCols.getSelectedIndex()+6,
                numInARow.getSelectedIndex()+3,lookAheadCombo.getSelectedIndex()+1);
    }//GEN-LAST:event_numInARowActionPerformed

    private void redRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redRadioBtnActionPerformed
        // TODO add your handling code here:
        firstPlayer = 1;
    }//GEN-LAST:event_redRadioBtnActionPerformed

    private void blueRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blueRadioBtnActionPerformed
        // TODO add your handling code here:
        firstPlayer = 2;
    }//GEN-LAST:event_blueRadioBtnActionPerformed

    private void lookAheadComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookAheadComboActionPerformed
        gameBoard.updateBoard(numRows.getSelectedIndex()+6,numCols.getSelectedIndex()+6,
                numInARow.getSelectedIndex()+3,lookAheadCombo.getSelectedIndex()+1);
    }//GEN-LAST:event_lookAheadComboActionPerformed

    private void tournamentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tournamentBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tournamentBtnActionPerformed

 /*
 * necessary methods to implement MouseListener
 * @param e - mouse event
 */
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
     }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
/**
 * mouseClicked is the only mouse event we care about.  Checks if one of the
 * players is a human and it is their turn.  If so, figure out which column
 * was clicked, and make the appropriate move.
 *
 * @param e - mouse event
 */
    public void mouseClicked(MouseEvent e) {
        if(!gameGoing ||
                (currPlayer == 1 && player1Combo.getSelectedIndex()!= 0) ||
                (currPlayer == 2 && player2Combo.getSelectedIndex()!= 0))
            return;
        int x = e.getX() - gamePanel.getX();
        int move = gameBoard.mouseToCol(x);
        if(move != -1 && gameBoard.legalMove(move)) {
            processMove(move);
        }
    }

    /**
    * main program for the GUI
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectXGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton blueRadioBtn;
    private javax.swing.ButtonGroup firstMoveBtnGrp;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox lookAheadCombo;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JComboBox numCols;
    private javax.swing.JComboBox numInARow;
    private javax.swing.JComboBox numRows;
    private javax.swing.JComboBox player1Combo;
    private javax.swing.JComboBox player2Combo;
    private javax.swing.JButton quitBtn;
    private javax.swing.JRadioButton redRadioBtn;
    private javax.swing.JButton startBtn;
    private javax.swing.JButton tournamentBtn;
    // End of variables declaration//GEN-END:variables

}
